var bs = angular.module("bs", []);

bs.controller("RegistrationController", ['$scope', '$http', function($scope, $http) {
	
	$scope.registerUser = function(){		
		
		var userObj = {
				userName : $scope.inputEmail,
				password : $scope.password,
				firstName : $scope.firstName,
				lastName : $scope.lastName
		};	
		var res = $http.post('/blog/account/user/create', userObj);
		res.success(function(data, status, headers, config) {
			$scope.message = data;
			$scope.inputEmail='';
			$scope.password='';
			$scope.firstName='';
			$scope.lastName='';
			window.location = '/blog/account/user/loginSuccess';
		});
		res.error(function(data, status, headers, config) {
			alert( "failure message: " + JSON.stringify({data: data}));
		});		
		// Making the fields empty
		//

	};
}]);


bs.controller("loginController", ['$scope', '$http', function($scope, $http) {
	
	$scope.login = function(){		
		
		var userObj = {
				userName : $scope.inputEmail,
				password : $scope.password
		};	
		var res = $http.post('/blog/account/user/login', userObj);
		res.success(function(data, status, headers, config) {
			$scope.message = data;
			$scope.inputEmail='';
			$scope.password='';
			window.location = '/blog/account/user/loginSuccess';
		});
		res.error(function(data, status, headers, config) {
			alert( "failure message: " + JSON.stringify({data: data}));
		});		
		// Making the fields empty
		//

	};
}]);

bs.directive("oneBlog", function() {
	return {
		restrict : 'E',
		templateUrl : '/blog/account/user/login'
	}
});