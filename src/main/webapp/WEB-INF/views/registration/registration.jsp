

<div ng-app="registration" ng-controller="registrationCtrl">

	<div class="col-md-6 col-md-offset-3">
		<h2>Register</h2>
		<form name="form" ng-submit="register(user)" role="form">
			<div>
				<label for="username">First name</label> <input type="text"
					name="firstName" id="firstName" ng-model="user.firstName" required />
			</div>
			<div>
				<label for="username">Last name</label> <input type="text"
					name="lastName" id="Text1" ng-model="user.lastName" required />
			</div>
			<div>
				<label for="username">Login</label> <input type="text" name="login"
					id="username" ng-model="user.login" required />
			</div>
			<div>
				<label for="email">Email</label> <input type="email" name="email"
					id="email" ng-model="user.email" required />
			</div>
			<div>
				<label for="password">Password</label> <input type="password"
					name="password" id="password" ng-model="user.password" required />
			</div>
			<div>
				<button type="submit">Register</button>
				<a href="/tassistant">Cancel</a>
			</div>
		</form>
	</div>
	Registration Form <span ng-bind="name"></span>! <span>{{results}}</span>

</div>

