<%-- 
    Document   : article
    Created on : 24.02.2015, 15:47:39
    Author     : ANABEL
--%>

<%@page import="controller.BeanFactory"%>
<%@page import="controller.ManageBeanStocksLocal"%>
<%@page import="controller.ManageBeanStocks"%>
<%@page import="model.StocksArticle"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="user" class="model.AppUser" scope="request"/>
<jsp:useBean id="household" class="model.Household" scope="request"/>
<jsp:useBean id="place" class="model.Place" scope="request"/>
<jsp:useBean id="article" class="model.StocksArticle" scope="request"/>

<head>
    <title>Artikelliste</title>
</head>
<body>

<table  align="center" valign="middle">
    <colgroup width="100" span="5"></colgroup>
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
SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
%>   
<c:forEach items="<%= a.getStocksUnitList() %>" var="zeile">
    <tr>
        
        <c:url var="raiseQuantity" value="/ControllerServlet?step=raiseQuantity">
            <c:param name="id" value="${zeile.stocksUnitID}"/>
        </c:url>
        <c:url var="reduceQuantity" value="/ControllerServlet?step=reduceQuantity">
            <c:param name="id" value="${zeile.stocksUnitID}"/>
        </c:url>
        <td>${zeile.quantity}</td>
        <td> <fmt:formatDate value="${zeile.mdd}" pattern ="dd.MM.yyyy" /> </td>
        <td>${zeile.commentSUnit}</td>
        <td>
            <form method="post" action="${raiseQuantity}">
                <input type="submit" value="+" />
            </form>
        </td>
        <td>
            <form method="post" action="${reduceQuantity}">
                <input type="submit" value="-" />
            </form>
        </td>
    </tr>
</c:forEach>
  
</table>

