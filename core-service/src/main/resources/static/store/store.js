angular.module('market').controller('storeController',function ($scope, $http, $location, $localStorage) {
    const contextPath = 'http://localhost:8080/app/api/v1';
    const cartContextPath = 'http://localhost:8081/app/api/';

    $scope.loadProducts = function(pageIndex){
        $http({
            url: contextPath + "/products",
            method: 'GET',
            params: {
                p: pageIndex,
                title: $scope.filter ? $scope.filter.title: null,
                min_price: $scope.filter ? $scope.filter.min: null,
                max_price: $scope.filter ? $scope.filter.max: null
            }
        }).then(function(response){
            $scope.ProductList = response.data.content;
        })
    }


    $scope.addProductOnBin = function (id){
        $http.get(cartContextPath + "v1/cart/add/" + id)
            .then(function (response){
            })
    };


    $scope.loadProducts();
})