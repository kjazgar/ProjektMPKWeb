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

@WebServlet(name = "Drivers", urlPatterns = {"/Drivers"})
public class DriversRequest extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        String action = request.getParameter("action");
        
        int id = Integer.parseInt(request.getParameter("idValue"));
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String sex =  request.getParameter("sex");
        boolean electric = Boolean.parseBoolean(request.getParameter("electric"));

                
        HttpSession s = request.getSession();
        ServletContext c = s.getServletContext();
        DriversDAO drivers = (DriversDAO)c.getAttribute("drivers");
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            switch (action) {
                case "previous" : {
                    drivers.Prev(id);
                    c.setAttribute("drivers", drivers);
                    response.sendRedirect("busdrivers.jsp");
                    break;
                }
                case "next" : {
                    drivers.Next(id);
                    c.setAttribute("drivers", drivers);
                    response.sendRedirect("busdrivers.jsp");
                    break;
                }
                case "create" : {
                    out.println(name + surname);
                    drivers.Create(name, surname, sex, electric);
                    c.setAttribute("drivers", drivers);
                    response.sendRedirect("busdrivers.jsp");
                    break;
                }
                case "update" : {
                    drivers.Update(name, surname, sex, electric);
                    c.setAttribute("drivers", drivers);
                    response.sendRedirect("busdrivers.jsp");
                    break;
                }
                case "delete" : {
                    drivers.Delete();
                    c.setAttribute("drivers", drivers);
                    response.sendRedirect("busdrivers.jsp");
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