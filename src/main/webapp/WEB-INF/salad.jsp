<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style>
            <%@include file="/content_tables.css" %>
         </style>
        <title>Salad</title>
    </head>

    <body>
        <h1 align=center>${saladSummary.name}</h1>
        <h2 align=center>(portions x${saladSummary.portions})</h2>
        <form method="GET">
           <input type="submit" value="Filter products: " onchange="submit()"/>
           <input type="text" name="from"/> - <input type="text" name="to"/> calories
           <input type="hidden" name="action" value="displayProductsInCaloriesBounds"/>
        </form>
        <form method="GET">
              <input type="submit" value="Display all" onchange="submit()"/>
              <input type="hidden" name="action" value="displaySaladDetails" />
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
                       <th>Product</th>
                       <th>Category</th>
                       <th>Preparation method</th>
                       <th>Calorific value</th>
                       <th>Weight</th>
                       <th>
                            <a href="${sortProductsByCaloriesAsc}">
                                <img src="${pageContext.request.contextPath}/img/ascending.png" alt="ASC">
                            </a>
                            Calories
                            <a href="${sortProductsByCaloriesDesc}">
                                <img src="${pageContext.request.contextPath}/img/descending.png" alt="DESC">
                            </a>
                        </th>
                        <th>Vegetarian</th>
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
                    <td colspan=4 alignment=left> TOTAL: </td>
                    <td>${saladSummary.totalWeight}</td>
                    <td>${saladSummary.totalCalories}</td>
                    <td>${saladSummary.vegan}</td>
                 </tfoot>
            </table>
         </div>
    </body>
</html>