<%-- 
    Document   : household
    Created on : 24.02.2015, 15:46:18
    Author     : ANABEL
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="template.jsp"></jsp:include>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="user" class="model.AppUser" scope="request"/>
<jsp:useBean id="household" class="model.Household" scope="request"/>
<jsp:useBean id="place" class="model.Place" scope="request"/>
<jsp:useBean id="article" class="model.StocksArticle" scope="request"/>

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
            <form method ="post" action="/MVK-war/ControllerServlet?step=deleteHousehold">
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
                            <tr>
                                <td>
                                    <strong class="pointer" id ="${article.getStocksArticleID()}" onclick="overlay('${article.getStocksArticleID()}', 'display')">${article.getName()}</strong> 
                                </td>
                            </tr>
                        </c:forEach>
                        <tr>
                            <td>    <%-- Button für neuen Artikel --%>

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
                <h2 class="hbox">Neuer Lagerort</h2> 
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

    <script>
        function overlay(myId, mode) {
            if (mode === 'display') {
                if (document.getElementById("overlay") === null) {
                    div = document.createElement("div");
                    div.setAttribute('id', 'overlay');
                    div.setAttribute('className', 'overlayBG');
                    div.setAttribute('class', 'overlayBG');

                    lightBox = document.createElement('div');
                    lightBox.setAttribute('id', 'lightBox');

                    span = document.createElement('span');
                    span.setAttribute('class', 'pointer');
                    span.setAttribute('className', 'pointer');
                    span.setAttribute('onclick', 'overlay(\'none\')');
                    text = document.createTextNode('Schließen');
                    span.appendChild(text);
                    lightBox.appendChild(span);

                    head = document.createElement('h2');
                    head.setAttribute('class', 'pointer');
                    text = document.createTextNode(document.getElementById(myId).innerHTML);
                    head.appendChild(text);
                    lightBox.appendChild(head);
                    
                    //tabelle
                    
                    table = document.createElement('table');
                    
                    tr = document.createElement('tr');
                    
                    td = document.createElement('td');
                    input1= document.createTextNode("test1");
                    input2= document.createTextNode("test2");
                    td.appendChild(input1);
                    tr.appendChild(td);
                    td.appendChild(input2);
                    tr.appendChild(td);
                    table.appendChild(tr);
                    
                    //tabelle
                    
                    form = document.createElement('form');
                    form.setAttribute('method', 'post');
                    form.setAttribute('action', '/MVK-war/ControllerServlet?step=createStocksArticle');

                    textfeld = document.createElement('input');
                    textfeld.setAttribute('type', 'text');
                    textfeld.setAttribute('name', 'name');

                    button = document.createElement('input');
                    button.setAttribute('type', 'submit');
                    button.setAttribute('value', 'add');

                    form.appendChild(textfeld);
                    form.appendChild(button);
                    lightBox.appendChild(form);
                    
                    lightBox.appendChild(table);

                    document.getElementsByTagName("body")[0].appendChild(div);
                    document.getElementsByTagName("body")[0].appendChild(lightBox);
                }
            } else {
                document.getElementsByTagName("body")[0].removeChild(document.getElementById("overlay"));
                document.getElementsByTagName("body")[0].removeChild(document.getElementById("lightBox"));
            }
        }
    </script>
</div>

