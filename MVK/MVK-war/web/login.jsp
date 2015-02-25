<%-- 
    Document   : login
    Created on : 24.02.2015, 14:58:04
    Author     : ANABEL
--%>

<jsp:include page="index.jsp"></jsp:include>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!-- Page Content -->
<div class="container">
    <div class="row">
        <div class="col-md-6">
            <h1>Willkommen</h1>
            <p>Hier entsteht deine Vorratskammer</p>
            </br>
        </div>
    </div>
    <div class ="row">
        <div class="col-md-6">
            <form method ="post" action="/MVK-war/ControllerServlet?step=login">
                <p>Email-Adresse: <input type ="email" name="email"/></p>
                <p>Passwort: <input type="password" name="password"/></p>
                <input type="submit" value ="Anmelden"/>
            </form>
            </br>
        </div>
    </div>
    <div class ="row">
        <div class="col-md-6">
            </br>
            <p>Neu hier? Dann registriere dich jetzt!</p>
            <input type ="submit" onClick="" value="Registrieren"/>
        </div>
    </div>
</div>
