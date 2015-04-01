<%-- 
Artikeldetailseite, die als Popup-Fenster bei Klick auf einen Artikel erscheint.
Beinhaltet eine tabellarische Übersicht der Vorratseinheiten.
--%>

<%@page import="controller.BeanFactory"%>
<%@page import="controller.ManageBeanStocksLocal"%>
<%@page import="controller.ManageBeanStocks"%>
<%@page import="model.StocksArticle"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
        <%--Überschriftszeile der Tabelle --%> 
        <tr>
            <th>  Menge  </th>
            <th>  Datum  </th>
            <th>  Kommentar  </th>
            <th>  <i class="fa fa-plus-circle"></i>  </th>
            <th>  <i class="fa fa-minus-circle"></i>  </th>
        </tr>
        <%--kurzer Java Code, um richtigen StocksArticle zu erhalten --%>
        <%
            ManageBeanStocksLocal manageBeanStocks = BeanFactory.getManageBeanStocks();
            StocksArticle a = manageBeanStocks.findStocksArticle(request.getParameter("StocksArticleID"));
        %>   
        
        <%--Schleife über alle Elemente der Liste mit StocksUnit eines StocksArticles--%>
        <c:forEach items="<%= a.getStocksUnitList()%>" var="zeile">
            <tr>
                <%--Übergabe relevanter Parameter --%>
                <c:url var="raiseQuantity" value="/ControllerServlet?step=raiseQuantity">
                    <c:param name="id" value="${zeile.stocksUnitID}"/>
                </c:url>
                <c:url var="reduceQuantity" value="/ControllerServlet?step=reduceQuantity">
                    <c:param name="id" value="${zeile.stocksUnitID}"/>
                </c:url>
                <%--Ausgabe der Vorratseinheiten --%>
                <td>${zeile.quantity}</td>
                <td> <fmt:formatDate value="${zeile.mdd}" pattern ="dd.MM.yyyy" /> </td>
                <td>${zeile.commentSUnit}</td>
                <%--Button zum Erhöhen der Menge --%>
                <td>
                    <form method="post" action="${raiseQuantity}">
                        <input type="submit" value="+" />
                    </form>
                </td>
                <%--Button zum reduzieren der Menge--%> 
                <td>
                    <form method="post" action="${reduceQuantity}">
                        <input type="submit" value="-" />
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>

