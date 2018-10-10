<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang='nl'>
	<head>
		<c:import url='/WEB-INF/JSP/head.jsp'>
			<c:param name='title' value='${pizza.name}'/>
		</c:import>
	</head>
	<body>
		<c:import url='/WEB-INF/JSP/menu.jsp'/>
		<c:if test='${empty pizza}'>
			<h1>Pizza not found</h1>
		</c:if>
		<c:if test='${not empty pizza}'>
			<h1>${pizza.name}</h1>
			<dl>
				<dt>Number</dt><dd>${pizza.id}</dd>
				<dt>Name</dt><dd>${pizza.name}</dd>
				<dt>Price</dt><dd>${pizza.price}</dd>
				<dt>Spciy</dt><dd>${pizza.spicy ? 'yes' : 'no'}</dd>
				<dt>In dollar</dt><dd>${inDollar}</dd>
			</dl>
		</c:if>
	</body>
</html>