<%-- 
    Document   : article
    Created on : 24.02.2015, 15:47:39
    Author     : ANABEL
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="template.jsp"></jsp:include>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="user" class="model.AppUser" scope="request"/>
<jsp:useBean id="household" class="model.Household" scope="request"/>
<jsp:useBean id="place" class="model.Place" scope="request"/>
<jsp:useBean id="article" class="model.StocksArticle" scope="request"/>

<div class="container">
    <div class="row">
        <div class="col-lg-12">rticleList()}" var="article" >
    <div class="col-md-2">
            <h1>Hallo ... </h1>
            <p>Das ist die Artikel-Detailseite zu deinen einzelnen Vorräten</p>
            </br>
        </div>
    </div>
</div>


<c:forEach items="${place.getStocksArticleList()}" var="article" >
    <div class="col-md-2">
        <strong class="pointer" onclick="overlay('display')">$(article.nameArt)</strong>
    </div>
</c:forEach>


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