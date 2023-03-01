angular.module('market').controller('orderController',function ($scope, $http, $location, $localStorage) {
    const contextPath = 'http://localhost:8080/app/api/v1';
    //const cartContextPath = 'http://localhost:8081/app/api/';


    $scope.loadOrders = function (){
        $http.get(contextPath + "/order").then(function (response){
            console.log(response.data);
            $scope.OrderList = response.data;
        })
    }

    $scope.loadOrderItems = function (orderId){
        $http.get(contextPath + "/order/" + orderId).then(function (response){
            $scope.OrderItemsList = response.data;
        })
    }

    $scope.loadOrders();
})
