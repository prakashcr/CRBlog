app.controller('BlogPostController', ['BlogPostService', '$scope', '$location', '$rootScope', '$cookieStore', function (BlogPostService, $scope, $location, $rootScope, $cookieStore) {



    $scope.saveBlogPost = function () {

        console.log('content reached to blogpost controller');
        console.log($scope.blogPost)
        console.log($rootScope.currentUser)

        $rootScope.currentUser = $cookieStore.get('currentUser');
        BlogPostService.saveBlogPost($scope.blogPost, $rootScope.currentUser.username).then(function (response) {
            alert('blog saved successfully');
            alert("please wait for approval");
            $location.path('/home')
        }, function (response) {
            if (response.status == 401)
                $location.path('/login')
            else
                $location.path('/saveblogpost')
        })
    }
}]);

