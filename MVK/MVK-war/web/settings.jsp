<%-- 
    Document   : about
    Created on : 10.03.2015, 13:56:55
    Author     : Steffen
--%>

<jsp:include page="template.jsp"></jsp:include>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Einstellungen</title>
    </head>
    <body>
        <div class="container">
            <h1>Benutzer ändern</h1>
            <form method="post" action="/MVK-war/ControllerServlet?step=changeUser">
                <div class="col-md-3">
                    <p>Name: <input type ="text" name="name"/></p>
                    <input type="submit"
                        value ="+ Name ändern"/>
                    <p>E-mail: <input type ="text" name="email"/></p>
                    <input type="submit"
                        value ="+ E-Mail ändern"/>
                    <p>Passwort: <input type ="text" name="password"/></p>
                    <input type="submit"
                        value ="+ Passwort ändern"/>
                </div>
            </form>
        </div>
    </body>
</html>
