<%-- 
    Document   : household
    Created on : 24.02.2015, 15:46:18
    Author     : ANABEL
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="template.jsp"></jsp:include>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="controller.BeanFactory"%>
<%@page import="controller.ManageBeanStocksLocal"%>
<%@page import="controller.ManageBeanStocks"%>

<jsp:useBean id="user" class="model.AppUser" scope="request"/>
<jsp:useBean id="household" class="model.Household" scope="request"/>
<jsp:useBean id="place" class="model.Place" scope="request"/>
<jsp:useBean id="article" class="model.StocksArticle" scope="request"/>
<jsp:useBean id="manageBeanStocks" class="controller.ManageBeanStocks" scope ="request"/>

<div class="container">
    <div class="row" >
        <div class="col-md-12">
            <h1>Hallo <jsp:getProperty name="user" property="name"/> </h1>
            <h3>Haushalt: <jsp:getProperty name="household" property="name"/> </h3>
            <p>Hier wird dein Haushalt verwaltet und die einzelnen Lagerorte angezeigt.</p>
            <br>
        </div>
    </div>
    <div class="row" >
        <div class="col-md-4">
            <%-- Formular, um Household zu teilen--%>
            <form method ="post" action="/MVK-war/ControllerServlet?step=shareHousehold">
                <table>
                    <tr>
                        <td>Teile deinen Haushalt mit anderen!</td>
                    </tr>
                    <tr>
                        <td><input type ="email" name="email" placeholder="Email"/></td>
                    </tr>
                    <tr><td><input type="submit" value ="Haushalt teilen"/></td></tr>
                </table>
            </form>

        </div>
        <div class="col-md-4">
            <%-- Formular, um Namen des Haushalts ändern--%>
            <form method ="post" action="/MVK-war/ControllerServlet?step=changeHousehold">
                <table>
                    <tr>
                        <td>Ändere den Namen deines Haushalts</td>
                    </tr>
                    <tr>
                        <td><input type ="text" name="name" placeholder="Name"/></td>
                    </tr>
                    <tr><td><input type="submit" value ="Name ändern"/></td></tr>
                </table>
            </form>   
        </div>    
        <div class="col-md-4">
            <%-- Formular, um Haushalt zu löschen--%>
            <form method ="post" action="/MVK-war/ControllerServlet?step=removeHousehold">
                <table>
                    <tr>
                        <td>Lösche deinen Haushalt endgültig!</td>
                    </tr>
                    <tr><td><input type="submit" value ="Haushalt löschen"/></td></tr>
                </table>
            </form>   
        </div>    
    </div>

    <div class="row">       
        <div class="hhcontainer">
            <%-- Lagerplätze nebeneinander --%>
            <c:forEach items="${household.getPlaceList()}" var="place" >
                <div class="hhdiv">
                    <h2 class="hbox"> ${place.name}</h2> 

                    <c:url var="idFromPlace" value="/ControllerServlet?step=createStocksArticle">
                        <c:param name="id" value="${place.placeID}"/>
                    </c:url>

                    <%-- Artikeltabelle untehalb des Lagerortes--%>

                    <table class="bgbox">
                        <c:forEach items="${place.getStocksArticleList()}" var="article" >
                            <%-- Unterscheidung, ob Artikel abgelaufen, in den nächsten 3 Tagen abläuft 
                                 oder noch haltbar ist --%>
                            <c:choose>
                                <%-- Artikel abgelaufen --%>
                                <c:when test="${manageBeanStocks.proofeMdd(article) == -1}">    
                                    <tr>
                                        <%-- Gesamtmenge des Artikels --%>
                                        <td style="width: 30px;">
                                            ${manageBeanStocks.sumUpQuantity(article)}
                                        </td>
                                        <%-- Artikel --%>
                                        <td>
                                            <strong style ="color: red;" class="pointer" id ="${article.getStocksArticleID()}" onclick="overlay('${article.getStocksArticleID()}', 'display')">${article.getName()}</strong> 
                                        </td>
                                        <%-- Button zum Löschen des Artikels --%>
                                        <td>
                                            <c:url var="removeStocksArticle" value="/ControllerServlet?step=removeStocksArticle">
                                                <c:param name="stocksArticleID" value="${article.getStocksArticleID()}"/>
                                            </c:url>
                                            <form method="post" action="${removeStocksArticle}">
                                                <input type="submit" value="X" />
                                            </form> 
                                        </td>
                                    </tr>
                                </c:when>
                                <%--Artikel läuft innerhalb der nächsten drei Tagen ab --%>
                                <c:when test="${manageBeanStocks.proofeMdd(article) == 0}">
                                    <tr>
                                        <td style="width: 30px;">
                                            ${manageBeanStocks.sumUpQuantity(article)}
                                        </td>
                                        <td>
                                            <strong style ="color: orange;" class="pointer" id ="${article.getStocksArticleID()}" onclick="overlay('${article.getStocksArticleID()}', 'display')">${article.getName()}</strong> 
                                        </td>
                                        <td>
                                            <c:url var="removeStocksArticle" value="/ControllerServlet?step=removeStocksArticle">
                                                <c:param name="stocksArticleID" value="${article.getStocksArticleID()}"/>
                                            </c:url>
                                            <form method="post" action="${removeStocksArticle}">
                                                <input type="submit" value="X" />
                                            </form> 
                                        </td>                                        
                                    </tr>
                                </c:when>
                                <%-- Artikel ist haltbar --%>
                                <c:otherwise>
                                    <tr>
                                        <td style="width: 30px;">
                                            ${manageBeanStocks.sumUpQuantity(article)}
                                        </td>
                                        <td>
                                            <strong  class="pointer" id ="${article.getStocksArticleID()}" onclick="overlay('${article.getStocksArticleID()}', 'display')">${article.getName()}</strong> 
                                        </td>
                                        <td>
                                            <c:url var="removeStocksArticle" value="/ControllerServlet?step=removeStocksArticle">
                                                <c:param name="stocksArticleID" value="${article.getStocksArticleID()}"/>
                                            </c:url>
                                            <form method="post" action="${removeStocksArticle}">
                                                <input type="submit" value="X" />
                                            </form> 
                                        </td>
                                    </tr>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                        <tr>
                            <%-- Button für neuen Artikel --%>
                            <td colspan ="2">   

                                <form method="post" action="ControllerServlet?step=createStocksArticle">
                                    <input type="text" name="name" placeholder="Artikel..." />
                                    <input type="hidden"
                                           value="${place.placeID}"
                                           name="id"
                                           />
                                    <input type="submit" value="Artikel hinzufügen"/>
                                </form>
                            </td>
                        </tr>
                    </table>
                </div>
            </c:forEach>

            <%--Button für neuen Lagerort --%>
            <div class="hhdiv">
                <h2 class="hbox" style="width: auto">Neuer Lagerort</h2> 
                <form method ="post" action="/MVK-war/ControllerServlet?step=createPlace">
                    <div class="hhdiv">
                        <input type ="text" name="name" placeholder="Lagerort..."/>
                        <input type="submit"
                               value ="+ Neuen Lagerort hinzufügen"/>
                    </div>
                </form>
            </div>
        </div>
    </div>


</div>

