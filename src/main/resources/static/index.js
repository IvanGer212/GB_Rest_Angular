angular.module('app',['ngStorage']).controller('productController',function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:8080/app/api/v1';

    $scope.tryToAuth = function (){
      $http.post('http://localhost:8080/auth', $scope.user)
          .then(function successCallback(response) {
              if (response.data.token) {
                  $http.default.headers.common.Authorization = 'Bearer ' + response.data.token;
                  $localStorage.myMarketUser = {username: $scope.user.username, token: response.data.token};

                  $scope.user.username = null;
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

    if ($localStorage.myMarketUser){
        try {
            let jwt = $localStorage.myMarketUser.token;
            let payload = JSON.parse(atob(jwt.split('.')[1]));
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


    $scope.loadProducts = function(pageIndex){
        console.log(pageIndex);
        //console.log(size);
        //console.log(sizeRet);
        // if (size!= sizeRet) {
        //     sizeRet = size;
        // }
        $http({
            url: contextPath + "/products",
            method: 'GET',
            params: {
                //size: sizeRet,
                p: pageIndex,
                title: $scope.filter ? $scope.filter.title: null,
                min_price: $scope.filter ? $scope.filter.min: null,
                max_price: $scope.filter ? $scope.filter.max: null
            }
        }).then(function(response){
            $scope.ProductList = response.data.content;
        })
    }


    $scope.deleteProduct = function (productId){
        console.log('delete');
        $http.delete(contextPath + "/products/" + productId)
            .then(function (response){
                $scope.loadProducts();
            });
    }

    // $scope.filterByCost = function (min, max){
    //     console.log($scope.Limits);
    //     $http({
    //         url: contextPath + "/product-between",
    //         method: 'GET',
    //         params: {
    //             min: $scope.Limits.min,
    //             max: $scope.Limits.max
    //         }
    //     }).then(function (response){
    //         $scope.ProductList = response.data;
    //     });
    // }


    $scope.createProduct = function (title, price){
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
        $http.get(contextPath + "/cart").then(function (response){
            $scope.ProductBinList = response.data;
        })
    }

    $scope.addProductOnBin = function (product){
         $http({
            url: contextPath + "/cart/add",
            method: 'POST',
            data: product

        }).then(function (response){
            $scope.loadProductsOnBin();
        })
    };


    $scope.deleteProductOnBin = function (productId){
        $http.get(contextPath + "/cart/delete/"+productId).then(function (response){
            $scope.loadProductsOnBin();
        });
    };

    $scope.clearCart = function (){
        $http.get(contextPath + "/cart/clear").then(function (response){
            $scope.loadProductsOnBin();
        });
    };

    $scope.changeScore = function (id, mark) {
        console.log(id, mark);
        $http({
            url: contextPath + "/cart/change_score",
            method: 'GET',
            params: {
                id: id,
                mark: mark
            }
        }).then(function (response) {
            $scope.loadProductsOnBin();
        })
    }

    $scope.loadProductsOnBin();
    $scope.loadProducts();
})