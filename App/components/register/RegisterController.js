app.controller('RegisterController', ['$scope', 'RegisterService', '$location',  function ($scope, RegisterService, $location) {

    $scope.user = {};
    $scope.register = function () {

        console.log("used data" + $scope.user);
        console.log($scope.user.firstName);
        RegisterService.register($scope.user).then(function (response) {

            alert('THANK YOU.......please wait for the approval..');
            $location.path('/login')
        }, function (error) {
            console.log(error);
            alert('Account already present.... Please select different username or email.');
            $location.path('/register')

        })
    }

    

}]);




