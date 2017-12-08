app.controller('piccontroller',['$scope','$location','$rootScope','$cookieStore',function($scope,$location,$rootScope,$cookieStore)
{
 
    console.log('content reached to pic controller');
    $scope.currentUser=$cookieStore.get('currentUser');
    console.log($scope.currentUser)
 
}])
        