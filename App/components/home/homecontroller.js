app.controller('homecontroller',['BlogPostService','$scope','$location','$rootScope','$cookieStore',function(BlogPostService,$scope,$location,$rootScope,$cookieStore)
{
function getApprovalStatus(){
    $rootScope.currentUser = $cookieStore.get('currentUser');
    console.log($rootScope.currentUser)
    BlogPostService.getApprovalStatus($scope.blogPost,$rootScope.currentUser.username).then(function(response){
        console.log(response.data)
        console.log(response.status)
        $rootScope.approvalStatus=response.data
        console.log($rootScope.approvalStatus.length)
    },function(response){
        console.log(response.status)
        if(response.status==401)
        $location.path('/login')
    })
}
getApprovalStatus()
}]);