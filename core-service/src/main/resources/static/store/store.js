angular.module('market').controller('storeController',function ($scope, $http, $location, $localStorage) {
    const contextPath = 'http://localhost:8080/app/api/v1';
    const cartContextPath = 'http://localhost:8081/app/api/';
    let username;
    let uuid;
    let categoryId;
    let savePage;

    $scope.savePageNum = function (page){
        console.log(savePage);
        savePage = page;
    }

    if ($localStorage.myMarketGuestCartId){
        uuid = $localStorage.myMarketGuestCartId;
    }

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


    $scope.addProductOnBin = function (id){
        $http({
            url: cartContextPath + "v1/cart/" + uuid + "/add/" + id,
            method: 'GET',
            params: {
                email: username
            }
        })
          .then(function (response){
          })
    };

    $scope.loadCategoriesStore = function() {
        $http.get(contextPath + "/categories").then(function (response) {
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

    $scope.loadCategoriesStore();

    $scope.loadProducts();

})
