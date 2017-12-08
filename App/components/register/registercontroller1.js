app.controller('registercontroller1', ['RegisterService', '$location', '$scope', '$rootScope', '$cookieStore', function (RegisterService, $location, $scope, $rootScope, $cookieStore) {
    $scope.user = {};

    function getUser() {
        $rootScope.currentUser = $cookieStore.get('currentUser');
        RegisterService.getUser($scope.user, $rootScope.currentUser.username).then(function (response) {
            console.log('content reached to getuser service');
            $scope.user = response.data
        }, function (response) {
            if (response.status == 401)
                $location.path('/login')
        })
    }
    getUser()

    $scope.updateUser= function () {
        RegisterService.updateUser($scope.user).then(function (response) {
            alert('updated the detail');
            $location.path('/home')
        }, function (response) {
            if (response.status == 401)
                $location.path('/login')
            $scope.error = response.data
            $location.path('/edituser')
        })
    }
}])