angular.module('app',[]).controller('productController',function ($scope, $http) {
    const contextPath = 'http://localhost:8080/app';

    console.log(123);

    $scope.loadProducts = function (){
        $http.get(contextPath + '/product')
            .then(function (response){
                $scope.ProductList = response.data;
            });
    };

    $scope.deleteProduct = function (productId){
        $http.get(contextPath+'/product/delete/' + productId)
            .then(function (response){
                $scope.loadProducts();
            });
    }
    $scope.loadProducts();
})