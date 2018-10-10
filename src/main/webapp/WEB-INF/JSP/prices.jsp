<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
	<head>
		<c:import url="/WEB-INF/JSP/head.jsp">
			<c:param name="title" value="Prices" />
		</c:import>
	</head>
	<body>
		<c:import url="/WEB-INF/JSP/menu.jsp" />
		<h1>Prices</h1>
		
		<ul>
			<c:forEach items="${prices}" var="price">
				<c:url value="/pizzas" var="url">
					<c:param name="price" value="${price}" />
				</c:url>
				<li><a href="${url}">${price}</a></li>
			</c:forEach>
		</ul>
		<c:if test="${not empty pizzas}">
			<h2>${prijs}</h2>
			<ul>
				<c:forEach items="${pizzas}" var="pizza">
					<spring:url var="url" value="/pizzas/{id}">
						<spring:param name="id" value="${pizza.id}" />
					</spring:url>
					<li><a href="${url}"><c:out value="${pizza.naam}" /></a></li>
				</c:forEach>
			</ul>
		</c:if>
	</body>
</html>