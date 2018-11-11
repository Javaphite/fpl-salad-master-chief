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
        <form method="GET">
           <input type="submit" value="Vegan recipes only" onchange="submit()"/>
           <input type="hidden" name="action" value="displayVeganRecipes"/>
        </form>
        <form method="GET">
              <input type="submit" value="All known recipes" onchange="submit()"/>
              <input type="hidden" name="action" value="displayKnownRecipes" />
        </form>
        <br><br>
        <div>
            <table class="contentTable">
                <thead>
                    <tr>
                       <th>#</th>
                       <th>Name</th>
                       <th>Description</th>
                       <th>Portion weight</th>
                       <th>Portion calories</th>
                       <th>Vegetarian</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="recipe" items="${recipes}">
                        <c:url var="createSalad" value="">
                            <c:param name="action" value="createSalad"/>
                            <c:param name="recipe" value="${recipe.name}"/>
                        </c:url>
                        <tr>
                            <td><a href="${createSalad}">LINK</a></td>
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