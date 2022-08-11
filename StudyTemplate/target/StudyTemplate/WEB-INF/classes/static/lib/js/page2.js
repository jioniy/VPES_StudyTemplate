var ngApp = angular.module('Page2AngularApp', ['ui.bootstrap']);

ngApp.controller('Page2Controller', function($rootScope, $scope, $http, $location) {
	
	/* Function - Member Data Load */
	$scope.memberDataLoading = function() {
		$http({
			url : "member/jdbc/memberlist",
			method : "GET",
			headers: {
				'Content-type': 'application/json;charset=utf-8',
				'ANGULAR' : true
			},
		}).then(function(response) {
			// Handle request success
			$scope.memberList = response.data.RESULT_DATA.MEMBERLIST;
			console.log($scope.memberList);
		}, function(response) {
			// Handle expired session
			if (response.status === 401) {
				$window.location.href = 'login';
			} else if (response.status === 405) {
				$window.location.href = 'login';
			}
		});
	};
	
});