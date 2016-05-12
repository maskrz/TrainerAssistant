var app = angular.module('main', [ 'resourceProvider' ]);
app
		.controller(
				'mainCtrl',
				function($scope, MainService) {
					$scope.name = 'Whirled';
					
				}

		).factory('MainService', [ 'Resource', function(Resource) {

			return Resource(':path/:requestedValue', {
				path : '@path',
				requestedValue : '@requestedValue'
			}, {
				"helloWorld" : {
					method : 'PUT',
					params : {
						path : 'hello'
					},
					isArray : true
				},
				"save" : {
					method : 'PUT',
					params : {
						path : 'save',
						requestedValue : '@requestedValue'
					}
				},
				"add" : {
					method : 'PUT',
					params : {
						path : 'add',
						requestedValue : '@requestedValue'
					}
				},
				"send" : {
					method : 'PUT',
					params : {
						path : 'send'
					}
				}
			});

		} ]);