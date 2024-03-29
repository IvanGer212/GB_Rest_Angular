angular.module('market').controller('userController',function ($scope, $http, $location, $localStorage) {
    const contextPath = 'http://localhost:8080/app/api/v1';

    // let savePage;
    //
    // $scope.savePageNum = function (page){
    //     //console.log(savePage);
    //     savePage = page;
    // }

    $scope.loadUsers = function (pageIndex) {
        console.log("PageIndex = " + pageIndex);
        $http({
            url: contextPath + "/users",
            method: 'GET',
            params:{
                p: pageIndex
            }
        }).then(function (response) {
            $scope.UserPage = response.data;
            $scope.generatePagesList(response.data.totalPages);
        })
    }

    $scope.deleteUser = function (userId) {
        $http.delete(contextPath + '/users/' + userId)
            .then(function (response) {
                $scope.loadUsers();
            });
    }

    $scope.createUser = function (userName, surname, password, email, phone, roles) {
        $http({
            url: contextPath + "/users",
            method: 'POST',
            data: $scope.User
        }).then(function (response) {
            $scope.loadUsers();
        });
    }

    $scope.generatePagesList = function (totalPage){
        $scope.pagesList = [];
        for (let i = 0; i < totalPage; i++){
            $scope.pagesList.push(i+1);
        }
    }

    $scope.loadUsers();
})