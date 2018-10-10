<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="nl">
	<head>
		<c:import url="/WEB-INF/JSP/head.jsp">
			<c:param name="title" value="Pizza's" />
		</c:import>
	</head>
	<body>
		<c:import url="/WEB-INF/JSP/menu.jsp" />
		<h1>Pizza's</h1>
		
		<ul class="zebra">
			<c:forEach var="pizza" items="${pizzas}">
				<li>
					${pizza.id}: <c:out value="${pizza.name}" /> ${pizza.price} &euro;
					<c:choose>
						<c:when test="${pizza.spicy}">spicy</c:when>
						<c:otherwise>not spicy</c:otherwise>
					</c:choose>
					<spring:url value="/pizzas/{id}" var="url">
						<spring:param name="id" value="${pizza.id}" />
					</spring:url>
					<a href="${url}">Detail</a>
				</li>
			</c:forEach>
		</ul>
	</body>
</html>