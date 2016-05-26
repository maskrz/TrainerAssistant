angular.module('profileService', [ 'resourceProvider' ])
.factory('Profile', [ 'Resource', function(Resource) {

	return Resource('profile/:path', {
		path : '@path',
	}, {
		"dailyRecords" : {
			method : 'GET',
			params : {
				path : 'dailyRecords',
			},
			isArray: true
		},
		"mergeDailyRecord" : {
			method : 'POST',
			params : {
				path : 'merge',
				requestedValue : '@requestedValue'
			}
		}
	});

} ]);