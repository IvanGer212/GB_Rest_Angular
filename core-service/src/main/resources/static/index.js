(function () {
    angular
        .module('market', ['ngRoute', 'ngStorage'])
        .config(config)
        .run(run);

    function config($routeProvider) {
        console.log(123);
        $routeProvider
            .when('/', {
                templateUrl: 'welcome/welcome.html',
                controller: 'welcomeController'
            })
            .when('/store', {
                templateUrl: 'store/store.html',
                controller: 'storeController'
            })
            .when('/cart', {
                templateUrl: 'cart/cart.html',
                controller: 'cartController'
            })
            .when('/users',{
                templateUrl: 'users/users.html',
                controller: 'userController'
            })
            .otherwise({
                redirectTo: '/'
            });
    }

    function run($rootScope, $http, $localStorage) {
        console.log(234);
        if ($localStorage.myMarketUser) {
            try {
                let jwt = $localStorage.myMarketUser.token;
                let payload = JSON.parse(atob(jwt.split('.')[1]));
                let currentTime = parseInt(new Date().getTime() / 1000);
                if (currentTime > payload.exp) {
                    console.log("Token is expired!!!");
                    delete $localStorage.myMarketUser;
                    $http.defaults.headers.common.Authorization = '';
                }
            } catch (e) {
            }

            $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.myMarketUser.token;
        }
    }
})();

angular.module('market').controller('indexController', function ($rootScope, $scope, $http, $location, $localStorage) {
    $scope.tryToAuth = function (){
            $http.post('http://localhost:8080/auth', $scope.user)
                .then(function successCallback(response) {
                    if (response.data.token) {
                        $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                        $localStorage.myMarketUser = {email: $scope.user.email, username: response.data.username, token: response.data.token};
                        $scope.user.email = null;
                        $scope.user.password = null;

                        $location.path('/')

                    }
                })
        };

    $rootScope.tryToLogout = function () {
        $scope.clearUser();
        $scope.user = null;
        $location.path('/');
    };

    $scope.clearUser = function () {
        delete $localStorage.myMarketUser;
        $http.defaults.headers.common.Authorization = '';
    };

    $rootScope.isUserLoggedIn = function () {
        if ($localStorage.myMarketUser) {
            return true;
        } else {
            return false;
        }
    };
});











// (function() {
//     angular
//         .module('market', ['ngRoute', 'ngStorage'])
//         .config(config())
//         .run(run());
//
//     function config($routeProvider) {
//         $routeProvider
//             .when('/', {
//                 templateUrl: 'welcome/welcome.html',
//                 controller: 'welcomeController'
//             })
//             .when('/store', {
//                 templateUrl: 'store/store.html',
//                 controller: 'storeController'
//             })
//             .when('/cart', {
//                 templateUrl: 'cart/cart.html',
//                 controller: 'cartController'
//             })
//             .when('/users', {
//                 templateUrl: 'users/users.html',
//                 controller: 'userController'
//             })
//             .otherwise({
//                 redirectTo: '/'
//             });
//     }
//
//     function run($rootScope, $http, $localStorage) {
//         if ($localStorage.myMarketUser) {
//             try {
//                 let jwt = $localStorage.myMarketUser.token;
//                 let payload = JSON.parse(atob(jwt.split('.')[1]));
//                 console.log(payload);
//                 console.log(payload.username);
//                 let curentTime = parseInt(new Date().getTime() / 1000);
//                 if (curentTime > payload.exp) {
//                     console.log("Token is expired!!!");
//                     delete $localStorage.myMarketUser;
//                     $http.defaults.headers.common.Authorization = '';
//                 }
//             } catch (e) {
//             }
//
//             $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.myMarketUser.token;
//         }
//     }
// })();
//
// angular.module('market').controller('indexController', function ($rootScope, $scope, $http, $location, $localStorage){
//
//
//         $scope.tryToAuth = function (){
//             $http.post('http://localhost:8080/auth', $scope.user)
//                 .then(function successCallback(response) {
//                     if (response.data.token) {
//                         $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
//                         $localStorage.myMarketUser = {email: $scope.user.email, username: response.data.username, token: response.data.token};
//                         $scope.user.email = null;
//                         $scope.user.password = null;
//
//                         $location.path('/')
//
//                     }
//                 })
//         };
//
//         $scope.tryToLogout = function (){
//             $scope.clearUser();
//             $scope.user = null;
//             $location.path('/')
//         }
//
//         $scope.clearUser = function (){
//             delete $localStorage.myMarketUser;
//             $http.defaults.headers.common.Authorization = '';
//         };
//
//         $scope.isUserLoggedIn = function (){
//             if ($localStorage.myMarketUser){
//                 return true;
//             } else {
//                 return false;
//             }
//         };
//
//
// });