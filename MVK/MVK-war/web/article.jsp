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
            <form method="post" action="/MVK-war/ControllerServlet?step=raiseQuantity">
                <input type="submit" value="+" />
            </form>
        </td>
        <td>
            <form method="post" action="/MVK-war/ControllerServlet?step=reduceQuantity">
                <input type="submit" value="-" />
            </form>
        </td>
        <td>            
            <form method ="post" action="/MVK-war/ControllerServlet?step=moveUnit" class="dropdown">
                <button class="btn btn-default dropdown-toggle" type="submit" id="menu1" data-toggle="dropdown">verschieben nach<span class="caret"></span></button>
                <ul class="dropdown-menu" role="menu" aria-labelledby="menu1">
                    <li role="presentation"><a role="menuitem" tabindex="-1" href="#">hierhin</a></li>
                    <li role="presentation"><a role="menuitem" tabindex="-1" href="#">dahin</a></li>
                    <li role="presentation"><a role="menuitem" tabindex="-1" href="#">dorthin</a></li>
                    <li role="presentation"><a role="menuitem" tabindex="-1" href="#">woanders hin</a></li>
                </ul>
            </form>
        </td>
    </tr>
</c:forEach>
        
 
</table>
           

