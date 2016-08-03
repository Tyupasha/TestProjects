package web.tasker.tyupa.app.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        if(request.getParameter("Button") != null) {
            try {
                if((request.getParameter("Button").equals("redirectToChangeTask")) ||
                        (request.getParameter("Button").equals("redirectToChangeManager"))) {
                    response.sendRedirect("frontend/generalEdit");
                }
            } catch (IOException ioException) {
                Logger.getLogger(MainServlet.class.getName()).log(Level.SEVERE, "IoException in doGet: ", ioException);
            }
        } else {
            try {
                getServletContext().getRequestDispatcher("/frontend/" + request.getRequestURI() + ".jsp").forward(request, response);
            } catch (ServletException servletException) {
                Logger.getLogger(MainServlet.class.getName()).log(Level.SEVERE, "ServletException in doGet: ", servletException);
            } catch (IOException ioException) {
                Logger.getLogger(MainServlet.class.getName()).log(Level.SEVERE, "IoException in doGet: ", ioException);
            }
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {

    }
}
