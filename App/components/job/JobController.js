app.controller('JobController', ['$scope', '$location', 'JobService', '$routeParams', function ($scope, $location, JobService, $routeParams) {
    var id = $routeParams.id;
    $scope.saveJob = function () {
        console.log("content reached controller");
        JobService.saveJob($scope.job).then(function (response) {
            alert("Job deatail is posted successfully");
            $location.path('/getalljobs')
        }, function (response) {
            $scope.message = response.data.message
            console.log = response.status
            if (response.status == 500)
                $location.path('/savejob')
        })
    }

    $scope.job = JobService.getJob(id,$scope.job).then(function (response) {
        console.log("JOB ID IS :" + id);
        $scope.job = response.data;
        console.log(response.data)
        console.log(response.status)
      
    },

    )

    

}]);
