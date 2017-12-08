var Registermodule = angular.module('Registermodule', [])

Registermodule.service('RegisterService', ['$http', 'REST_URI', function ($http, REST_URI) {
    this.register = function (user) {
        console.log("content reached service");

        return $http.post(REST_URI + '/registeruser', user).then(
            function (response) {
                console.log(response.data);

            }
        );
    }
    
    this.getUser = function(user,username) {
        console.log('edit profile service');
        return $http.post(REST_URI + "/getusers/" +username,user)
            
    }
        
    
    this.updateUser = function (user) {
        console.log("update profile service");
        return $http.post(REST_URI + '/updateprofile',user)    

}
 
}
]);