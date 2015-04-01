<%-- 
Haushaltsübersichtsseite
Hier werden die einzelnen Haushalte eines Users dargestellt sowie
ein Formular zum Erstellen eines neuen Haushaltes.
--%>


<jsp:include page="template.jsp"></jsp:include>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
        <%--Übersicht der vorhanden Haushalte eines Users--%>
        <c:forEach items="${user.getHouseholdList()}" var="household">
            <div class="col-md-2">
                <c:url var="toHousehold" value="/ControllerServlet?step=toHousehold">
                    <c:param name="id" value="${household.householdID}"/>
                </c:url>
                <div class="col-md-2">
                    <a href='${toHousehold}' name="HouseholdLabel">
                        <input type="hidden"
                               value="${household.householdID}"
                               name="id"
                               />
                        <input type="submit"
                               class="hhhead"
                               value="${household.name}"
                               name="householdName"
                               />
                    </a>
                </div>
            </div>
        </c:forEach>

        <%--Formular um neuen Haushalt anzulegen --%>
        <form method ="post" action="ControllerServlet?step=createHousehold">
            <div class="col-md-2">
                <p>Name: <input type ="text" name="name"/></p>
                <input type="submit"
                       value ="+ Neuen Haushalt hinzufügen"/>
            </div>
        </form>
    </div>
</div>
