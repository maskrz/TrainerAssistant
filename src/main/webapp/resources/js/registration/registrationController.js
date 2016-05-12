angular.module('registration', [ 'registrationService', 'angular-md5' ])
.controller(
		'registrationCtrl',
		[ '$scope', 'md5', 'Registration',
				function($scope, md5, Registration) {
					$scope.user = {};
					$scope.register = function(user_account) {
						$scope.results = "Please wait - processing...";
						var user = angular.copy(user_account);
						user.password = md5.createHash(user.password || '');
						Registration.register(user, function(results) {
							$scope.results = results;
							console.log(results);
						});
					}
				}

		]);