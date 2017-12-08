app.controller('FriendController', ['FriendService', '$location', '$rootScope', '$cookieStore', '$scope', '$http', '$routeParams', 'REST_URI',
	function (FriendService, $location, $rootScope, $cookieStore, $scope, $http, $routeParams, REST_URI) {
		var me = this;
		me.friends = {};
		me.friendEntity = {};
		me.users = {};
		me.event = '';
		me.myFriends = {};
		me.id = '';
		me.friends = null;
		me.users = null;
		$scope.defaultImage = REST_URI + '/resources/images/default.png';
		console.log("Friends Controller Invoked");
		me.currentUser = $cookieStore.get('currentUser');
		me.imageUrl = REST_URI + '/resources/images/' + me.currentUser.username + '.jpg';
		me.imgPath = REST_URI + '/resources/images/';


		me.uploadFile = function () {

			console.log('file is ');
			console.dir($scope.myFile);
			FriendService.uploadFileToUrl($scope.myFile, me.currentUser.username);
			me.imageUrl = REST_URI + '/resources/images/' + me.currentUser.username + '.jpg';

		};

		if ($routeParams.id != null || $routeParams != 0 || $routeParams != '0') {

		}



	}]);