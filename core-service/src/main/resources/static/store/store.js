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


    $scope.deleteProduct = function (productId){
        $http.delete(contextPath + "/products/" + productId)
            .then(function (response){
                $scope.loadProducts();
            });
    }

    $scope.addProductOnBin = function (id){
        $http.get(cartContextPath + "v1/cart/add/" + id)
            .then(function (response){
            })
    };

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

    $scope.loadProducts();
})
