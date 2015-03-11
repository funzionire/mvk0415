/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static controller.BeanFactory.getSessionBeanHousehold;
import static controller.BeanFactory.getSessionBeanUser;
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
        SessionBeanUserLocal sessionBeanUser = getSessionBeanUser();
        SessionBeanHouseholdLocal sessionBeanHousehold = getSessionBeanHousehold();
        String currentStep = request.getParameter("step");
        LOG.info("CustomInfo: Aktueller Schritt:" + currentStep);
        
        /*-------------------------------------------------------------------------------------------
        User
        -------------------------------------------------------------------------------------------*/
        if(currentStep == null || currentStep.equals("login")){
            AppUser user = sessionBeanUser.login(request.getParameter("email"),
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
            AppUser user = sessionBeanUser.createUser(request.getParameter("name"),
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
            Boolean isDeleted = sessionBeanUser.deleteUser((AppUser)session.getAttribute("user"));
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
            AppUser changedUser = sessionBeanUser.changeUser((AppUser)session.getAttribute("user"), 
                                                            (String)request.getAttribute("name"), 
                                                            (String)request.getAttribute("email"), 
                                                            (String)request.getAttribute("password"));
            if(changedUser != null){
                LOG.info("CustomInfo: User erfolgreich geändert");
                session.setAttribute("user", changedUser);
                request.getRequestDispatcher("/homepage.jsp").forward(request, response);
            }
            else{
                LOG.info("CustomInfo: User ändern fehlgeschlagen");
                request.getRequestDispatcher("/homepage.jsp").forward(request, response);
            }
        }
        
        /*-------------------------------------------------------------------------------------------
        Household
        -------------------------------------------------------------------------------------------*/
        else if(currentStep.equals("createHousehold")){
            HttpSession session = request.getSession(true);
            Household household = sessionBeanHousehold.createHousehold(request.getParameter("name"),
                    (AppUser) session.getAttribute("user"));
            if (household != null) {
                LOG.info("CustomInfo: Haushalt erfolgreich angelegt");
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
            session.getAttribute("household");
        }
        else if(currentStep.equals("deleteHousehold")){
            HttpSession session = request.getSession(true);
            session.getAttribute("household");
        }
        else if(currentStep.equals("toHousehold")){
                HttpSession session = request.getSession(true);
                LOG.info("CustomInfo: Haushalt öffnen");
                request.setAttribute("user", session.getAttribute("user"));
                request.setAttribute("household", session.getAttribute("household"));
                request.getRequestDispatcher("/household.jsp").forward(request, response);
        }
    }
    /*-------------------------------------------------------------------------------------------
    Place
    -------------------------------------------------------------------------------------------*/
    
    /*-------------------------------------------------------------------------------------------
    StocksArticle
    -------------------------------------------------------------------------------------------*/
    

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
