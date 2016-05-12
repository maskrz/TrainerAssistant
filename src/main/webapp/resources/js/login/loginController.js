angular.module('login', [ 'loginService', 'angular-md5' ])
.controller(
		'loginCtrl',
		[ '$scope', 'md5', 'Login',
				function($scope, md5, Login) {
					$scope.user = {};
					$scope.register = function(user_account) {
						$scope.results = "Please wait - processing...";
						var user = angular.copy(user_account);
						user.password = md5.createHash(user.password || '');
						Login.login(user, function(results) {
							$scope.results = results;
							console.log(results);
						});
					}
				}

		]);