angular.module('app',['ngStorage']).controller('productController',function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:8080/app/api/v1';


    $scope.tryToAuth = function (){
        $http.post('http://localhost:8080/auth', $scope.user)
            .then(function successCallback(response) {
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.myMarketUser = {email: $scope.user.email, username: response.data.username, token: response.data.token};
                    $scope.user.email = null;
                    $scope.user.password = null;

                }
            })
    };

    $scope.tryToLogout = function (){
        $scope.clearUser();
        $scope.user = null;
        $location.pathname('/')
    }

    $scope.clearUser = function (){
        delete $localStorage.myMarketUser;
        $http.defaults.headers.common.Authorization = '';
    };

    $scope.isUserLoggedIn = function (){
        if ($localStorage.myMarketUser){
            return true;
        } else {
            return false;
        }
    };

    $scope.loadUsers = function (){
        $http.get(contextPath + "/users").then(function (response){
            console.log(response.data);
            $scope.UserList = response.data;
        })
    }

    $scope.deleteUser = function (userId){
        console.log('delete user')
        $http.delete(contextPath + '/users/' + userId)
            .then(function (response){
                $scope.loadUsers();
            });
    }

    $scope.createUser = function (userName, surname, password, email, phone, roles){
        $http({
            url: contextPath + "/users",
            method: 'POST',
            data: $scope.User
        }).then(function (response){
            $scope.loadUsers();
        });
    }

    if ($localStorage.myMarketUser){
        try {
            let jwt = $localStorage.myMarketUser.token;
            let payload = JSON.parse(atob(jwt.split('.')[1]));
            console.log(payload);
            console.log(payload.username);
            let curentTime = parseInt(new Date().getTime()/1000);
            if (curentTime > payload.exp){
                console.log("Token is expired!!!");
                delete $localStorage.myMarketUser;
                $http.defaults.headers.common.Authorization = '';
            }
        } catch (e) {
        }

        $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.myMarketUser.token;
    }


    $scope.loadProducts = function(pageIndex = 1){
        $http({
            url: contextPath + "/products",
            method: 'GET',
            params: {
                title: $scope.filter ? $scope.filter.title: null,
                min_price: $scope.filter ? $scope.filter.min: null,
                max_price: $scope.filter ? $scope.filter.max: null
            }
        }).then(function(response){
            $scope.ProductList = response.data.content;
        })
    }


    $scope.deleteProduct = function (productId){
        $http.delete(contextPath + "/products/" + productId)
            .then(function (response){
                $scope.loadProducts();
            });
    }

    $scope.createProduct = function (title, price){
        console.log('create');
        $http({
            url: contextPath + "/products",
            method: 'POST',
            data: $scope.Product
        }).then(function (response){
                if(response.data.cost <= 0){
                    $scope.msg = "Price could not be less or equal 0!";
                }
                else if (response.data.title){
                    $scope.msg = "";
                }
                else {
                    $scope.msg = "Product name could not be empty!";
                }

                $scope.loadProducts();

        });
    }

    $scope.loadProductsOnBin = function (){
        $http.get("http://localhost:8081/app/api/v1/cart").then(function (response){
            $scope.ProductBinList = response.data;
        })
    }

    $scope.addProductOnBin = function (id){
         $http.get("http://localhost:8081/app/api/v1/cart/add/" + id)
        .then(function (response){
            $scope.loadProductsOnBin();
        })
    };


    $scope.deleteProductOnBin = function (productId){
        console.log('delete');
        $http.get("http://localhost:8081/app/api/v1/cart/delete/"+productId).then(function (response){
            $scope.loadProductsOnBin();
        });
    };

    $scope.clearCart = function (){
        console.log('clear');
        $http.get("http://localhost:8081/app/api/v1/cart/clear").then(function (response){
            $scope.loadProductsOnBin();
        });
    };

    $scope.changeScore = function (id, mark) {
        console.log(id);
        console.log(mark);
        $http({
            url: "http://localhost:8081/app/api/v1/cart/change_score",
            method: 'GET',
            params: {
                id: id,
                mark: mark
            }
        }).then(function (response) {
            $scope.loadProductsOnBin();
        })
    }

    $scope.createOrder = function (cost){
        console.log(cost);
        let username;
        let loged = $scope.isUserLoggedIn;
        if (loged()){
            username = $localStorage.myMarketUser.username;
        }
        else {
            username = "";
        }

        $http({
            url: contextPath + "/order",
            method: 'POST'
        }).then(function (response){
            $scope.clearCart()
        })
    }

    $scope.loadProductsOnBin();
    $scope.loadProducts();
    $scope.loadUsers();
})