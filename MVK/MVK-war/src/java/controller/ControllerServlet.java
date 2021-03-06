package controller;

import static controller.BeanFactory.getManageBeanStocks;
import static controller.BeanFactory.getManageBeanUserHousehold;
import java.io.IOException;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.AppUser;
import model.Household;
import model.Place;
import model.StocksArticle;
import model.StocksUnit;

/*
Das ControllerServlet ist die zentrale Steuerungseinheit der webbasierten Datenbankanwendung.
Es bearbeitet alle HTTP Anfragen. Dabei wird immer die processRequest Methode aufgerufen und ausgeführt.
Der Programmablauf wird durch den Parameter "Step" gesteuert. Anhand dieses Parameters wird entschieden, 
welche Methoden der Beans aufgerufen werden müssen und an welche JSP die Ergebnisse weitergeleitet werden. 
Im Servlet wird auch das Exception Handling umgesetzt. Das heißt es werden Exceptions abgefangen
und die darin enthaltenen Informationen an eine Error-JSP weitergeleitet. 

Das Servlet stellt die Controller Komponente der Model View Controller - Architektur dar.
*/
@WebServlet(name = "ControllerServlet", urlPatterns = {"/ControllerServlet"})
public class ControllerServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(ControllerServlet.class.getName());

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        try{
            LOG.info("CustomInfo: SessionBean initialisieren");
            ManageBeanUserHouseholdLocal manageBeanUserHousehold = getManageBeanUserHousehold();
            ManageBeanStocksLocal manageBeanStocks = getManageBeanStocks();
            String currentStep = request.getParameter("step");
            LOG.info("CustomInfo: Aktueller Schritt:" + currentStep);

            /*-------------------------------------------------------------------------------------------
            User (login, registrieren, löschen, ändern, logout)
            -------------------------------------------------------------------------------------------*/
            if(currentStep == null || currentStep.equals("login")){
                if(request.getParameter("email").equals("")){
                    throw new MVKException("Fehler beim Login: Keine Email angegeben.");
                }
                if(request.getParameter("password").equals("")){
                    throw new MVKException("Fehler beim Login: Kein Passwort angegeben.");
                }
                AppUser user = manageBeanUserHousehold.login(request.getParameter("email"),
                        request.getParameter("password"));
                if (user != null) {
                    LOG.info("CustomInfo: Email und Passwort korrekt");
                    HttpSession session = request.getSession(true);
                    session.setAttribute("user", user);
                    request.setAttribute("user", user);
                    request.getRequestDispatcher("/homepage.jsp").forward(request, response);
                } else {
                    LOG.info("CustomInfo: Email oder Passwort falsch");
                    request.getRequestDispatcher("/index.jsp").forward(request, response);
                }
            }
            else if(currentStep.equals("register")){
                if(request.getParameter("name").equals("")){
                    throw new MVKException("Fehler bei der Registrierung: Kein Name angegeben.");
                }
                if(request.getParameter("email").equals("")){
                    throw new MVKException("Fehler bei der Registrierung: Keine Email angegeben.");
                }
                if(request.getParameter("password").equals("")){
                    throw new MVKException("Fehler bei der Registrierung: Kein Passwort angegeben.");
                }
                AppUser user = manageBeanUserHousehold.createUser(request.getParameter("name"),
                        request.getParameter("email"),
                        request.getParameter("password"));
                if (user != null) {
                    LOG.info("CustomInfo: Registrierung erfolgreich");
                    HttpSession session = request.getSession(true);
                    session.setAttribute("user", user);
                    request.setAttribute("user", user);
                    request.getRequestDispatcher("/homepage.jsp").forward(request, response);
                } else {
                    LOG.info("CustomInfo: Registrierung fehlgeschlagen");
                    request.getRequestDispatcher("/register.jsp").forward(request, response);
                }
            }
            else if(currentStep.equals("deleteUser")){
                HttpSession session = request.getSession(true);
                //ich habe es jetzt einfach mit boolean gelöst!
                boolean isDeleted = false;              
                isDeleted = manageBeanUserHousehold.deleteUser((AppUser)session.getAttribute("user"));
                if(isDeleted){
                    LOG.info("CustomInfo: User erfolgreich gelöscht");
                    session.setAttribute("user", null);
                    request.getRequestDispatcher("/index.jsp").forward(request, response);
                }
                else{
                    LOG.info("CustomInfo: User löschen fehlgeschlagen");
                    request.getRequestDispatcher("/homepage.jsp").forward(request, response);
                } 
            }
            else if(currentStep.equals("changeUser")){
                HttpSession session = request.getSession(true);
                AppUser currentUser = (AppUser)session.getAttribute("user");
                String name = (String)request.getParameter("name");
                String email = (String)request.getParameter("email");
                String password = (String)request.getParameter("password");
                AppUser changedUser = null;
                if(!name.equals("")){
                    changedUser = manageBeanUserHousehold.changeName(currentUser, name);
                }
                else if(!email.equals("")){
                    changedUser = manageBeanUserHousehold.changeEmail(currentUser, email);
                }
                else if(!password.equals("")){
                    changedUser = manageBeanUserHousehold.changePassword(currentUser, password);
                }else{
                    throw new MVKException("Fehler beim Ändern des Users: Kein neuer Wert angegeben.");
                }

                if(changedUser != null){
                    LOG.info("CustomInfo: User erfolgreich geändert");
                    request.setAttribute("user", changedUser);
                    session.setAttribute("user", changedUser);
                    request.getRequestDispatcher("/homepage.jsp").forward(request, response);
                }
                else{
                    LOG.info("CustomInfo: User ändern fehlgeschlagen");
                    request.getRequestDispatcher("/homepage.jsp").forward(request, response);
                }
            }
            else if(currentStep.equals("logout")){
                HttpSession session = request.getSession(true);
                session.invalidate();
                LOG.info("CustomInfo: Ausloggen erfolgreich");
                request.getRequestDispatcher("/logout.jsp").forward(request, response);
            }

            /*-------------------------------------------------------------------------------------------
            Household
            -------------------------------------------------------------------------------------------*/
            else if(currentStep.equals("createHousehold")){
                HttpSession session = request.getSession(true);
                if(request.getParameter("name").equals("")){
                    throw new MVKException("Fehler beim Anlegen des Haushalts: Kein Name angegeben.");
                }
                Household household = manageBeanUserHousehold.addHousehold(request.getParameter("name"),
                                                                        (AppUser) session.getAttribute("user"));
                if (household != null) {
                    LOG.info("CustomInfo: Haushalt erfolgreich angelegt");
                    session.setAttribute("household", household);
                    request.setAttribute("user", (AppUser) session.getAttribute("user"));
                    request.setAttribute("household", household);
                    request.getRequestDispatcher("/household.jsp").forward(request, response);
                } else {
                    LOG.info("CustomInfo: Haushalt anlegen fehlgeschlagen");
                    request.getRequestDispatcher("/homepage.jsp").forward(request, response);
                }
            }
            else if(currentStep.equals("changeHousehold")){
                HttpSession session = request.getSession(true);
                String name = (String) request.getParameter("name");
                if(name.equals("")){
                    throw new MVKException("Fehler beim Ändern des Haushalts: Kein Name angegeben.");
                }
                Household changedHousehold = manageBeanUserHousehold.changeHousehold((Household)session.getAttribute("household"),
                                                                                     name);
                if(changedHousehold != null){
                    LOG.info("CustomInfo: Haushalt erfolgreich geändert");
                    request.setAttribute("user", session.getAttribute("user"));
                    request.setAttribute("household", changedHousehold);
                    session.setAttribute("household", changedHousehold);
                    response.sendRedirect("/MVK-war/ControllerServlet?step=toHousehold&id=" + session.getAttribute("id"));
                }
                else{
                    LOG.info("CustomInfo: Haushalt ändern fehlgeschlagen");
                    request.getRequestDispatcher("/household.jsp").forward(request, response);
                }       
            }
            else if(currentStep.equals("removeHousehold")){
                HttpSession session = request.getSession(true);
                LOG.info("CustomInfo: Haushalt entfernen (ControllerServlet)");
                AppUser user = manageBeanUserHousehold.removeHousehold((Household)session.getAttribute("household"),(AppUser)session.getAttribute("user"));
                session.setAttribute("user", user);
                request.setAttribute("user", user);
                request.getRequestDispatcher("homepage.jsp").forward(request, response);
            }
            else if(currentStep.equals("shareHousehold")){
                HttpSession session = request.getSession(true);
                LOG.info("CustomInfo: Haushalt teilen");
                String email = (String)request.getParameter("email");
                if(email.equals("")){
                    throw new MVKException("Fehler beim Teilen des Haushalts: Keine Email angegeben.");
                }
                manageBeanUserHousehold.shareHousehold((Household) session.getAttribute("household"), email);

                request.setAttribute("user", session.getAttribute("user"));
                request.setAttribute("household", session.getAttribute("household"));
                request.getRequestDispatcher("/household.jsp").forward(request, response);

            }
            /*-------------------------------------------------------------------------------------------
            Place
            -------------------------------------------------------------------------------------------*/
            else if(currentStep.equals("createPlace")){
                HttpSession session = request.getSession(true);
                if(request.getParameter("name").equals("")){
                    throw new MVKException("Fehler beim Anlegen des Lagerortes: Kein Name angegeben.");
                }
                Place place = manageBeanUserHousehold.addPlace(request.getParameter("name"),
                                                            (Household) session.getAttribute("household"));
                if (place != null) {
                    LOG.info("CustomInfo: Lagerort erfolgreich angelegt");
                    request.setAttribute("user", (AppUser) session.getAttribute("user"));
                    request.setAttribute("household", (Household) session.getAttribute("household"));
                    request.setAttribute("place", place);
                    request.getRequestDispatcher("/household.jsp").forward(request, response);
                } else {
                    LOG.info("CustomInfo: Lagerort anlegen fehlgeschlagen");
                    request.getRequestDispatcher("/household.jsp").forward(request, response);
                }
            }
            /*-------------------------------------------------------------------------------------------
            StocksArticle
            -------------------------------------------------------------------------------------------*/
            else if(currentStep.equals("createStocksArticle")){
                HttpSession session = request.getSession(true);
                if(request.getParameter("name").equals("")){
                    throw new MVKException("Fehler beim Anlegen des Vorratsartikels: Kein Name angegeben.");
                }
                LOG.info("CustomInfo: ID vom Platz holen");
                Place place = manageBeanUserHousehold.findPlace((String)request.getParameter("id"));
                session.setAttribute("place", place);
                StocksArticle stocksArticle = manageBeanStocks.addStocksArticle(request.getParameter("name"),
                                                place, "");
                if (stocksArticle != null) {
                    LOG.info("CustomInfo: StocksArticle erfolgreich angelegt");
                    request.setAttribute("user", (AppUser) session.getAttribute("user"));
                    request.setAttribute("household", (Household) session.getAttribute("household"));
                    request.setAttribute("place", (Place) session.getAttribute("place"));
                    request.setAttribute("stocksArticle", stocksArticle);
                    response.sendRedirect("/MVK-war/ControllerServlet?step=toHousehold&id=" + session.getAttribute("id"));
                } else {
                    LOG.info("CustomInfo: StocksArticle anlegen fehlgeschlagen");
                    request.getRequestDispatcher("/household.jsp").forward(request, response);
                }
            }
            else if(currentStep.equals("changeStocksArticle")){
                HttpSession session = request.getSession(true);
                session.getAttribute("stocksArticle");
            }
            else if(currentStep.equals("removeStocksArticle")){
                HttpSession session = request.getSession(true);
                StocksArticle findStocksArticle = 
                        manageBeanStocks.findStocksArticle((String)request.getParameter("stocksArticleID"));
                
                
                boolean isDeleted = true;              
                isDeleted = manageBeanStocks.removeStocksArticle(findStocksArticle);
                if(isDeleted){
                    LOG.info("CustomInfo: StocksArticle erfolgreich gelöscht");
                    request.setAttribute("user", (AppUser) session.getAttribute("user"));
                    request.setAttribute("household", (Household) session.getAttribute("household"));
                    response.sendRedirect("/MVK-war/ControllerServlet?step=toHousehold&id=" + session.getAttribute("id"));
                }
                else{
                    LOG.info("CustomInfo: StocksArticle löschen fehlgeschlagen");
                    request.getRequestDispatcher("/household.jsp").forward(request, response);
                } 
            }
            /*-------------------------------------------------------------------------------------------
            StocksUnit
            -------------------------------------------------------------------------------------------*/
            else if(currentStep.equals("createStocksUnit")){
                HttpSession session = request.getSession(true);
                LOG.info("CustomInfo: ID vom StocksArticle holen");
                if(request.getParameter("Menge").equals("")){
                    throw new MVKException("Fehler beim Anlegen der Vorratseinheit: Keine Menge angegeben.");
                }
                StocksArticle stocksArticle = manageBeanStocks.findStocksArticle((String)request.getParameter("StocksArticleID"));
                session.setAttribute("stocksArticle", stocksArticle);
                
                StocksUnit stocksUnit = manageBeanStocks.addStocksUnit(stocksArticle, request.getParameter("Menge"),
                                                request.getParameter("Datum"), request.getParameter("Kommentar"));
                if (stocksUnit != null) {
                    LOG.info("CustomInfo: StocksUnit erfolgreich angelegt");
                    request.setAttribute("user", (AppUser) session.getAttribute("user"));
                    request.setAttribute("household", (Household) session.getAttribute("household"));
                    request.setAttribute("place", (Place) session.getAttribute("place"));
                    request.setAttribute("stocksArticle", (StocksArticle) session.getAttribute("stocksArticle"));
                    request.setAttribute("stocksUnit", stocksUnit);
                    response.sendRedirect("/MVK-war/ControllerServlet?step=toHousehold&id=" + session.getAttribute("id"));
                } else {
                    LOG.info("CustomInfo: StocksArticle anlegen fehlgeschlagen");
                    request.getRequestDispatcher("/household.jsp").forward(request, response);
                }
            }
            else if(currentStep.equals("raiseQuantity")){
                HttpSession session = request.getSession(true);
                LOG.info("CustomInfo: Menge des Units erhöhen"); 
                
                StocksUnit stocksUnit = manageBeanStocks.findStocksUnit((String)request.getParameter("id"));
                session.setAttribute("stocksUnit", stocksUnit);
                StocksUnit changedUnit = manageBeanStocks.raiseQuantityOfStocksUnit((StocksUnit) session.getAttribute("stocksUnit"));
                if (changedUnit != null){
                    LOG.info("CustomInfo: Menge des Units wurde erhöht"); 
                    request.setAttribute("user", (AppUser) session.getAttribute("user"));
                    request.setAttribute("household", (Household) session.getAttribute("household"));
                    request.setAttribute("place", (Place) session.getAttribute("place"));
                    request.setAttribute("stocksArticle", (StocksArticle) session.getAttribute("stocksArticle"));
                    request.setAttribute("stocksUnit", changedUnit);
                    session.setAttribute("stocksUnit", changedUnit);
                    request.getRequestDispatcher("/ControllerServlet?step=toHousehold&id=" + session.getAttribute("id")).forward(request, response);
                }
                else{
                     LOG.info("CustomInfo: StocksUnit erhöhen fehlgeschlagen");
                     request.getRequestDispatcher("/ControllerServlet?step=toHousehold&id=" + session.getAttribute("id")).forward(request, response);

                }
            }
            else if(currentStep.equals("reduceQuantity")){
                HttpSession session = request.getSession(true);
                LOG.info("CustomInfo: ID vom StocksArticle holen");       
                StocksUnit stocksUnit = manageBeanStocks.findStocksUnit((String)request.getParameter("id"));
                
                if(stocksUnit.getQuantity() == 0){
                    throw new MVKException("Fehler beim Reduzieren der Menge: Menge kleiner 0.");
                }
                
                session.setAttribute("stocksUnit", stocksUnit);
                LOG.info("CustomInfo: Menge des Units reduzieren"); 
                
                StocksUnit changedUnit = manageBeanStocks.reduceQuantityOfStocksUnit(stocksUnit);
                if (changedUnit != null){
                    LOG.info("CustomInfo: Menge des Units wurde vermindert"); 
                    request.setAttribute("user", (AppUser) session.getAttribute("user"));
                    request.setAttribute("household", (Household) session.getAttribute("household"));
                    request.setAttribute("place", (Place) session.getAttribute("place"));
                    request.setAttribute("stocksArticle", (StocksArticle) session.getAttribute("stocksArticle"));
                    request.setAttribute("stocksUnit", changedUnit);
                    session.setAttribute("stocksUnit", changedUnit);
                    request.getRequestDispatcher("/ControllerServlet?step=toHousehold&id=" + session.getAttribute("id")).forward(request, response);
                }
                else{
                     LOG.info("CustomInfo: StocksUnit erhöhen fehlgeschlagen");
                     
                     request.getRequestDispatcher("/ControllerServlet?step=toHousehold&id=" + session.getAttribute("id")).forward(request, response);
                }
            }
            else if(currentStep.equals("changeStocksArticle")){
                HttpSession session = request.getSession(true);
                session.getAttribute("stocksArticle");
            }

            /*-------------------------------------------------------------------------------------------
                Navigation
            -------------------------------------------------------------------------------------------*/
            else if(currentStep.equals("toHousehold")){
                HttpSession session = request.getSession(true);
                LOG.info("CustomInfo: Haushalt öffnen");
                Household household = manageBeanUserHousehold.findHousehold((String)request.getParameter("id"));

                request.setAttribute("user", session.getAttribute("user"));
                request.setAttribute("household", household);
                session.setAttribute("id", household.getHouseholdID());
                session.setAttribute("household", household);
                request.getRequestDispatcher("/household.jsp").forward(request, response);
            }
            else if(currentStep.equals("toSettings")){
                HttpSession session = request.getSession(true);
                LOG.info("CustomInfo: Einstellungen öffnen");
                request.setAttribute("user", session.getAttribute("user"));
                request.getRequestDispatcher("/settings.jsp").forward(request, response);
            }
            else if(currentStep.equals("toAbout")){
                LOG.info("CustomInfo: UeberUns öffnen");
                request.getRequestDispatcher("/about.jsp").forward(request, response);
            }
            else if(currentStep.equals("toHomepage")){
                HttpSession session = request.getSession(true);
                LOG.info("CustomInfo: Einstellungen öffnen");
                request.setAttribute("user", session.getAttribute("user"));
                request.getRequestDispatcher("/homepage.jsp").forward(request, response);
            }
        } catch (MVKException e){
            LOG.info("Exception geworfen");
            request.setAttribute("errorText", e.getMessage());
            request.setAttribute("stackTrace", e.getStackTrace());
            request.setAttribute("currentPage", request.getContextPath());

            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

    /*
    Aufruf der processRequest-Methode bei HTTP-Verben GET und POST
    */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
