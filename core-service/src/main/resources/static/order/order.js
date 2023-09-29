angular.module('market').controller('orderController',function ($scope, $http, $location, $localStorage) {
    const contextPath = 'http://localhost:8080/app/api/v1';
    //const cartContextPath = 'http://localhost:8081/app/api/';

    let savePage;

    $scope.savePageNum = function (page){
        console.log(savePage);
        savePage = page;
    }


    $scope.loadOrders = function (pageIndex){
        $http({
            url: contextPath + "/order",
            method: 'GET',
            params: {
                p: pageIndex
            }
        })
            .then(function (response){
                console.log(response.data);
                $scope.OrderPage = response.data;
                $scope.generatePagesList($scope.OrderPage.totalPages);
            })

    }

    $scope.loadOrderItems = function (orderId){
        $http.get(contextPath + "/order/" + orderId).then(function (response){
            $scope.OrderItemsList = response.data;
        })
    }

    $scope.generatePagesList = function (totalPage){
        $scope.pagesList = [];
     for (let i = 0; i < totalPage; i++){
           $scope.pagesList.push(i+1);
        }
    }

    $scope.loadOrders();
})
