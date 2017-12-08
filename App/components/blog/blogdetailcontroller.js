app.controller('blogdetailcontroller',['BlogPostService','$scope','$location','$rootScope','$cookieStore','$routeParams',function(BlogPostService,$scope,$location,$rootScope,$cookieStore,$routeParams)
{

    var id=$routeParams.id
    $scope.showRejectionTxt=false
$scope.isLiked=false
console.log($rootScope.currentUser)

/**
 * statement will fetch blogpost for given id
 */
    BlogPostService.getBlogPostById(id).then(function(response){
        $scope.blogPost=response.data
        console.log(response.data)
        console.log(response.status)
    },function(response){
        console.log(response.data)
        console.log(response.status)
        if(response.status==401)
            $location.path('/login')
            
    })
    
    $scope.updateApproval=function(){
        console.log($scope.blogPost)
        BlogPostService.updateApproval($scope.blogPost).then(function(response){
            $location.path('/getallblogs')
        },function(response){
            if(response.data==401)
                $location.path('/login')
            else
            $location.path('/blogpostforapproval/'+id)
        })
    
    }
    
    $scope.setValueForRejectionTxt=function(val){
        $scope.showRejectionTxt=val
    }
    
    
        
    $scope.addComment=function(){
        
    //        console.log('before assigning blogPost to blogComment.blogPost is ' + $scope.blogComment)
            $scope.blogComment.blogPost=$scope.blogPost
      //      console.log('After assigning blogPost to blogComment.blogPost is ' + $scope.blogComment)
            BlogPostService.addComment($scope.blogComment,$rootScope.currentUser.username).then(function(response){
                $scope.blogComment.commentText=''
                console.log(response.data)
                console.log(response.status)
            },function(response){
                console.log(response.data)
                console.log(response.status)//Error
            })
        }
        
    function getBlogComments(){
        BlogPostService.getBlogComments(id).then(function(response){
            console.log(response.data)
            console.log(response.status)
            $scope.blogComments=response.data //List<BlogComment>
        },function(response){
            console.log(response.data)
            console.log(response.status)
        })
    }
    
    getBlogComments()


}]);


