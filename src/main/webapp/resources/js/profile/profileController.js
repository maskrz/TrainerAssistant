var profile = angular.module('profile', [ 'profileService' ]).controller(
		'profileCtrl', [ '$scope', 'Profile', function($scope, Profile) {
			$scope.selectedRecords = [];
			$scope.resourceLoadedFlag = false;
			$scope.record = {
				"t1" : null,
				"t2" : null,
				"t3" : null,
				"wsr" : null,
				"date" : null
			};

			google.charts.load('current', {
				'packages' : [ 'corechart' ]
			});
			google.charts.setOnLoadCallback(resourceLoaded($scope));

			$scope.loadRecords = function() {
				$scope.results = null;
				Profile.dailyRecords(function(results) {
					$scope.results = results;
				});
			}

			$scope.toDate = function(recordDate) {
				return toDateString(recordDate);
			}

			$scope.drawChart = function() {
				console.log($scope.resourceLoadedFlag);
				drawChart($scope.selectedRecords);
			}

			$scope.handleRecordsClick = function() {
				if ($scope.selectedRecords.length == 1) {
					loadSelected($scope, $scope.selectedRecords[0]);
				} else {
					resetSelected($scope);
				}
			}

			$scope.merge = function(dailyRecord) {
				Profile.mergeDailyRecord(dailyRecord, function(result) {
					$scope.updateRecords(result);
				});
			}
			
			$scope.updateRecords = function(newRecord) {
				var d1 = javaToDate(newRecord.dailyRecordId.recordDate);
				var add = true;
				for (var i = 0; i < $scope.results.length; i++) {
					var d2 = javaToDate($scope.results[i].dailyRecordId.recordDate);
					if (d1.getTime() === d2.getTime()) {
						$scope.results[i].t1 = newRecord.t1;
						$scope.results[i].t2 = newRecord.t2;
						$scope.results[i].t3 = newRecord.t3;
						$scope.results[i].wsr = newRecord.wsr;
						add = false;
						break;
					} else if (d2 > d1) {
						$scope.results.splice(i, 0, newRecord);
						add = false;
						break;
					}
				}
				if (add) {
					$scope.results.push(newRecord);
					$scope.selectedRecords = [];
					$scope.selectedRecords.push(newRecord);
				}			
			}
			
			$scope.calculateWSR = function() {
				$scope.record.wsr = calcWSR($scope.record.t1, $scope.record.t2, $scope.record.t3);
			}
		} ]);

// Callback that creates and populates a data table,
// instantiates the pie chart, passes in the data and
// draws it.
function drawChart(selectedRecords) {
	// Create the data table.
	var data = new google.visualization.DataTable();
	data.addColumn('string', 'X');
	data.addColumn('number', 'T1');
	data.addColumn('number', 'T2');
	data.addColumn('number', 'T3');
	data.addColumn('number', 'WSR');
	var rows = [];
	for (var i = 0; i < selectedRecords.length; i++) {
		var record = selectedRecords[i];
		rows.push([ toDateString(record.dailyRecordId.recordDate), record.t1,
				record.t2, record.t3, record.wsr ]);
	}
	data.addRows(rows);

	// Set chart options
	var options = {
		hAxis : {
			title : 'Data'
		},
		vAxis : {
			title : 'Uderzenia/min'
		},
		colors : [ 'red', 'green', 'blue', 'black' ],
		crosshair : {
			color : '#000',
			trigger : 'selection'
		},
		interpolateNulls : true,
		height: 400
	};

	// Instantiate and draw our chart, passing in some options.
	var chart = new google.visualization.LineChart(document
			.getElementById('chart_div'));
	chart.draw(data, options);
}

function resourceLoaded($scope) {
	$scope.resourceLoadedFlag = true;
}

function toDateString(recordDate) {
	return handleDateNumber(recordDate.dayOfMonth) + "/"
			+ handleDateNumber(recordDate.monthValue) + "/" + recordDate.year;
}

function handleDateNumber(number) {
	if (number < 10) {
		return "0" + number;
	} else {
		return number;
	}
}

function loadSelected($scope, selectedRecord) {
	$scope.record.t1 = selectedRecord.t1;
	$scope.record.t2 = selectedRecord.t2;
	$scope.record.t3 = selectedRecord.t3;
	$scope.record.wsr = selectedRecord.wsr;
	$scope.record.date = toDateString(selectedRecord.dailyRecordId.recordDate);
}

function calculateWSR(t1, t2, t3) {
	
}

function resetSelected($scope) {
	$scope.record.t1 = null;
	$scope.record.t2 = null;
	$scope.record.t3 = null;
	$scope.record.wsr = null;
	$scope.record.date = null;
}

function javaToDate(javaDate) {
	return new Date(javaDate.year, javaDate.monthValue - 1, javaDate.dayOfMonth);
}

function calcWSR(t1, t2, t3) {
	if (t1 && t2 && t3) {
		return (100 * (t2 - t3) / (t2 - t1)).toFixed(2);
	} else {
		return 0;
	}
}

profile.directive('datepicker', function() {
	return {
		require : 'ngModel',
		link : function(scope, el, attr, ngModel) {
			$(el).datepicker({
				dateFormat: 'dd/mm/yy',
				onSelect : function(dateText) {
					scope.$apply(function() {
						ngModel.$setViewValue(dateText);
					});
				}
			});
		}
	};
});