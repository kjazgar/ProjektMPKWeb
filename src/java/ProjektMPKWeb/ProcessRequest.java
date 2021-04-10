package ProjektMPKWeb;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.*;
import javax.servlet.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Process", urlPatterns = {"/Process"})
public class ProcessRequest extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        String action = request.getParameter("action");
        
        int id = Integer.parseInt(request.getParameter("idValue"));
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String sex =  request.getParameter("sex");
        int level = Integer.parseInt(request.getParameter("level"));

                
        HttpSession s = request.getSession();
        ServletContext c = s.getServletContext();
        MotormanDAO motorman = (MotormanDAO)c.getAttribute("motorman");
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            switch (action) {
                case "previous" : {
                    motorman.Prev(id);
                    c.setAttribute("motorman", motorman);
                    response.sendRedirect("index.jsp");
                    break;
                }
                case "next" : {
                    motorman.Next(id);
                    c.setAttribute("motorman", motorman);
                    response.sendRedirect("index.jsp");
                    break;
                }
                case "create" : {
                    out.println(name + surname);
                    motorman.Create(name, surname, sex, level);
                    c.setAttribute("motorman", motorman);
                    response.sendRedirect("index.jsp");
                    break;
                }
                case "update" : {
                    motorman.Update(name, surname, sex, level);
                    c.setAttribute("motorman", motorman);
                    response.sendRedirect("index.jsp");
                    break;
                }
                case "delete" : {
                    motorman.Delete();
                    c.setAttribute("motorman", motorman);
                    response.sendRedirect("index.jsp");
                    break;
                }
                default : {
                    out.println("The action for " + action + "OnClick()" + " is to be implemented... .");
                }
            }
        }
    }

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
}