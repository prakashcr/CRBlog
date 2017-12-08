app.controller('jobcontroller1', ['JobService', '$location', '$scope','$rootScope','$cookieStore','$routeParams', function (JobService, $location, $scope,$rootScope,$cookieStore,$routeParams) 
{
   
function listOfJobs(){
    JobService.listOfJobs().then(function(response){
        console.log(response.data)
        console.log(response.status)
        $scope.jobs = response;      // <tr ng-repeat="job in jobs | filter:searchText">
    
    },function(response){
        console.log(response.status)
        if(response.status==401)
        $location.path('/login')
    })
    }
    listOfJobs()
    

    

    
}]);