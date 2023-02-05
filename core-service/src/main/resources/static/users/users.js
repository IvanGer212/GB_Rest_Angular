angular.module('market').controller('userController',function ($scope, $http, $location, $localStorage) {
    const contextPath = 'http://localhost:8080/app/api/v1';

    $scope.loadUsers = function () {
        $http.get(contextPath + "/users").then(function (response) {
            console.log(response.data);
            $scope.UserList = response.data;
        })
    }

    $scope.deleteUser = function (userId) {
        console.log('delete user')
        $http.delete(contextPath + '/users/' + userId)
            .then(function (response) {
                $scope.loadUsers();
            });
    }

    $scope.createUser = function (userName, surname, password, email, phone, roles) {
        console.log($scope.User);
        $http({
            url: contextPath + "/users",
            method: 'POST',
            data: $scope.User
        }).then(function (response) {
            $scope.loadUsers();
        });
    }

    // $scope.loadRoles = function (){
    //     $http.get('http://localhost:8080/app/api/v1/users/roles')
    //         .then(function (response){
    //             $scope.Roles = response.data.content;
    //         });
    // };

    //$scope.loadRoles();
    $scope.loadUsers();
})