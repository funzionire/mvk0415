<%-- 
    Document   : login
    Created on : 24.02.2015, 14:58:04
    Author     : ANABEL
--%>

<jsp:include page="template.jsp"></jsp:include>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!-- Page Content -->
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

    <!-- ----------Overlay----------- -->

    <div class ="row">
        <div class="col-md-6">
            </br>
            <strong class="pointer" id="zzz" onclick="overlay('display')">Licht aus, Spot an!</strong>
        </div>
    </div>

    <script>
        function overlay(mode) {
            if (mode === 'display') {
                if (document.getElementById("overlay") === null) {
                    div = document.createElement("div");
                    div.setAttribute('id', 'overlay');
                    div.setAttribute('className', 'overlayBG');
                    div.setAttribute('class', 'overlayBG');


                    lightBox = document.createElement('div');
                    lightBox.setAttribute('id', 'lightBox');

                    span = document.createElement('span');
                    span.setAttribute('class', 'pointer');
                    span.setAttribute('className', 'pointer');
                    span.setAttribute('onclick', 'overlay(\'none\')');
                    text = document.createTextNode('Schließen');
                    span.appendChild(text);
                    lightBox.appendChild(span);

                    head = document.createElement('h2');
                    head.setAttribute('class', 'pointer');
                    text = document.createTextNode(document.getElementById('zzz').innerHTML);
                    head.appendChild(text);
                    lightBox.appendChild(head);
                    
                    form = document.createElement('form');
                    form.setAttribute('method', 'post');
                    form.setAttribute('action', '/MVK-war/ControllerServlet?step=createStocksArticle');
                    
                    textfeld = document.createElement('input');
                    textfeld.setAttribute('type', 'text');
                    textfeld.setAttribute('name', 'name');

                    button = document.createElement('input');
                    button.setAttribute('type', 'submit');
                    button.setAttribute('value', 'add');
                    
                    form.appendChild(textfeld);
                    form.appendChild(button);
                    lightBox.appendChild(form);

                    document.getElementsByTagName("body")[0].appendChild(div);
                    document.getElementsByTagName("body")[0].appendChild(lightBox);
                }
            } else {
                document.getElementsByTagName("body")[0].removeChild(document.getElementById("overlay"));
                document.getElementsByTagName("body")[0].removeChild(document.getElementById("lightBox"));
            }
        }
    </script>
    <!-- Overlay Ende-->

</div>
