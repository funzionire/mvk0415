<%-- 
    Document   : homepage
    Created on : 24.02.2015, 15:44:38
    Author     : ANABEL
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="template.jsp"></jsp:include>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="user" class="model.AppUser" scope="request"/>
<jsp:useBean id="household" class="model.Household" scope="request"/>

<div class="container">
    <div class="row">
        <div class="col-md-6">
            <h1>Hallo <jsp:getProperty name="user" property="name" /> </h1>
            <h1>Deine Haushalte</h1>
            <br />
        </div>

    </div>
    <div class ="row">
        <div>
            <%--Methode returnHouseholds wird noch zu getHouseholdsList geändert --%>
            <c:forEach items="${user.getHouseholdList()}" var="household" >
                <form method ="post" action ="MKV-war/ControllerServlet?step=toHousehold">
                    <div class="col-md-2">
                       <input type="submit" class="hhhead" value="${household.name}"/>  
                    </div>
                </form>
            </c:forEach>
            
            <form method ="post" action="/MVK-war/ControllerServlet?step=createHousehold">
                <div class="col-md-2">
                    <p>Name: <input type ="text" name="name"/></p>
                    <input type="submit"
                           value ="+ Neuen Haushalt hinzufügen"/>
                </div>
            </form>
        </div>
    </div>
</div>
