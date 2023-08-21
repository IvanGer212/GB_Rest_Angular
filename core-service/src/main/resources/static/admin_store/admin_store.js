angular.module('market').controller('adminStoreController',function ($scope, $http, $location, $localStorage) {
    const contextPath = 'http://localhost:8080/app/api/v1';
    let username;
    let categoryId;

    $scope.getUsername = function (){
        if ($localStorage.myMarketUser){
            username = $localStorage.myMarketUser.email;
        } else {username = null;}
        console.log(username);
    }

    $scope.getUsername();

    $scope.loadProducts = function(pageIndex){
        $http({
            url: contextPath + "/products",
            method: 'GET',
            params: {
                p: pageIndex,
                title: $scope.filter ? $scope.filter.title: null,
                min_price: $scope.filter ? $scope.filter.min: null,
                max_price: $scope.filter ? $scope.filter.max: null,
                category: categoryId
            }
        }).then(function(response){
            $scope.ProductsPage = response.data;
            $scope.generatePagesList($scope.ProductsPage.totalPages);
        })
    }


    $scope.deleteProduct = function (productId){
        $http.delete(contextPath + "/products/" + productId)
            .then(function (response){
                $scope.loadProducts();
            });
    }


    $scope.createProduct = function (title, price, category){
        // console.log('create');
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

    $scope.loadCategories = function(){
        $http.get(contextPath + "/categories").then(function(response){
            //console.log(response.data);
            $scope.CategoryList = response.data;
        })
    }

    $scope.findProductsByCategory = function(id){
        categoryId = id;
        $scope.loadProducts();
    }

    $scope.generatePagesList = function (totalPage){
        $scope.pagesList = [];
        for (let i = 0; i < totalPage; i++){
            $scope.pagesList.push(i+1);
        }
    }

    // $scope.findProductsByCategory = function(id){
    //     $http({
    //         url: contextPath + "/products/by_category",
    //         method: 'GET',
    //         params: {id : id}
    //     }).then(function(response){
    //         console.log(response.data);
    //         $scope.ProductListCategory = response.data;
    //         $scope.loadProducts();
    //     })
    // }

    $scope.loadCategories();

    $scope.loadProducts();
})
