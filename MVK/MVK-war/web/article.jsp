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
        <th>  Menge  </th>
        <th>  Datum  </th>
        <th>  Kommentar  </th>
        <th>  <i class="fa fa-plus-circle"></i>  </th>
        <th>  <i class="fa fa-minus-circle"></i>  </th>
    </tr>


<% 
ManageBeanStocksLocal manageBeanStocks = BeanFactory.getManageBeanStocks();

StocksArticle a = manageBeanStocks.findStocksArticle(request.getParameter("StocksArticleID"));
String myID =request.getParameter("StocksArticleID");
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
    </tr>
</c:forEach>
    
 
</table>

