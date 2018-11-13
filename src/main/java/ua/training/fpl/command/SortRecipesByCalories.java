package ua.training.fpl.command;

import ua.training.fpl.config.ApplicationConfig;
import ua.training.fpl.dto.RecipeSummary;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Comparator;

public class SortRecipesByCalories implements HttpServletCommand {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        boolean descending = Boolean.parseBoolean(req.getParameter(ApplicationConfig.getOrderingParam()));
        Comparator<RecipeSummary> comparator = Comparator.comparingLong(RecipeSummary::getCalories);

        req.setAttribute(ApplicationConfig.getRecipesParam(),
                ApplicationConfig.getRecipeService().getRecipesSorted(descending? comparator.reversed(): comparator));

        req.getRequestDispatcher(ApplicationConfig.getIndexPage()).forward(req, resp);
    }
}
