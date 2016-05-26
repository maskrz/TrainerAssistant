
<div ng-app="profile" ng-controller="profileCtrl"
	ng-init="loadRecords()" class="ng-cloak main-container">

	<div class="row">
		<div class="col-xs-2 left-column">
			<select name="records" multiple ng-model="selectedRecords"
				ng-options="toDate(record.dailyRecordId.recordDate) for record in results"
				ng-click="handleRecordsClick()" class="select-records">
			</select>
		</div>
		<div class="col-xs-2 right-column">

			<div class="input-group">
				<span class="input-group-addon input-label" id="label-t1">T1:</span>
				<input type="number" ng-model="record.t1"
					class="form-control input-value" aria-describedby="label-t1"
					ng-change="calculateWSR()">
			</div>

			<div class="input-group">
				<span class="input-group-addon input-label" id="label-t2">T2:</span>
				<input type="number" ng-model="record.t2"
					class="form-control input-value" aria-describedby="label-t2"
					ng-change="calculateWSR()">
			</div>

			<div class="input-group">
				<span class="input-group-addon input-label" id="label-t3">T3:</span>
				<input type="number" ng-model="record.t3"
					class="form-control input-value" aria-describedby="label-t3"
					ng-change="calculateWSR()">
			</div>

			<div class="input-group">
				<span class="input-group-addon input-label" id="label-wsr">WSR:</span>
				<input type="text" ng-model="record.wsr" disabled
					class="form-control input-value" aria-describedby="label-wsr">
			</div>

			<div class="input-group">
				<span class="input-group-addon input-label" id="label-date">Data:</span>
				<input type="text" datepicker ng-model="record.date"
					class="form-control input-value" aria-describedby="label-date">
			</div>

			<button ng-click="merge(record)" ng-disabled="!record.date"
				class="btn btn-primary">Zapisz</button>
			<button ng-click="drawChart()"
				ng-disabled="!resourceLoadedFlag || selectedRecords.length < 2"
				class="btn btn-primary">Rysuj wykres</button>
		</div>

	<div class="col-xs-8 chart-column">
		<div id="chart_div"></div>
	</div>
	</div>


	


</div>

