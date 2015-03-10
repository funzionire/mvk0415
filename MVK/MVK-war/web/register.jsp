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
                <table>
                    <tr>
                        <td>Email-Adresse: </td><td><input type ="email" name="email"/></td>
                    </tr>
                    <tr>
                        <td>Name: </td><td><input type="text" name="name"/> </td>
                    </tr>
                    <tr>
                        <td>Passwort: </td><td><input type="password" name="password"/></td>
                    </tr>
                    <tr>
                        <td><input type="submit" value ="Registrieren"/></td>
                    </tr>
                </table>
            </form>
            </br>
        </div>
    </div>
</div>