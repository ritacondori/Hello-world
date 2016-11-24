<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>All slideshows</title>
<link rel="stylesheet" href="/css/reset.css">
<link rel="stylesheet" href="/css/slideshow-list.css">
</head>
<body>
<spring:url value="/slideshows" htmlEscape="true" var="slideshowsBaseUrl" />
<h1>Les slideshows disponibles</h1>
<form action="/logout" method="post">
	<input type="hidden"  name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <input type="submit" value="Sign Out"/>
</form>
<div class="slideshow-gallery">
<c:forEach items="${ slideshows }" var="s" varStatus="myVarStatus">
	<a href="${slideshowsBaseUrl}/${s.id}/slide/0"  class="slideshow-gallery__item">
        <img class="slideshow-gallery__item__image" src="/img/${ slideshowThumbnails[myVarStatus.index] }" alt="" />
        <p class="slideshow-gallery__item__title">${ s.title}</p>
    </a>
</c:forEach>  
</div>
</html>