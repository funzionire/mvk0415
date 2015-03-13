<%-- 
    Document   : about
    Created on : 10.03.2015, 13:56:55
    Author     : Steffen
--%>

<jsp:include page="template.jsp"></jsp:include>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="user" class="model.AppUser" scope="request"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Einstellungen</title>
    </head>
    <body>
        <div class="container">
            <div class ="row">
                <div class="col-lg-12">
                <h1>Benutzer ändern</h1>
                    <form method="post" action="/MVK-war/ControllerServlet?step=changeUser">
                        <table>
                            <tr>
                                <td>Name: </td><td><input type="text" name="name"/> </td><td><input type="submit" value ="+ Name ändern"/></td>
                            </tr>
                            <tr>
                                <td>Email-Adresse: </td><td><input type ="email" name="email"/></td><td><input type="submit" value ="+ E-Mail ändern"/></td>
                            </tr>
                            <tr>
                                <td>Passwort: </td><td><input type="password" name="password"/></td><td><input type="submit" value ="+ Passwort ändern"/></td>
                            </tr>
                        </table>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
