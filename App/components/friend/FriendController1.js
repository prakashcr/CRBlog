app.controller('FriendController1', function ($scope, $rootScope,  $cookieStore, FriendService1, $location) {
	
	

	function listOfSuggesteduser()
	{
		$rootScope.currentUser=$cookieStore.get('currentUser');
		$scope.suggestedUser=FriendService1.suggestedUser($scope.user,$rootScope.currentUser.username).then(function(response){
			console.log(response.data)
			$scope.suggestedUser=response.data;
		},function(response){
			console.log(response.data)
			console.log(response.status)
		})
	}
	
	function pendingRequests() {
		$rootScope.currentUser=$cookieStore.get('currentUser');
		$scope.listOfPendingRequests = FriendService1.listOfPendingRequests($scope.user,$rootScope.currentUser.username).then(function (response) {
			$scope.listOfPendingRequests = response.data;
		}, function (response) {
			console.log(response.data)
			console.log(response.status)
		})
	}
	
	function listOfFriends() {
		$rootScope.currentUser=$cookieStore.get('currentUser');
		$scope.friendsList = FriendService1.friendsList($scope.user,$rootScope.currentUser.username).then(function (response) {
			$scope.friendsList = response.data;
		}, function (response) {
			console.log(response.data)
		})
	}
	/**
	 * to user class
	 */
	
	$scope.friendrequest = function (toUsername) {
		$rootScope.currentUser=$cookieStore.get('currentUser');
		FriendService1.sendFriendRequest(toUsername,$rootScope.currentUser.username).then(function (response) {
			listOfSuggesteduser();
			$location.path('/suggestedpersons')
		}, function (response) {
			console.log(response.data)
		})
	}

	$scope.updatePendingRequests = function (fromUser,status,username) {
		$rootScope.currentUser=$cookieStore.get('currentUser');
		FriendService1.updatePendingRequests(fromUser, status,$rootScope.currentUser.username,$scope.friend1).then(function (response) {
			pendingRequests();
			$location.path('/pendingrequests')
		}, function (response) {
			console.log(response.data)
			$location.path('/pendingrequests')
		})
	}
	listOfFriends()
	pendingRequests()
	listOfSuggesteduser()
})