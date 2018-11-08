<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Welcome</title>
    </head>

    <body>
        <h1>Salad Master-Chief App</h1>
        <div>Known recipes: </div>
        <div>${recipes}</div>
        <br><br>
        <div>
            <c:forEach var="component" items="${components}"
                 <li><c:out value="${component}"/></li>
            </c:forEach>
        </div>
    </body>
</html>