<%-- 
    Document   : register
    Created on : 24.02.2015, 14:43:25
    Author     : ANABEL
--%>

<jsp:include page="template.jsp"></jsp:include>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="container">
    <div class="row">
        <div class="col-lg-12">
            <h1>Willkommen</h1>
            <p>Registriere dich und verwaltete deine eigene Vorratskammer!</p>
            </br>
        </div>
    </div>
    <div class ="row">
        <div class="col-lg-12">
            <form method ="post" action="/MVK-war/ControllerServlet?step=register">
                <p>Email-Adresse: <input type ="email" name="email"/></p>
                <p>Name: <input type="text" name="name"/> </p>
                <p>Passwort: <input type="password" name="password"/></p>
                <input type="submit" value ="Registrieren"/>
            </form>
            </br>
        </div>
    </div>
</div>