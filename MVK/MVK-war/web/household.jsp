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
                                    <strong class="pointer" id ="${article.getStocksArticleID()}" onclick="overlay('${article.getStocksArticleID()}', 'display','${article.getStocksUnitList()}')">${article.getName()}</strong> 
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

    <script>
        function overlay(myId, mode, list) {
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
                    
                    table = document.createElement('table-responsive');
                    
                    //Überschriftszeile
                        tr = document.createElement('tr');
                            th = document.createElement('th');
                            menge= document.createTextNode("Menge");
                            th.appendChild(menge);
                            tr.appendChild(th);

                            th = document.createElement('th');
                            datum= document.createTextNode("Datum");
                            th.appendChild(datum);
                            tr.appendChild(th);
                            
                            th = document.createElement('th');
                            kommentar= document.createTextNode("Kommentar");
                            th.appendChild(kommentar);
                            tr.appendChild(th);
                            
                            th = document.createElement('th');
                            plus= document.createTextNode("+");
                            th.appendChild(plus);
                            tr.appendChild(th);
                            
                            th = document.createElement('th');
                            minus= document.createTextNode("-");
                            th.appendChild(minus);
                            tr.appendChild(th);
                            
                            th = document.createElement('th');
                            verschieben= document.createTextNode("->");
                            th.appendChild(verschieben);
                            tr.appendChild(th);

                        table.appendChild(tr);
                    
                    //Tabellen-Inhalt
                    
                    //Schleife ...
                    /*
                    for (var eintrag in list){
                        tr = document.createElement(tr);
                        
                        ajax = new XMLHttpRequest();
                        
                            td = document.createElement(td);
                            menge = eintrag.getQuantity();
                            td.appendChild(menge);
                            tr.appendChild(td);
                            
                            td = document.createElement(td);
                            datum = eintrag.getMdd();
                            td.appendChild(datum);
                            tr.appendChild(td);
                            
                            td = document.createElement(td);
                            kommentar = eintrag.getCommentSUnit();
                            td.appendChild(kommentar);
                            tr.appendChild(td);
                            
                            td = document.createElement(td);
                            leer = document.createTextNode('+++');
                            td.appendChild(leer);
                            tr.appendChild(td);
                            
                            td = document.createElement(td);
                            leer2 = document.createTextNode('---');
                            td.appendChild(leer2);
                            tr.appendChild(td);
                            
                            td = document.createElement(td);
                            leer3 = document.createTextNode('-->');
                            td.appendChild(leer3);
                            tr.appendChild(td);
                            
                        
                        table.appendChild(tr);
                    }
                    
                    
                    */
                    //
                     lightBox.appendChild(table);
                    //Untere Zeile
                    table2 = document.createElement('table');
                    
                    tr = document.createElement('tr');
                    
                    form = document.createElement('form');
                    form.setAttribute('method', 'post');

                    form.setAttribute('action', '/MVK-war/ControllerServlet?step=createStocksUnit&StocksArticleID='+myId);
                    
                            td = document.createElement('td');
                            textfeldm = document.createElement('input');
                            textfeldm.setAttribute('type', 'number');
                            textfeldm.setAttribute('min', '1');
                            textfeldm.setAttribute('name', 'Menge');
                            textfeldm.setAttribute('placeholder', 'Menge...');
                            td.appendChild(textfeldm);
                            tr.appendChild(td);

                            td = document.createElement('td');
                            textfeldd = document.createElement('input');
                            textfeldd.setAttribute('type', 'date');
                            textfeldd.setAttribute('name', 'Datum');
                            textfeldd.setAttribute('placeholder', 'MHD...');
                            td.appendChild(textfeldd);
                            tr.appendChild(td);
                            
                            td = document.createElement('td');
                            textfeldc = document.createElement('input');
                            textfeldc.setAttribute('type', 'text');
                            textfeldc.setAttribute('name', 'Kommentar');
                            textfeldc.setAttribute('placeholder', 'Kommentar...');
                            td.appendChild(textfeldc);
                            tr.appendChild(td);
                            
                            td = document.createElement('td');
                            button = document.createElement('input');
                            button.setAttribute('type', 'submit');
                            button.setAttribute('value', 'hinzufügen');
                            td.appendChild(button);
                            tr.appendChild(td);
                                                        
                           form.appendChild(tr);
                           
                           table2.appendChild(form);
                           lightBox.appendChild(table2);

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

