<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style>
            <%@include file="/switch_on_off.css" %>
            <%@include file="/content_tables.css" %>
         </style>
        <title>Welcome</title>
    </head>

    <body>
        <h1>Salad Master-Chief App</h1>
            <div class="onoffswitch">
                <input type="checkbox" name="onoffswitch" class="onoffswitch-checkbox" id="myonoffswitch" checked>
                <label class="onoffswitch-label" for="myonoffswitch">
                    <span class="onoffswitch-inner"></span>
                    <span class="onoffswitch-switch"></span>
                </label>
            </div>
        <br>
        <div>
            <table class="contentTable">
                <thead>
                    <tr>
                       <th>Name</th><th>Description</th><th>Portion weight</th><th>Portion calories</th><th>Vegetarian</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="recipe" items="${recipes}">
                        <tr>
                            <td>${recipe.name}</td>
                            <td>${recipe.description}</td>
                            <td>${recipe.weight}</td>
                            <td>${recipe.calories}</td>
                            <td>${recipe.vegan}</td>
                        </tr>
                    </c:forEach>
                 </tbody>
            </table>
         </div>
    </body>
</html>