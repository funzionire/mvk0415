/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static controller.BeanFactory.getManageBeanStocks;
import static controller.BeanFactory.getManageBeanUserHousehold;
import java.io.IOException;
import java.util.Enumeration;
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

/**
 *
 * @author baader
 */
/*In der Homepage.jsp gibt es einen Button, der zum Erzeugen eines neuen Haushaltes genutzt werden soll
 Dafür musste ein neuer Step eingefügt werden (kann man auch gern noch umbenennen)
 bei dem muss dann die Methode createHoushold aufgerufen werden
 */
@WebServlet(name = "ControllerServlet", urlPatterns = {"/ControllerServlet"})
public class ControllerServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(ControllerServlet.class.getName());

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        LOG.info("CustomInfo: SessionBean initialisieren");
        ManageBeanUserHouseholdLocal manageBeanUserHousehold = getManageBeanUserHousehold();
        //SessionBeanPlaceLocal sessionBeanPlace = getSessionBeanPlace();
        ManageBeanStocksLocal manageBeanStocks = getManageBeanStocks();
        String currentStep = request.getParameter("step");
        LOG.info("CustomInfo: Aktueller Schritt:" + currentStep);
        
        /*-------------------------------------------------------------------------------------------
        User
        -------------------------------------------------------------------------------------------*/
        if(currentStep == null || currentStep.equals("login")){
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
            AppUser user = manageBeanUserHousehold.createUser(request.getParameter("name"),
                    request.getParameter("email"),
                    request.getParameter("password"));
            if (user != null) {
                LOG.info("CustomInfo: Registrierung erfolgreich");
                request.setAttribute("user", user);
                request.getRequestDispatcher("/homepage.jsp").forward(request, response);
            } else {
                LOG.info("CustomInfo: Registrierung fehlgeschlagen");
                request.getRequestDispatcher("/register.jsp").forward(request, response);
            }
        }
        else if(currentStep.equals("deleteUser")){
            HttpSession session = request.getSession(true);
            Boolean isDeleted = manageBeanUserHousehold.deleteUser((AppUser)session.getAttribute("user"));
            if(isDeleted == true){
                LOG.info("CustomInfo: User erfolgreich gelöscht");
                session.setAttribute("user", null);
                request.getRequestDispatcher("/index.jsp");
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
            LOG.info(name);
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
            //manageBeanUserHousehold.logoutUser((AppUser)session.getAttribute("user"));
            session.removeAttribute("user");
            session.removeAttribute("household");
            session.removeAttribute("place");
            session.removeAttribute("StocksArticle");
            
            LOG.info("CustomInfo: Ausloggen erfolgreich");
            request.getRequestDispatcher("/logout.jsp").forward(request, response);
        }
        
        /*-------------------------------------------------------------------------------------------
        Household
        -------------------------------------------------------------------------------------------*/
        else if(currentStep.equals("createHousehold")){
            HttpSession session = request.getSession(true);
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
            Household changedHousehold = manageBeanUserHousehold.changeHousehold((Household)session.getAttribute("household"),
                                                                                 (String)request.getAttribute("name"));
            if(changedHousehold != null){
                LOG.info("CustomInfo: Haushalt erfolgreich geändert");
                request.setAttribute("household", changedHousehold);
                session.setAttribute("household", changedHousehold);
                request.getRequestDispatcher("/household.jsp").forward(request, response);
            }
            else{
                LOG.info("CustomInfo: Haushalt ändern fehlgeschlagen");
                request.getRequestDispatcher("/household.jsp").forward(request, response);
            }
        }
        else if(currentStep.equals("deleteHousehold")){
            HttpSession session = request.getSession(true);
            session.getAttribute("household");
        }
        else if(currentStep.equals("shareHousehold")){
            HttpSession session = request.getSession(true);
            LOG.info("CustomInfo: Haushalt teilen");
            String email = (String)request.getParameter("email");
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
            Place place = manageBeanUserHousehold.addPlace(request.getParameter("name"),
                                                        (Household) session.getAttribute("household"));
            if (place != null) {
                LOG.info("CustomInfo: Lagerort erfolgreich angelegt");
                session.setAttribute("place", place);
                request.setAttribute("user", (AppUser) session.getAttribute("user"));
                request.setAttribute("household", (Household) session.getAttribute("household"));
                request.setAttribute("place", place);
                request.getRequestDispatcher("/household.jsp").forward(request, response);
            } else {
                LOG.info("CustomInfo: Lagerort anlegen fehlgeschlagen");
                request.getRequestDispatcher("/household.jsp").forward(request, response);
            }
        }
        else if(currentStep.equals("changePlace")){
            HttpSession session = request.getSession(true);
            session.getAttribute("place");
            Place changedPlace = manageBeanUserHousehold.changePlace((Place)session.getAttribute("place"),
                                                                     (String)request.getAttribute("name"));
            if(changedPlace != null){
                LOG.info("CustomInfo: Lagerort erfolgreich geändert");
                request.setAttribute("place", changedPlace);
                session.setAttribute("plcae", changedPlace);
                request.getRequestDispatcher("/household.jsp").forward(request, response);
            }
            else{
                LOG.info("CustomInfo: Lagerort ändern fehlgeschlagen");
                request.getRequestDispatcher("/household.jsp").forward(request, response);
            }
        }
//        else if(currentStep.equals("deletePlace")){
//            HttpSession session = request.getSession(true);
//            session.getAttribute("place");
//        }
        /*-------------------------------------------------------------------------------------------
        StocksArticle
        -------------------------------------------------------------------------------------------*/
        else if(currentStep.equals("createStocksArticle")){
            HttpSession session = request.getSession(true);
            LOG.info("CustomInfo: ID vom Platz holen");
            String id = (String)request.getParameter("id");
            long longID = Long.parseLong(id);
            Place place = manageBeanUserHousehold.findPlace(longID);
            session.setAttribute("place", place);
            StocksArticle stocksArticle = manageBeanStocks.addStocksArticle(request.getParameter("name"),
                                            place, "");
            if (stocksArticle != null) {
                LOG.info("CustomInfo: StocksArticle erfolgreich angelegt");
                session.setAttribute("stocksArticle", stocksArticle);
                request.setAttribute("user", (AppUser) session.getAttribute("user"));
                request.setAttribute("household", (Household) session.getAttribute("household"));
                request.setAttribute("place", (Place) session.getAttribute("place"));
                request.setAttribute("stocksArticle", stocksArticle);
//                request.setAttribute("id", session.getAttribute("id"));
                response.sendRedirect("/MVK-war/ControllerServlet?step=toHousehold&id=" + session.getAttribute("id"));
//                request.getRequestDispatcher("/household.jsp").forward(request, response);
            } else {
                LOG.info("CustomInfo: StocksArticle anlegen fehlgeschlagen");
                request.getRequestDispatcher("/household.jsp").forward(request, response);
            }
        }
        else if(currentStep.equals("changeStocksArticle")){
            HttpSession session = request.getSession(true);
            session.getAttribute("stocksArticle");
        }
        else if(currentStep.equals("deleteStocksArticle")){
            HttpSession session = request.getSession(true);
            session.getAttribute("stocksArticle");
        }
        
        /*-------------------------------------------------------------------------------------------
            Navigation
        -------------------------------------------------------------------------------------------*/
        else if(currentStep.equals("toHousehold")){
            HttpSession session = request.getSession(true);
            LOG.info("CustomInfo: Haushalt öffnen");
            String id = (String)request.getParameter("id");
            long longID = Long.parseLong(id);
            Household household = manageBeanUserHousehold.findHousehold(longID);
            
            request.setAttribute("user", session.getAttribute("user"));
            request.setAttribute("household", household);
            session.setAttribute("id", longID);
            session.setAttribute("household", household);
            request.getRequestDispatcher("/household.jsp").forward(request, response);
        }
//        else if(currentStep.equals("toPlace")){
//            HttpSession session = request.getSession(true);
//            LOG.info("CustomInfo: Platz öffnen");
//            String id = (String)request.getParameter("id");
//            long longID = Long.parseLong(id);
//            Place place = manageBeanUserHousehold.findPlace(longID);
//            
//            request.setAttribute("user", session.getAttribute("user"));
//            request.setAttribute("household", session.getAttribute("household"));
//            request.setAttribute("place", place);
//            session.setAttribute("place", place);
//            request.getRequestDispatcher("/place.jsp").forward(request, response);
//        }
//        else if(currentStep.equals("toStocksArticle")){
//            HttpSession session = request.getSession(true);
//            LOG.info("CustomInfo: StocksArticle öffnen");
//            request.setAttribute("user", session.getAttribute("user"));
//            request.setAttribute("household", session.getAttribute("household"));
//            request.setAttribute("place", session.getAttribute("place"));
//            request.setAttribute("stocksArticle", session.getAttribute("stocksArticle"));
//            request.getRequestDispatcher("/household.jsp").forward(request, response);
//        }
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
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
