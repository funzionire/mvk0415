<%-- 
    Document   : household
    Created on : 24.02.2015, 15:46:18
    Author     : ANABEL
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="template.jsp"></jsp:include>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="user" class="model.AppUser" scope="request"/>
<jsp:useBean id="household" class="model.Household" scope="request"/>
<div class="container">
    <div class="row">
        <div class="col-lg-12">
            <h1>Hallo <jsp:getProperty name="user" property="name"/> </h1>
            <h3>Haushalt: <jsp:getProperty name="household" property="name"/> </h3>
            <p>Hier wird dein Haushalt verwaltet und die einzelnen Lagerorte angezeigt.</p>
            </br>
        </div>
    </div>
    <div class="row">
        <div>
            <%-- ${household.getPlaceList()} muss in Items eingefügt werden --%>
            <c:forEach items="${household.getPlaceList()}" var="place" >
                <div class="col-md-2">
                    <h2> ${place.name}</h2> 
                </div>
            </c:forEach>
            
            <form method ="post" action="/MVK-war/ControllerServlet?step=createPlace">
                <div class="col-md-2">
                    <p>Name: <input type ="text" name="name"/></p>
                    <input type="submit"
                           value ="+ Neuen Lagerort hinzufügen"/>
                </div>
            </form>
        </div>
    </div>
</div>

