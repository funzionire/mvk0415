/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static controller.BeanFactory.getSessionBeanUser;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;

/**
 *
 * @author baader
 */
@WebServlet(name = "ControllerServlet", urlPatterns = {"/ControllerServlet"})
public class ControllerServlet extends HttpServlet {

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
        
        SessionBeanUserLocal   sessionBeanUser = getSessionBeanUser();
        String currentStep = request.getParameter("step");
        if(currentStep == null || currentStep.equals("login")){
            User user = sessionBeanUser.login(request.getParameter("email"),
                                    request.getParameter("password"));
            if (user != null)
            {
                request.setAttribute("user", user);
                request.getRequestDispatcher("/homepage.jsp").forward(request, response);
            }
            else
            {
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }
        }
        else if(currentStep.equals("toRegister")){
            request.getRequestDispatcher("/register.jsp").forward(request, response);
        }
        else if(currentStep.equals("register")){
            User user = sessionBeanUser.createUser(request.getParameter("name"),
                                                    request.getParameter("email"),
                                                    request.getParameter("password"));
            if (user != null)
            {
                request.setAttribute("user", user);
                request.getRequestDispatcher("/homepage.jsp").forward(request, response);
            }
            else
            {
                request.getRequestDispatcher("/register.jsp").forward(request, response);
            }
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
