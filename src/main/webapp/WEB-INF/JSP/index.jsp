<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="nl">
	<head>
		<c:import url="/WEB-INF/JSP/head.jsp">
			<c:param name="title" value="Pizza Luigi" />
		</c:import>
	</head>
	<body>
		<c:import url="/WEB-INF/JSP/menu.jsp" />
		<h1>Welcome to Pizza Luigi</h1>
		
		<img src="<c:url value="/images/pizza.jpg" />" alt="pizza" class="fullwidth" />
		
		<h2>${welcomeMessage}</h2>
		
		<p>Your unlucky number for today: ${unluckyNumber}</p>
		
		<h2>The manager</h2>
		<dl>
			<dt>Name</dt><dd>${manager.name}</dd>
			<dt>Number of children</dt><dd>${manager.numberOfChildren}</dd>
			<dt>Married</dt><dd>${manager.married ? 'Yes' : 'No'}</dd>
			<dt>Adres</dt>
			<dd>${manager.address.street} ${manager.address.number}<br />
				${manager.address.postalCode} ${manager.address.communality}</dd>
		</dl>
	</body>
</html>