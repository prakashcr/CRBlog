var FriendModule = angular.module('FriendModule', []);

FriendModule.directive('fileModel', ['$parse', function ($parse) {
    return {
        restrict: 'A',
        link: function (scope, element, attrs) {
            var model = $parse(attrs.fileModel);
            var modelSetter = model.assign;

            element.bind('change', function () {
                scope.$apply(function () {
                    modelSetter(scope, element[0].files[0]);
                });
            });
        }
    };
}]);

FriendModule.service('FriendService', ['$http', '$location', '$timeout', 'REST_URI', function ($http, $location, $timeout, REST_URI) {

    this.uploadFileToUrl = function (file, username) {
        console.log("Friends Service Invoked");
        var fd = new FormData();
        fd.append('file', file);

        $http({
            method: 'POST',
            url: REST_URI + '/image/upload/' + username, // The URL to Post.
            headers: { 'Content-Type': undefined }, // Set the Content-Type to undefined always.
            data: fd,
            transformRequest: function (data, headersGetterFunction) {
                return data;
            }
        }).then(
            function (response) {
                $location.path('/user');
            }, function (error) {
                alert(error);
            }
            );

    }


}]);