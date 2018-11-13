package ua.training.fpl.command;

import ua.training.fpl.config.ApplicationConfig;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DisplayKnownRecipes implements HttpServletCommand {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute(ApplicationConfig.getRecipesParam(), ApplicationConfig.getRecipeService().getKnownRecipes());
        req.getRequestDispatcher(ApplicationConfig.getIndexPage()).forward(req, resp);
    }
}
