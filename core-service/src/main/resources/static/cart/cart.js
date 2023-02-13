angular.module('market').controller('cartController',function ($scope, $http, $location, $localStorage) {
    const contextPath = 'http://localhost:8081/app/api/';
    const orderContextPath = 'http://localhost:8080/app/api/';
    let username;

    $scope.getUsername = function (){
        if ($localStorage.myMarketUser){
            username = $localStorage.myMarketUser.email;
        } else {username = null;}
    }

    $scope.getUsername();

    $scope.loadProductsOnBin = function (){
        $http({
            url: contextPath + "v1/cart/0",
            method: 'GET',
            params: {
                email: username
            }
        }).then(function (response){
            $scope.ProductBinList = response.data;
        })
    }

    $scope.deleteProductOnBin = function (productId){
        $http({
            url: contextPath + "v1/cart/0/delete/"+productId,
            method: 'GET',
            params: {
                email: username
            }
        }).then(function (response){
            $scope.loadProductsOnBin();
        });
    };

    $scope.clearCart = function (){
        $http({
            url: contextPath + "v1/cart/0/clear",
            method: 'GET',
            params: {
                email: username
            }
        }).then(function (response){
            $scope.loadProductsOnBin();
        });
    };

    $scope.changeScore = function (id, mark) {
        $http({
            url: contextPath + "v1/cart/0/change_score",
            method: 'GET',
            params: {
                email: username,
                id: id,
                mark: mark
            }
        }).then(function (response) {
            $scope.loadProductsOnBin();
        })
    }

    $scope.createOrder = function (cost){
        let username;
        let loged = $scope.isUserLoggedIn;
        if (loged()){
            username = $localStorage.myMarketUser.username;
        }
        else {
            username = "";
        }

        $http({
            url: orderContextPath + "v1/order",
            method: 'POST'
        }).then(function (response){
            $scope.clearCart()
        })
    }

    $scope.loadProductsOnBin();
})