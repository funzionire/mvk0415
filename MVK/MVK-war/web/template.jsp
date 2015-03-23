<%-- 
    Document   : index
    Created on : 24.02.2015, 13:31:16
    Author     : ANABEL
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="user" class="model.AppUser" scope="request"/>
<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Meine Vorratskammer</title>
        
        <!-- Skript Overlay-->
        <script language="javascript" type="text/javascript" src="js/overlay.js" > </script>

        <!-- Bootstrap Core CSS -->
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <!-- Custom CSS -->
        <link href="css/logo-nav.css" rel="stylesheet">
        <!-- Awesome Icons-->
        <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">


        <!-- Favicon-->
        <link rel="apple-touch-icon" sizes="57x57" href="/MVK-war/img/apple-touch-icon-57x57.png">
        <link rel="apple-touch-icon" sizes="60x60" href="/MVK-war/img/apple-touch-icon-60x60.png">
        <link rel="apple-touch-icon" sizes="72x72" href="/MVK-war/img/apple-touch-icon-72x72.png">
        <link rel="apple-touch-icon" sizes="76x76" href="/MVK-war/img/apple-touch-icon-76x76.png">
        <link rel="apple-touch-icon" sizes="114x114" href="/MVK-war/img/apple-touch-icon-114x114.png">
        <link rel="apple-touch-icon" sizes="120x120" href="/MVK-war/img/apple-touch-icon-120x120.png">
        <link rel="apple-touch-icon" sizes="144x144" href="/MVK-war/img/apple-touch-icon-144x144.png">
        <link rel="apple-touch-icon" sizes="152x152" href="/MVK-war/img/apple-touch-icon-152x152.png">
        <link rel="apple-touch-icon" sizes="180x180" href="/MVK-war/img/apple-touch-icon-180x180.png">
        <link rel="icon" type="image/png" href="/MVK-war/img/favicon-32x32.png" sizes="32x32">
        <link rel="icon" type="image/png" href="/MVK-war/img/favicon-194x194.png" sizes="194x194">
        <link rel="icon" type="image/png" href="/MVK-war/img/favicon-96x96.png" sizes="96x96">
        <link rel="icon" type="image/png" href="/MVK-war/img/android-chrome-192x192.png" sizes="192x192">
        <link rel="icon" type="image/png" href="/MVK-war/img/favicon-16x16.png" sizes="16x16">
        <link rel="manifest" href="/MVK-war/img/manifest.json">
        <link rel="shortcut icon" href="/MVK-war/img/favicon.ico">
        <meta name="msapplication-TileColor" content="#008000">
        <meta name="msapplication-TileImage" content="/MVK-war/img/mstile-144x144.png">
        <meta name="msapplication-config" content="/MVK-war/img/browserconfig.xml">
        <meta name="theme-color" content="#008000">

    </head>


    <body  id="content">

        <!-- Navigation -->
        <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
            <div class="container">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>

                    <c:choose>
                        <c:when test="${sessionScope.user == null}">
                            <a class="navbar-brand" href="index.jsp">
                                <img src="img/mvk-logo.png" alt=""/>
                            </a>
                        </c:when>
                        <c:when test="${sessionScope.user != null}">  
                            <c:url var="toHomepage" value="/ControllerServlet?step=toHomepage">
                                <c:param name="id" value="${user.userID}"/>
                            </c:url>
                            <a class="navbar-brand" href="${toHomepage}">
                                <img src="img/mvk-logo.png" alt=""/>
                                <input type="hidden"
                                       value="${user.userID}"
                                       name="id"
                                       />
                            </a>
                        </c:when>
                    </c:choose>

                </div>
                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav">
                        <c:if test="${sessionScope.user == null}" >
                            <li>
                                <a href="/MVK-war/register.jsp" name="RegistrierenLabel">Registrieren</a>
                            </li>
                        </c:if>
                        <li>
                            <a href="about.jsp" name="UeberUnsLabel">Über uns</a>
                        </li>
                        <c:if test="${sessionScope.user != null}">
                            <li>
                                <a href="/MVK-war/ControllerServlet?step=toSettings" name="EinstellungenLabel">Einstellungen</a>
                            </li>
                        </c:if>
                        <c:if test="${sessionScope.user != null}">
                            <li>
                                <a href="/MVK-war/ControllerServlet?step=logout" name="AbmeldenLabel">Abmelden</a>
                            </li>
                        </c:if>
                    </ul>
                </div>
                <!-- /.navbar-collapse -->
            </div>
            <!-- /.container -->
        </nav>

        <div class="blur"></div>

        <!-- page content -->
        <!-- EINBINDEN DER RICHTIGEN JSP -->

        <!-- /.container -->

        <!-- jQuery -->
        <script src="js/jquery.js"></script>

        <!-- Bootstrap Core JavaScript -->
        <script src="js/bootstrap.min.js"></script>
    </body>

</html>

