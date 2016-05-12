<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<script
	src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
<script
	src="http://cdnjs.cloudflare.com/ajax/libs/angular.js/1.2.16/angular-resource.min.js"></script>
<script
	src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular-resource.min.js"></script>
<script
	src="https://raw.githubusercontent.com/gdi2290/angular-md5/master/angular-md5.js"></script>
<link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">

<script
	src="<c:url value="/resources/js/login/loginController.js" />"></script>
	<script
	src="<c:url value="/resources/js/login/loginService.js" />"></script>
<script src="<c:url value="/resources/js/resourceProvider.js" />"></script>
<head>

<meta charset="utf-8">
<title>Welcome</title>
</head>
<body>
	<div ng-app="login" ng-controller="loginCtrl">

		<div class="col-md-6 col-md-offset-3">
			<h2>Register</h2>
			<form name="form" ng-submit="login(user)" role="form">
				<div>
					<label for="username">First name</label> <input type="text"
						name="firstName" id="firstName" ng-model="user.firstName" required />
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

	<script>
		
	</script>

</body>
</html>
