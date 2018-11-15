<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:set var="language"
value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
scope="session"/>
<fmt:setLocale value="${language}" scope="session"/>
<fmt:setBundle basename="interface"/>

<html lang=${language}>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style>
            <%@include file="/styles/content_tables.css" %>
         </style>
        <title>Salad</title>
    </head>

    <body>
        <h1 align=center>${saladSummary.name}</h1>
        <h2 align=center>(<fmt:message key="salad.portions"/> x ${saladSummary.portions})</h2>
        <form method="GET">
           <input type="submit" value="<fmt:message key="salad.button.filter"/>" onchange="submit()"/>
           <input type="text" name="from"/> - <input type="text" name="to"/>
                <fmt:message key="salad.button.filter.calories"/>
           <input type="hidden" name="action" value="displayProductsInCaloriesBounds"/>
        </form>
        <form method="GET">
              <input type="submit" value="<fmt:message key="salad.button.all"/>" onchange="submit()"/>
              <input type="hidden" name="action" value="displaySaladDetails"/>
        </form>
        <br><br>
        <div>
            <c:url var="sortProductsByCaloriesDesc" value="">
                  <c:param name="action" value="sortProductsByCalories"/>
                  <c:param name="descending" value="true"/>
            </c:url>
            <c:url var="sortProductsByCaloriesAsc" value="">
                  <c:param name="action" value="sortProductsByCalories"/>
                  <c:param name="descending" value="false"/>
            </c:url>
            <table class="contentTable">
                <thead>
                    <tr>
                       <th><fmt:message key="salad.table.product"/></th>
                       <th><fmt:message key="salad.table.category"/></th>
                       <th><fmt:message key="salad.table.preparation"/></th>
                       <th><fmt:message key="salad.table.calorificValue"/></th>
                       <th><fmt:message key="salad.table.weight"/></th>
                       <th>
                            <a href="${sortProductsByCaloriesAsc}">
                                <img src="${pageContext.request.contextPath}/img/ascending.png" alt="ASC">
                            </a><fmt:message key="salad.table.calories"/>
                            <a href="${sortProductsByCaloriesDesc}">
                                <img src="${pageContext.request.contextPath}/img/descending.png" alt="DESC">
                            </a>
                        </th>
                        <th><fmt:message key="salad.table.vegan"/></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="component" items="${components}">
                        <tr>
                            <td>${component.name}</td>
                            <td>${component.category}</td>
                            <td>${component.preparationMethod}</td>
                            <td>${component.calorificValue}</td>
                            <td>${component.weight}</td>
                            <td>${component.calories}</td>
                            <td>${component.vegan}</td>
                        </tr>
                    </c:forEach>
                 </tbody>
                 <tfoot>
                    <td colspan=4 alignment=left><fmt:message key="salad.table.total"/></td>
                    <td>${saladSummary.totalWeight}</td>
                    <td>${saladSummary.totalCalories}</td>
                    <td>${saladSummary.vegan}</td>
                 </tfoot>
            </table>
         </div>
    </body>
</html>