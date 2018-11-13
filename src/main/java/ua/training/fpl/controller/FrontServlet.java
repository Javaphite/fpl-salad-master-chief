package ua.training.fpl.controller;

import ua.training.fpl.config.ApplicationConfig;
import ua.training.fpl.command.DoFallback;
import ua.training.fpl.command.HttpServletCommand;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("")
public class FrontServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resolveCommand(req).execute(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resolveCommand(req).execute(req, resp);
    }

    private HttpServletCommand resolveCommand(HttpServletRequest req) {
        Class<? extends HttpServletCommand> commandClazz =
                ApplicationConfig.getCommandMapping().get(req.getParameter("action"));
        try {
            return commandClazz.getConstructor().newInstance();
        } catch (Exception exception) {
            // TODO: log and fallback
            return new DoFallback();
        }
    }
}
