<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>
		<c:import url="/WEB-INF/JSP/head.jsp">
			<c:param name="title" value="Headers" />
		</c:import>
	</head>
	<body>
		<c:import url="/WEB-INF/JSP/menu.jsp" />
		Your browser is running on a
		${onWindows ? "Windows" : "non-Windows"} operating system.
	</body>
</html>