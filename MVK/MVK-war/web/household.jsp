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
    <div class="row">
        <div class="col-lg-12">
            <h1>Hallo <jsp:getProperty name="user" property="name"/> </h1>
            <h3>Haushalt: <jsp:getProperty name="household" property="name"/> </h3>
            <p>Hier wird dein Haushalt verwaltet und die einzelnen Lagerorte angezeigt.</p>
            </br>
        </div>
    </div>
    <div class="row">
        <div>
            <%-- Lagerplätze nebeneinander --%>
            <c:forEach items="${household.getPlaceList()}" var="place" >
                <div class="col-md-2">
                    <h2> ${place.name}</h2> 
                </div>
            </c:forEach>
            <!-- Artikeltabelle untehalb des Lagerortes-->
            <br>
            <c:forEach items="${place.getStocksArticleList()}" var="article" >
                <div class="">
                    <strong class="pointer" onclick="overlay('display')">$(article.nameArt)</strong>
                </div>
            </c:forEach>
            <form method="post" action="/MVK-war/ControllerServlet?step=creatStocksArticle">
                <div class="">
                    <input type="text" name="name" placeholder="Artikel..." />
                    <input type="submit" value="Artikel hinzufügen"/>
                </div>
                
            </form>


            <script>
                function overlay(mode) {
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
                            head.setAttribute('className', 'pointer');
                            text = document.createTextNode();
                            head.appendChild(text);
                            lightBox.appendChild(head);

                            document.getElementsByTagName("body")[0].appendChild(div);
                            document.getElementsByTagName("body")[0].appendChild(lightBox);
                        }
                    } else {
                        document.getElementsByTagName("body")[0].removeChild(document.getElementById("overlay"));
                        document.getElementsByTagName("body")[0].removeChild(document.getElementById("lightBox"));
                    }
                }
            </script>




            <form method ="post" action="/MVK-war/ControllerServlet?step=createPlace">
                <div class="col-md-2">
                    <p>Name: <input type ="text" name="name"/></p>
                    <input type="submit"
                           value ="+ Neuen Lagerort hinzufügen"/>
                </div>
            </form>
        </div>
    </div>
</div>

