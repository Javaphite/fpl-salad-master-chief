package ua.training.fpl.command;

import ua.training.fpl.Configuration;
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
        boolean descending = Boolean.valueOf(req.getParameter(Configuration.getOrderingParam()));
        Comparator<RecipeSummary> comparator = Comparator.comparingLong(RecipeSummary::getCalories);

        req.setAttribute(Configuration.getRecipesParam(),
                Configuration.getRecipeService().getRecipesSorted(descending? comparator.reversed(): comparator));

        req.getRequestDispatcher(Configuration.getIndexPage()).forward(req, resp);
    }
}
