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
<h1>All films</h1>

<spring:url value="/films" htmlEscape="true" var="filmsBaseUrl" />
<ul>
<c:forEach items="${ films }" var="f">
	<li><a href="${filmsBaseUrl}/${f.id}">${f.title}</a></li>
</c:forEach>
</ul>
</body>
</html>