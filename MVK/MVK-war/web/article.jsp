<%-- 
    Document   : article
    Created on : 24.02.2015, 15:47:39
    Author     : ANABEL
--%>

<%@page import="controller.BeanFactory"%>
<%@page import="controller.ManageBeanStocksLocal"%>
<%@page import="controller.ManageBeanStocks"%>
<%@page import="model.StocksArticle"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="user" class="model.AppUser" scope="request"/>
<jsp:useBean id="household" class="model.Household" scope="request"/>
<jsp:useBean id="place" class="model.Place" scope="request"/>
<jsp:useBean id="article" class="model.StocksArticle" scope="request"/>

<head>
    <title>Artikelliste</title>
</head>
<body>

<table>
    <tr>
        <th>Menge</th>
        <th>Datum</th>
        <th>Kommentar</th>
        <th>+</th>
        <th>-</th>
        <th>--></th>
    </tr>


<% 
ManageBeanStocksLocal manageBeanStocks = BeanFactory.getManageBeanStocks();

StocksArticle a = manageBeanStocks.findStocksArticle(request.getParameter("StocksArticleID"));
%>   
<c:forEach items="<%= a.getStocksUnitList() %>" var="zeile">
    <tr>
        <td>${zeile.quantity}</td>
        <td>${zeile.mdd}</td>
        <td>${zeile.commentSUnit}</td>
        <td>
            <input type="submit" value="+" action="/MVK-war/ControllerServlet?step=raiseQuantity"/>
        </td>
        <td>
            <input type="submit" value="-" action="/MVK-war/ControllerServlet?step=reduceQuantity"/>
        </td>
        <td>
            <button class="btn" type="submit" action="/MVK-war/ControllerServlet?step=moveUnit">
                <i class="icon-arrow-right"></i>
            </button>
                
        </td>
    </tr>
</c:forEach>
        
 
</table>
           

