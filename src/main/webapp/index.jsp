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
<link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">

<script
	src="<c:url value="/resources/js/registration/mainController.js" />"></script>
<script src="<c:url value="/resources/js/resourceProvider.js" />"></script>
<head>

<meta charset="utf-8">
<title>Welcome</title>
</head>
<body>
	<div ng-app="main" ng-controller="mainCtrl">
	
	Hello <span ng-bind="name"></span>!
	<a ng-href="registration">Register!</a> (link, reload!)<br />

	</div>

	<script>
		
	</script>

</body>
</html>
