package ua.training.fpl.command;


import ua.training.fpl.Configuration;
import ua.training.fpl.dto.RecipeSummary;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DisplayVeganRecipes implements HttpServletCommand {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute(Configuration.getRecipesParam(),
                Configuration.getRecipeService().getRecipesFiltered(RecipeSummary::isVegan));

        req.getRequestDispatcher(Configuration.getIndexPage()).forward(req, resp);
    }
}
