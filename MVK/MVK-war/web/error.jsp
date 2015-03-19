<%-- 
    Document   : error
    Created on : 14.03.2015, 22:09:25
    Author     : Felix
--%>

<jsp:include page="template.jsp"></jsp:include>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ein Fehler ist aufgetreten!</title>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <h1>Ups..das sollte nicht passieren! Es ist ein Fehler aufgetreten!</h1>
                    </br>
                    <h1>Bitte versuchen Sie es erneut!</h1>
                    </br>
                </div>
                <div>
                    
                </div>
                <form method ="post" action="/MVK-war/ControllerServlet?step=login">
                <div class="col-md-2">
                    <input type="submit"
                           value ="Zurück zu ???"/>
                </div>
                </form>
            </div>
        </div>
    </body>
</html>
