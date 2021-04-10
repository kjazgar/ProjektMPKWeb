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

@WebServlet(name = "Buses", urlPatterns = {"/Buses"})
public class BusesRequest extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        String action = request.getParameter("action");
        
        int id = Integer.parseInt(request.getParameter("idValue"));
        String name = request.getParameter("name");
//        String surname = request.getParameter("surname");
//        String sex =  request.getParameter("sex");
        boolean electric = Boolean.parseBoolean(request.getParameter("electric"));

                
        HttpSession s = request.getSession();
        ServletContext c = s.getServletContext();
        BusesDAO bus = (BusesDAO)c.getAttribute("bus");
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            switch (action) {
                case "previous" : {
                    bus.Prev(id);
                    c.setAttribute("bus", bus);
                    response.sendRedirect("buses.jsp");
                    break;
                }
                case "next" : {
                    bus.Next(id);
                    c.setAttribute("bus", bus);
                    response.sendRedirect("buses.jsp");
                    break;
                }
                case "create" : {
                    out.println(name);
                    bus.Create(name, electric);
                    c.setAttribute("bus", bus);
                    response.sendRedirect("buses.jsp");
                    break;
                }
                case "update" : {
                    bus.Update(name, electric);
                    c.setAttribute("bus", bus);
                    response.sendRedirect("buses.jsp");
                    break;
                }
                case "delete" : {
                    bus.Delete();
                    c.setAttribute("bus", bus);
                    response.sendRedirect("buses.jsp");
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