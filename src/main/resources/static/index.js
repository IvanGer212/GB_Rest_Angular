angular.module('app',[]).controller('productController',function ($scope, $http) {
    const contextPath = 'http://localhost:8080/app/api/v1';


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
        $http.get(contextPath + "/products/bin").then(function (response){
            $scope.ProductBinList = response.data;
        })
    }

    $scope.addProductOnBin = function (product){
         $http({
            url: contextPath + "/products/bin",
            method: 'POST',
            data: product

        }).then(function (response){
            $scope.loadProductsOnBin();
        })
    };


    $scope.deleteProductOnBin = function (productName){
        $http.delete(contextPath + "/products/bin/"+productName).then(function (response){
            $scope.loadProductsOnBin();
        });
    }

    $scope.loadProductsOnBin();
    $scope.loadProducts();
})