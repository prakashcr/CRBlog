app.controller('blogpostcontroller1',['BlogPostService','$scope','$location','$rootScope','$cookieStore',function(BlogPostService,$scope,$location,$rootScope,$cookieStore)
{
    function listOfBlogsWaitingForApproval(){
        BlogPostService.listOfBlogsWaitingForApproval().then(function(response){
            
            console.log(response.data)
            console.log(response.status)
            $scope.blogPostWaitingForApproval=response.data
        },function(response){
            console.log(response.data)
            console.log(response.status)
            if(response.status==401)
                $location.path('/login')
        })
    }
    

    listOfBlogsWaitingForApproval()



    
function listOfBlogsApproved(){
    BlogPostService.listOfBlogsApproved().then(function(response){
        console.log(response.data)
        console.log(response.status)
        $scope.blogPostApproved=response.data;
    },function(response){
        console.log(response.data)
        console.log(response.status)
        if(response.status==401)
        $location.path('/login')
    })
}

listOfBlogsApproved()

}]);