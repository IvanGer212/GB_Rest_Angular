angular.module('market').controller('cartController',function ($scope, $http, $location, $localStorage) {
    const contextPath = 'http://localhost:8081/app/api/';
    const orderContextPath = 'http://localhost:8080/app/api/';

    $scope.loadProductsOnBin = function (){
        $http.get(contextPath + "v1/cart").then(function (response){
            $scope.ProductBinList = response.data;
        })
    }

    $scope.deleteProductOnBin = function (productId){
        $http.get(contextPath + "v1/cart/delete/"+productId).then(function (response){
            $scope.loadProductsOnBin();
        });
    };

    $scope.clearCart = function (){
        $http.get(contextPath + "v1/cart/clear").then(function (response){
            $scope.loadProductsOnBin();
        });
    };

    $scope.changeScore = function (id, mark) {
        $http({
            url: contextPath + "v1/cart/change_score",
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