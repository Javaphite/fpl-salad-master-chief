package ua.training.fpl.controller.command;


import ua.training.fpl.config.ApplicationConfig;
import ua.training.fpl.model.dto.RecipeSummary;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Command to filter recipes leaving only vegetarian ones.
 */
public class DisplayVeganRecipes implements HttpServletCommand {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setAttribute(ApplicationConfig.getRecipesParam(),
                ApplicationConfig.getRecipeService().getRecipesFiltered(RecipeSummary::isVegan));
        req.getRequestDispatcher(ApplicationConfig.getIndexPage()).forward(req, resp);
    }
}
