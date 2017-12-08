var Loginmodule = angular.module('Loginmodule', [])

Loginmodule.service('LoginService', ['$http', 'REST_URI', function ($http, REST_URI) {
    this.login = function (user) {
        console.log("content reached service");
        return $http.post(REST_URI + '/loginuser', user)
    }
     
    this.getUser = function() {
        console.log("edit profile service");
        return $http.get(REST_URI + '/getuserdetails')
    }
    
    this.logout = function (user) {
        console.log("login service reached.......");

       return  $http.post(REST_URI + '/logout',user).then(
            function(response){
                console.log(response.data);
                }
         )
    }



}]);