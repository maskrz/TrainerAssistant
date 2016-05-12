angular.module('loginService', [ 'resourceProvider' ])
.factory('Login', [ 'Resource', function(Resource) {

	return Resource('login/:path/:requestedValue', {
		path : '@path',
		requestedValue : '@requestedValue'
	}, {
		"login" : {
			method : 'POST',
			params : {
				path : 'login',
				requestedValue : '@requestedValue'
			}
		}
	});

} ]);