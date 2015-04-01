<%-- 
Startseite der Webbasierten Datenbankanwendung "meine Vorratskammer".
Beinhaltet einen Begrüßungstext, das Anmeldeformular und einen Link zur Registrierungs-JSP.
--%>

<jsp:include page="template.jsp"></jsp:include>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


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
                <table>
                    <tr>
                        <td>Email-Adresse: </td><td><input type ="email" name="email"/></td>
                    </tr>
                    <tr>
                        <td>Passwort: </td><td><input type="password" name="password"/></td>
                    </tr>
                    <tr><td><input type="submit" value ="Anmelden"/></td></tr>
                </table>
            </form>
            </br>
        </div>
    </div>
    <div class ="row">
        <div class="col-md-6">
            </br>
            <p> Neu hier? Dann <a href="register.jsp">registriere</a> dich jetzt!</p>
        </div>
    </div>
</div>
