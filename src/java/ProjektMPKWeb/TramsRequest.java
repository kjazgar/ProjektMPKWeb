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

@WebServlet(name = "Trams", urlPatterns = {"/Trams"})
public class TramsRequest extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        String action = request.getParameter("action");
        
        int id = Integer.parseInt(request.getParameter("idValue"));
        String name = request.getParameter("name");
//        String surname = request.getParameter("surname");
//        String sex =  request.getParameter("sex");
        int level = Integer.parseInt(request.getParameter("level"));

                
        HttpSession s = request.getSession();
        ServletContext c = s.getServletContext();
        TramsDAO tram = (TramsDAO)c.getAttribute("tram");
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            switch (action) {
                case "previous" : {
                    tram.Prev(id);
                    c.setAttribute("tram", tram);
                    response.sendRedirect("trams.jsp");
                    break;
                }
                case "next" : {
                    tram.Next(id);
                    c.setAttribute("tram", tram);
                    response.sendRedirect("trams.jsp");
                    break;
                }
                case "create" : {
                    out.println(name);
                    tram.Create(name, level);
                    c.setAttribute("tram", tram);
                    response.sendRedirect("trams.jsp");
                    break;
                }
                case "update" : {
                    tram.Update(name, level);
                    c.setAttribute("tram", tram);
                    response.sendRedirect("trams.jsp");
                    break;
                }
                case "delete" : {
                    tram.Delete();
                    c.setAttribute("tram", tram);
                    response.sendRedirect("trams.jsp");
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