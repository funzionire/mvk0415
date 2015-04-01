/* JavaScript Datei zur Erzeugung eines Popup-Fensters bei Klick auf einen Artikel
 * und zum Einbinden der Artikeldetailseite
 */

var xmlHttpObject = false;

if (typeof XMLHttpRequest !== 'undefined') {
    xmlHttpObject = new XMLHttpRequest();
}
if (!xmlHttpObject) {
    try {
        xmlHttpObject = new ActiveXObject("Msxml2.XMLHTTP");
    }
    catch (e) {
        try {
            xmlHttpObject = new ActiveXObject("Microsoft.XMLHTTP");
        }
        catch (e) {
            xmlHttpObject = null;
        }
    }
}

function overlay(myId, mode) {
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
            text = document.createTextNode(document.getElementById(myId).innerHTML);
            head.appendChild(text);
            lightBox.appendChild(head);

            //Tabelle
            tableContent = document.createElement('div');
            tableContent.setAttribute('id', 'myContent');
            lightBox.appendChild(tableContent);

            //Öffnen der Artikeldetailseite mit der article.jsp
            xmlHttpObject.open('get', 'article.jsp?StocksArticleID=' + myId);
            xmlHttpObject.onreadystatechange = handleContent;
            xmlHttpObject.send(null);

            //Untere Zeile
            table2 = document.createElement('table');
            table2.setAttribute('align', 'center');
            table2.setAttribute('valign', 'middle');


            //Breite setzen
            breite = document.createElement('colgroup');
            zelle = document.createElement('col');
            zelle.setAttribute('width', '100');
            breite.appendChild(zelle);
            breite.appendChild(zelle);
            zelle.setAttribute('width', '200');
            breite.appendChild(zelle);
            zelle.setAttribute('width', '100');
            breite.appendChild(zelle);

            table2.appendChild(breite);

            //Elemente setzen
            //Formular zum Anlegen eines neuen StocksUnits
            tr = document.createElement('tr');

            form = document.createElement('form');
            form.setAttribute('method', 'post');

            form.setAttribute('action', '/MVK-war/ControllerServlet?step=createStocksUnit&StocksArticleID=' + myId);

            td = document.createElement('td');
            textfeldm = document.createElement('input');
            textfeldm.setAttribute('type', 'number');
            textfeldm.setAttribute('min', '1');
            textfeldm.setAttribute('name', 'Menge');
            textfeldm.setAttribute('placeholder', 'Menge...');
            td.appendChild(textfeldm);
            tr.appendChild(td);

            td = document.createElement('td');
            textfeldd = document.createElement('input');
            textfeldd.setAttribute('type', 'date');
            textfeldd.setAttribute('name', 'Datum');
            textfeldd.setAttribute('placeholder', 'MHD...');
            td.appendChild(textfeldd);
            tr.appendChild(td);

            td = document.createElement('td');
            textfeldc = document.createElement('input');
            textfeldc.setAttribute('type', 'text');
            textfeldc.setAttribute('name', 'Kommentar');
            textfeldc.setAttribute('placeholder', 'Kommentar...');
            td.appendChild(textfeldc);
            tr.appendChild(td);

            td = document.createElement('td');
            button = document.createElement('input');
            button.setAttribute('type', 'submit');
            button.setAttribute('value', 'hinzufügen');
            td.appendChild(button);
            tr.appendChild(td);

            form.appendChild(tr);

            table2.appendChild(form);
            lightBox.appendChild(table2);

            document.getElementsByTagName("body")[0].appendChild(div);
            document.getElementsByTagName("body")[0].appendChild(lightBox);
        }
    } else {
        document.getElementsByTagName("body")[0].removeChild(document.getElementById("overlay"));
        document.getElementsByTagName("body")[0].removeChild(document.getElementById("lightBox"));
    }
}
function handleContent() {
    if (xmlHttpObject.readyState === 4) {
        document.getElementById('myContent').innerHTML = xmlHttpObject.responseText;
    }
}



