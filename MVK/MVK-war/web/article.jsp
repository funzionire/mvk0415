<%-- 
    Document   : article
    Created on : 24.02.2015, 15:47:39
    Author     : ANABEL
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


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