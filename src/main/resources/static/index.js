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

    // $scope.createProduct = function (title, price) {
    //     console.log($scope.Product);
    //     $http.post(contextPath + "/createProduct", $scope.Product)
    //         .then(function (response){
    //             console.log(response.data);
    //          //   $scope.msg = response.data;
    //             $scope.loadProducts();
    //         });
    //
    // }

    $scope.createProduct = function (title, price){
        $http({
            url: contextPath + "/createProduct",
            method: 'POST',
            data: $scope.Product
        }).then(function (response){
            console.log(response.data.title);
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





    $scope.loadProducts();
})