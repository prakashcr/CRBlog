var Jobmodule = angular.module('Jobmodule', [])
Jobmodule.service('JobService', ['$http', 'REST_URI', function ($http, REST_URI) {

    this.saveJob = function (job) {
        console.log("content reached service");
        return $http.post(REST_URI + "/savejob", job)
    }
    this.listOfJobs = function (job) {
        console.log("all jobs");
        return $http.get(REST_URI + "/getalljobs", job).then(
            function (response) {
                console.log(response.data);
                console.log(response.status);

                return response.data;
            }
        );
    }
    this.getJob = function (id,job) {
        console.log("job id")
        return $http.get
            (REST_URI + '/getjob/' + id,job)

    }



}]);