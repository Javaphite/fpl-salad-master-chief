<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language"
value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
scope="session"/>
<fmt:setLocale value="${language}" scope="session"/>
<fmt:setBundle basename="interface"/>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style>
            <%@include file="/styles/content_tables.css" %>
         </style>
        <title>Recipes</title>
    </head>

    <body>
        <form>
           <select id="language" name="language" onchange="submit()">
               <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
               <option value="uk" ${language == 'uk' ? 'selected' : ''}>Українська</option>
           </select>
        </form>
        <h1 align=center><fmt:message key="recipes.header"/></h1>
        <form method="GET">
           <input type="submit" value="<fmt:message key="recipes.button.vegan"/>" onchange="submit()"/>
           <input type="hidden" name="action" value="displayVeganRecipes"/>
        </form>
        <form method="GET">
              <input type="submit" value="<fmt:message key="recipes.button.all"/>" onchange="submit()"/>
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
                       <th><fmt:message key="recipes.table.name"/></th>
                       <th><fmt:message key="recipes.table.description"/></th>
                       <th><fmt:message key="recipes.table.weight"/></th>
                       <th>
                            <a href="${sortRecipesByCaloriesAsc}">
                                <img src="${pageContext.request.contextPath}/img/ascending.png" alt="ASC">
                            </a><fmt:message key="recipes.table.calories"/>
                            <a href="${sortRecipesByCaloriesDesc}">
                                <img src="${pageContext.request.contextPath}/img/descending.png" alt="DESC">
                            </a>
                        </th>
                       <th><fmt:message key="recipes.table.vegan"/></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="recipe" items="${recipes}">
                        <tr>
                            <td>
                                <form method="GET" value="./">
                                    <input type="text" name="portions"/>
                                    <input type="hidden" name="action" value="createSalad"/>
                                    <input type="hidden" name="recipeId" value="${recipe.id}"/>
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