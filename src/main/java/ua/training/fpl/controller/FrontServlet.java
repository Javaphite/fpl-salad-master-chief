package ua.training.fpl.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.training.fpl.config.ApplicationConfig;
import ua.training.fpl.controller.command.FallbackCommand;
import ua.training.fpl.controller.command.HttpServletCommand;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Simple front controller implementation to serve all requests in similar way.
 */

@WebServlet("")
public class FrontServlet extends HttpServlet {

    private static final Logger LOG  = LoggerFactory.getLogger(FrontServlet.class);

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
            LOG.error("Exception during command resolving: ", exception);
            return new FallbackCommand();
        }
    }
}
