<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>All films</title>
</head>
	<body>
	<c:if test="${param.error}">
		<div>Invalid username and password.</div>
	</c:if>
	<c:if test="${param.logout}">
		<div>You have been logged out.</div>
	</c:if>
	<form action="/login" method="post">
	    <div><label> User Name : <input type="text" name="username"/> </label></div>
	    <div><label> Password: <input type="password" name="password"/> </label></div>
	    <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
	    <div><input type="submit" value="Sign In"/></div>
	</form>
</body>
</html>