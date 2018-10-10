<%@ page contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header>
	<nav>
		<ul>
			<li><a href="<c:url value="/" />">Welcome</a></li>
			<li><a href="<c:url value="/pizzas" />">Pizza's</a></li>
			<li><a href="<c:url value="/pizzas/fromtoprice" />">From to price</a></li>
			<li><a href="<c:url value="/pizzas/prices" />">Prices</a></li>
			<li><a href="<c:url value="/pizzas/add" />">Add</a></li>
			<li><a href="<c:url value="/basket" />">Basket</a></li>
			<li><a href="<c:url value="/identification" />">Identification</a></li>
			<li><a href="<c:url value="/headers" />">Headers</a></li>
		</ul>
	</nav>
</header>