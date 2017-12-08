app.controller('LoginController', ['$scope', 'LoginService', '$location', '$rootScope', '$cookieStore', function ($scope, LoginService, $location, $rootScope, $cookieStore) {
    $scope.user = {};
    $scope.login = function () {
        LoginService.login($scope.user).then(function (response) {
            $scope.success = "LOGGED IN SUCCESSFULLY........"
            $rootScope.currentUser = response.data
            $cookieStore.put('currentUser', response.data)
            $location.path('/home')
        }, function (response) {
            $scope.response = response.status.data
            console.log(response.status);

            $scope.error = response.data
            if ($scope.error.code == 404)
                $scope.loginFail = "Invalid username/password"
            alert('INVALID USER PLEASE REGISTER FIRST...');

            $location.path('/login')
        })
    }
    
    $rootScope.currentUser = {};
    $rootScope.logout = function () {
        LoginService.logout($scope.currentUser).then(function (response) {
            $scope.success = "LOGGED OUT SUCCESSFULLY........"
            console.log("logout");
            $cookieStore.remove('currentUser');
            $rootScope.currentUser='';
            $location.path('/login');
        })
    }
}]);
