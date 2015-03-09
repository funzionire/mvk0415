<%-- 
    Document   : homepage
    Created on : 24.02.2015, 15:44:38
    Author     : ANABEL
--%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<jsp:include page="template.jsp"></jsp:include>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="user" class="model.AppUser" scope="request"/>
<jsp:useBean id="household" class="model.Household" scope="request"/>

<div class="container">
    <div class="row">
        <h1>Hallo <jsp:getProperty name="user" property="name" /> </h1>
        <div class="col-md-6">
            <p>Hier werden deine dir zugeordneten Haushalte angezeigt</p>
            </br>
        </div>

    </div>
    <div class="row">
        <h1>Deine Haushalte</h1>
        <div class="col-md-3">
            <input type="button" onclick="" action="/MVK-war/ControllerServlet?step=newHoushold"
                   value ="+ Neuen Haushalt hinzufügen"/>
        </div>
         Hier sollen Haushalte als Buttons angezeigt werden, damit man über diese zur household.jsp kommt 
        <c:forEach var="household" items="${user.returnHouseholds()}">
            <div class ="col-md-3">
                <p> <input type="button" name="${household.name}"</p>
            </div>
        </c:forEach> 

    </div>
</div>
