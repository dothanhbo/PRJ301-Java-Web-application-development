/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myservlet;

import Packages.Account;
import dbacess.AccountDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author NCC
 */
public class loginServlet extends HttpServlet {

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
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String email=request.getParameter("txtemail");
            String password=request.getParameter("txtpassword");
            String save=request.getParameter("savelogin");
            Account acc=null;
            if(email==null || email.equals("")|| password==null || password.equals("")){
               Cookie[] c=request.getCookies();
               String token="";
               if(c!=null){
                   for(Cookie aCookie : c){
                       if (aCookie.getName().equals("selector")){
                           token= aCookie.getValue();
                       }
                   }
               }
               if (!token.equals("")){
                   response.sendRedirect("personalPage.jsp");
               }else
                   response.sendRedirect("invalid.html");
            }
            else{
                acc=AccountDao.getAccount(email,password);
            if(acc!=null){
                    if(acc.getRole()==1){
                        response.sendRedirect("adminPage.jsp");
                    }        
                else{
       
                    HttpSession session=request.getSession(true);
                    if(session!=null){
                        session.setAttribute("name", acc.getFullname());
                        session.setAttribute("email",email);
                        if(save !=null){
                            String token= "rememberemail";
                            AccountDao.updateToken(token, email);
                            Cookie cookie=new Cookie("selector",token);
                            cookie.setMaxAge(60*2);
                            response.addCookie(cookie);                 
                        }
                    response.sendRedirect("personalPage.jsp");
           }
        }
    }else response.sendRedirect("invalid.html");
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(loginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(loginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
