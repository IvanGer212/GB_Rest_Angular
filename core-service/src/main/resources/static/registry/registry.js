angular.module('market').controller('registryController',function ($scope, $http, $location, $localStorage) {
    const contextPath = 'http://localhost:8080/app/api/v1';
    let msg = '';

    $scope.registryUser = function (userName, surname, password, passwordRepeat, email, phone, ) {
        if ($scope.User.password == $scope.passwordRepeat){
            console.log('check OK')
            $scope.User.roles = 'user';
            console.log($scope.User);
            $http({
                url: contextPath + "/users/registry",
                method: 'POST',
                data: $scope.User
            }).then(function (response) {
                alert('Вы успешно зарегистрированы!');
                $location.path('/');
                //msg = 'Вы успешно зарегистрированы!';
            });
        } else {
            alert('Не возможно зарегистрироваться! Пароли не совпадают!')
            //msg = 'Не возможно зарегистрироваться! Пароли не совпадают!';
        }

    }
})
