<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${ slideshow.title }</title>
<link rel="stylesheet" href="/css/reset.css">
<link rel="stylesheet" href="/css/slideshow.css">
</head>
<body>
	<spring:url value="/slideshows" htmlEscape="true"
		var="slideshowsBaseUrl" />
	<div class="container">
		<div class="slideshow">
			<h1 class="slideshow__title">${ slideshow.title }</h1>

			<svg xmlns="http://www.w3.org/2000/svg" version="1.1"
				class="slideshow__slide"
				viewport="0 0 ${ slideshow.width } ${ slideshow.height }"
				xmlns:xlink="http://www.w3.org/1999/xlink">
                
                <c:forEach items="${ slide.slideElements }" var="e">
                		<spring:eval
						expression="T(com.aiconoa.slideshow.helper.SlideElementRenderer).renderHTML(e)"
						var="html" />                
                		${ html }
                </c:forEach>
            </svg>
		</div>
	</div>

	<div class="slideshow__hud">
		<c:if test="${ index le 0}">
		<a class="slideshow__hud__link slideshow__hud__link--hidden" 
		   href="${slideshowsBaseUrl}/${slideshow.id}/slide/${index - 1}">previous</a>
		</c:if>

		<c:if test="${ index gt 0}">
		<a class="slideshow__hud__link" 
		   href="${slideshowsBaseUrl}/${slideshow.id}/slide/${index - 1}">previous</a>
		</c:if>
		
		<c:if test="${ index ge slideshow.size() -1 }">
		<a class="slideshow__hud__link slideshow__hud__link--hidden" 
		   href="${slideshowsBaseUrl}/${slideshow.id}/slide/${index + 1}">next</a>
		</c:if>
		
		<c:if test="${ index lt slideshow.size() -1 }">
			<a class="slideshow__hud__link" 
			   href="${slideshowsBaseUrl}/${slideshow.id}/slide/${index + 1}">next</a>
		</c:if>

		<p class="slideshow__hud__index">${ index + 1 }/${ slideshow.size() }</p>

	</div>
</body>
</html>