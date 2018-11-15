package ua.training.fpl.command;

import ua.training.fpl.config.AccessConfig;
import ua.training.fpl.config.ApplicationConfig;
import ua.training.fpl.model.entity.Recipe;
import ua.training.fpl.model.entity.Salad;
import ua.training.fpl.util.Numbers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateSalad implements HttpServletCommand {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int recipeId = Integer.parseInt(req.getParameter(ApplicationConfig.getRecipeIdentifierParam()));
        Recipe recipe = AccessConfig.getDaoFactory().getRecipeDao().find(recipeId);
        int portions = Numbers.parseOrDefault(req.getParameter("portions"),1);
        Salad salad = ApplicationConfig.getSaladService().createSalad(recipe, portions);

        req.getSession().setAttribute("id", salad.getId());
        resp.sendRedirect(ApplicationConfig.getSaladRedirectionLink(salad.getId()));
    }
}
