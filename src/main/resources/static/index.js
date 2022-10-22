angular.module('app',[]).controller('productController',function ($scope, $http) {
    const contextPath = 'http://localhost:8080/app';


    $scope.loadProducts = function (){
        $http.get(contextPath + '/product')
            .then(function (response){
                $scope.ProductList = response.data;
            });
    };

    $scope.deleteProduct = function (productId){
        $http.get(contextPath+'/delete/' + productId)
            .then(function (response){
                console.log(123);
                console.log(productId);
                $scope.loadProducts();
            });
    }
    $scope.loadProducts();
})