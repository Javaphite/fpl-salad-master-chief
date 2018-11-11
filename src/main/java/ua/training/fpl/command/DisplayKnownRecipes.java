package ua.training.fpl.command;

import ua.training.fpl.Configuration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DisplayKnownRecipes implements HttpServletCommand {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute(Configuration.getRecipesParam(), Configuration.getRecipeService().getKnownRecipes());
        req.getRequestDispatcher(Configuration.getIndexPage()).forward(req, resp);
    }
}
