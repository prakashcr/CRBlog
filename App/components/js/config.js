app.constant('REST_URI', 'http://localhost:8085/Backendproject/');


app.config(function ($routeProvider) {
    $routeProvider

        .when('/home', {
            templateUrl: "/components/home/home.html",
            controller: 'homecontroller'
        })
        .when('/contactus', {
            templateUrl: "/components/home/contactus.html",

        })
        .when('/profilepic', {
            templateUrl: "/components/friend/user.html",
            controller: "FriendController",
            controllerAs: 'FrndCtrl',

        })
        .when('/login', {
            templateUrl: "/components/login/login.html",
            controller: "LoginController",
        })
        .when('/register', {
            templateUrl: "/components/register/register.html",
            controller: "RegisterController",

        })

        .when('/edituser', {
            templateUrl: "/components/register/editprofile.html",
            controller: "registercontroller1",
        })

        .when('/postjobs', {
            templateUrl: "/components/job/jobs.html",
            controller: "JobController",
        })
        .when('/getalljobs', {
            templateUrl: "/components/job/jobslist.html",
            controller: "jobcontroller1",
        })
        .when('/getjob/:id', {
            templateUrl: "/components/job/job.html",
            controller: "JobController",
        })
        .when('/post', {
            templateUrl: "/components/blog/post.html",
            controller: "BlogPostController",
        })
        .when('/getallblogs', {
            templateUrl: "/components/blog/listofblog.html",
            controller: "blogpostcontroller1",
        })
        .when('/blogpostforapproval/:id', {
            templateUrl: '/components/blog/blogpostapproval.html',
            controller: 'blogdetailcontroller',
        })
        .when('/blogdetail/:id', {
            templateUrl: '/components/blog/blogdetail.html',
            controller: 'blogdetailcontroller',
        })
        .when('/approvalstatus/:id', {
            templateUrl: '/components/blog/blogpoststatus.html',
            controller: 'blogdetailcontroller',
        })
        .when('/suggestedusers', {
            templateUrl: '/components/friend/suggestedusers.html',
            controller: 'FriendController1' //From C to V
        })
        .when('/pendingrequests', {
            templateUrl: '/components/friend/pendingrequest.html',
            controller: 'FriendController1'//From C to V
        })
        .when('/getfriends', {
            templateUrl: '/components/friend/listoffriends.html',
            controller: 'FriendController1'//From C to V
        })
        .when('/chat',
        {
            templateUrl: '/components/chat/chat.html',
            controller: 'ChatController',
            controllerAs: 'ChatCtrl',
        })

        .otherwise({
            templateUrl: "/components/home/home.html"
        })
});
/*
app.run(function($rootScope,$cookieStore,LoginService,$location){

    
	if ($rootScope.currentUser == undefined)
    $rootScope.currentUser = $cookieStore.get("currentUser")


    $rootScope.logout=function(){
        LoginService.logout().then(function(response){
            $rootScope.message="LOGGED OUT SUCCESSFULLY..."
          delete $rootScope.currentUser;
           $cookieStore.remove('currentUser')
         $location.path('/login')
        },function(response){
            
            $rootScope.message = response.data.message
        })
    }

});  
    
*/


