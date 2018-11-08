package ua.training.fpl.model.controller.servlet;

import ua.training.fpl.model.salads.Salad;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/*")
public class SaladMasterChiefServlet extends HttpServlet {

    private static Salad saladHolder;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        if (req.getPathTranslated().contains("createSalad")) {

        }
        req.getRequestDispatcher("/WEB-INF/registerPerson.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

    }

    private void createSalad(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String recipeName = (String) req.getAttribute("recipe");

        int portions = (Integer) req.getAttribute("portions");
        saladHolder = new Salad()
        resp.sendRedirect(req.getContextPath() + '/');
    }

}
