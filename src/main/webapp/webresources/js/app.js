/**
 * Simple Angular JS based REST Client for User application. 
 */

var app = angular.module('userOrigination', [ 'ngMessages', 'ngMaterial' ]);

app.service('pageService', function($http, $mdToast) {
	
	return {
		getPreloadFn : getPreload,
		getRegisteredUsersFn : getRegisteredUsers,
		registerUserFn : registerUser,
		deleteUserFn : deleteUser,
		showToastFn : showToast
	}
	
	function getPreload() {
		return $http.get('preload').then(handlePreloadData);
		
		function handlePreloadData(preloadResult) {
			return preloadResult.data;
		}
	}
	
	function getRegisteredUsers() {
		return $http.get('view').then(handlePreloadData);
		
		function handlePreloadData(preloadResult) {
			return preloadResult.data;
		}
	}
	
	function registerUser(userData, successCallback, errorCallback) {
		$http.post('manage', userData)
		.then(successCallback, errorCallback);
	}

	function deleteUser(userId, successCallback, errorCallback) {
		$http.post('delete', userId)
			.then(successCallback, errorCallback);
	}
	
	function showToast(message) {
		 $mdToast.show($mdToast.simple().textContent(message).hideDelay(3000));
	}
	
});


app.controller('regController', function($scope, $http, $mdToast, $mdDialog, pageService) {
	
	var vm = this;
	
	vm.register = function() {
		
		function checkColorEnabled(colorList) {
			var enabledColors = [];
			angular.forEach(colorList, function(value, key) {
				if(value.enabled) {
					enabledColors.push(value.colorCode);
					value.enabled = false;
				}
			});
			return enabledColors;
		}
		
		var newUserRequest = {
			userName : vm.user.username,
			creditBalance : vm.user.credit,
			qualification : vm.user.school,
			gender : vm.user.gend,
			favouriteColor : checkColorEnabled(vm.favoriteColorType)
		}
		
		var success = function(response) {
			
			if (response.data.success) {
				pageService.showToastFn('Registration success!');
				pageService.getRegisteredUsersFn().then(function(data) {
					vm.registeredUsers = data;
				});
				delete vm.user;
				
			} else {
				pageService.showToastFn('Provided data is invalid, registration failed!');
			}
		}
		
		var failed = function() {
			pageService.showToastFn('Registration failed!');
		}
		
		pageService.registerUserFn(
				newUserRequest,
				success, 
				failed
		);
		
	}
	
	vm.deleteuser = function(userId) {
		
		var dialog = $mdDialog.confirm()
			.title("Delete confirmation")
			.textContent("Are you sure to delete user?")
			.ok("Yes")
			.cancel("No");
		
		$mdDialog.show(dialog).then(function() {
			var success = function() {
				pageService.getRegisteredUsersFn().then(function(data) {
					vm.registeredUsers = data;
				});
				pageService.showToastFn("User deleted!");
			}
			
			var failed = function() {
				pageService.showToastFn("Delete failed! :'(");
			}
			
			pageService.deleteUserFn(userId, success, failed);
		}, function() {
			console.debug("User deletion has been rejected.");
		});
	}
	
	vm.preload = function() {
		pageService.getPreloadFn().then(function(data) {
			vm.favoriteColorType = data.favoriteColorType;
			vm.qualificationType = data.qualificationType;
		});
	}
	
	vm.preload();
	
});