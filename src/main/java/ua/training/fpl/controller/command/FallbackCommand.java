package ua.training.fpl.controller.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Command implementing standard fallback logic for cases:
 * <li> no mapped command for request
 * <li> mapped command couldn't be instantiated
 * <li> incorrect request
 */
public class FallbackCommand implements HttpServletCommand {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
       new DisplayKnownRecipes().execute(req, resp);
    }
}
