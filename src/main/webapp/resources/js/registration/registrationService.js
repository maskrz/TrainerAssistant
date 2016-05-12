angular.module('registrationService', [ 'resourceProvider' ])
.factory('Registration', [ 'Resource', function(Resource) {

	return Resource('registration/:path/:requestedValue', {
		path : '@path',
		requestedValue : '@requestedValue'
	}, {
		"register" : {
			method : 'POST',
			params : {
				path : 'register',
				requestedValue : '@requestedValue'
			}
		}
	});

} ]);