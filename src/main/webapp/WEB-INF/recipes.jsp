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
        <h1 align=center>Salad Master-Chief App</h1>
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
            <c:url var="sortRecipesByCaloriesDesc" value="">
                  <c:param name="action" value="sortRecipesByCalories"/>
                  <c:param name="descending" value="true"/>
            </c:url>
            <c:url var="sortRecipesByCaloriesAsc" value="">
                  <c:param name="action" value="sortRecipesByCalories"/>
                  <c:param name="descending" value="false"/>
            </c:url>
            <table class="contentTable">
                <thead>
                    <tr>
                       <th>#</th>
                       <th>Name</th>
                       <th>Description</th>
                       <th>Portion weight</th>
                       <th>
                            <a href="${sortRecipesByCaloriesAsc}">
                                <img src="${pageContext.request.contextPath}/img/ascending.png" alt="ASC">
                            </a>
                              Portion calories
                            <a href="${sortRecipesByCaloriesDesc}">
                                <img src="${pageContext.request.contextPath}/img/descending.png" alt="DESC">
                            </a>
                        </th>
                       <th>Vegetarian</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="recipe" items="${recipes}">
                        <tr>
                            <td>
                                <form method="POST" value="">
                                    <input type="text" name="portions" width=3/>
                                    <input type="hidden" name="action" value="createSalad"/>
                                    <input type="hidden" name="recipe" value="${recipe.name}"/>
                                    <input type="image"  src="${pageContext.request.contextPath}/img/ok.png" alt="CREATE"/>
                                </form>
                             </td>
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