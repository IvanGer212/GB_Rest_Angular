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

    $scope.filterByCost = function (min, max){
        console.log($scope.Limits);
        $http({
            url: contextPath + "/product-between",
            method: 'GET',
            params: {
                min: $scope.Limits.min,
                max: $scope.Limits.max
            }
        }).then(function (response){
            $scope.ProductList = response.data;
        });
    }

    $scope.createProduct = function (title, price) {
        console.log($scope.Product);
        $http.post(contextPath + "/createProduct", $scope.Product)
            .then(function (response){
                $scope.loadProducts();
            });

    }

    $scope.loadProducts();
})