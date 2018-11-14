package ua.training.fpl.command;

import ua.training.fpl.config.AccessConfig;
import ua.training.fpl.config.ApplicationConfig;
import ua.training.fpl.model.entity.Recipe;
import ua.training.fpl.model.entity.Salad;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class CreateSalad implements HttpServletCommand {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int recipeId = Integer.parseInt(req.getParameter(ApplicationConfig.getRecipeIdentifierParam()));
        Recipe recipe = AccessConfig.getDaoFactory().getRecipeDao().find(recipeId);
        int portions = Integer.parseInt(req.getParameter("portions"));

        Salad salad = ApplicationConfig.getSaladService().createSalad(Objects.requireNonNull(recipe), portions);
        req.getSession().setAttribute("id", salad.getId());
        resp.sendRedirect(ApplicationConfig.getSaladRedirectionLink(salad.getId()));
    }
}
