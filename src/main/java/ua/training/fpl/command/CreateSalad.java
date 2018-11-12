package ua.training.fpl.command;

import ua.training.fpl.Configuration;
import ua.training.fpl.model.entity.Recipe;
import ua.training.fpl.model.entity.Salad;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateSalad implements HttpServletCommand {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String recipeName = req.getParameter(Configuration.getRecipeIdentifierParam());
        Recipe recipe = Configuration.getRecipeService().getRecipeByName(recipeName);
        int portions = Integer.parseInt(req.getParameter("portions"));

        Salad salad = Configuration.getSaladService().createSalad(recipe, portions);
        req.getSession().setAttribute("saladId", salad.getId());
        resp.sendRedirect(Configuration.getSaladRedirectionLink(salad.getId()));
    }
}
