<%@ page contentType='text/html; charset=ISO-8859-1' pageEncoding='ISO-8859-1' session='false' %>

<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib prefix='spring' uri='http://www.springframework.org/tags' %>
<%@ taglib prefix='form' uri='http://www.springframework.org/tags/form' %>
<!DOCTYPE html>
<html>
	<head>
		<c:import url='/WEB-INF/JSP/head.jsp'>
			<c:param name="title" value="From to price" />
		</c:import>
	</head>
	<body>
		<c:import url='/WEB-INF/JSP/menu.jsp' />
		<h1>From to price</h1>
		<c:url value='/pizzas' var='url' />
		<form:form action='${url}' modelAttribute='fromToPriceForm' method='get'>
			<form:label path='from'>From:<form:errors path='from' /></form:label>
			<form:input path='from' autofocus='autofocus' />
			<form:label path='to'>To:<form:errors path='to' /></form:label>
			<form:input path='to' />
			<input type='submit' value='Search'><form:errors cssClass='fout' />
		</form:form>
		<c:if test='${not empty pizzas}'>
			<ul>
			<c:forEach items='${pizzas}' var='pizza'>
				<spring:url var='url' value='/pizzas/{id}'>
					<spring:param name='id' value='${pizza.id}' />
				</spring:url>
				<li><a href='${url}'><c:out value='${pizza.name}' /></a> (${pizza.price})</li>
			</c:forEach>
			</ul>
		</c:if>
	</body>
</html>