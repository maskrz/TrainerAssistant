<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<tiles:importAttribute name="stylesheets" />
<tiles:importAttribute name="javascripts" />
<c:forEach var="css" items="${stylesheets}">
	<c:url var="cssHref" value="${css}" />
	<link rel="stylesheet" type="text/css" href="${cssHref}" />
</c:forEach>
<c:forEach var="js" items="${javascripts}">
	<c:url var="jsSrc" value="${js}" />
	<script type="text/javascript" src="${jsSrc}">
		<jsp:text></jsp:text>
	</script>
</c:forEach>
<title><tiles:insertAttribute name="title" ignore="true" /></title>
</head>
<body>
	<tiles:insertAttribute name="header" />
	<tiles:insertAttribute name="body" />
</body>
</html>