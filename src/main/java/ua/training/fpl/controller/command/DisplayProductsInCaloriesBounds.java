package ua.training.fpl.controller.command;

import ua.training.fpl.config.ApplicationConfig;
import ua.training.fpl.model.dto.SaladComponent;
import ua.training.fpl.model.entity.Salad;
import ua.training.fpl.model.service.SaladService;
import ua.training.fpl.util.Numbers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Command to filter products in particular {@link Salad} instance
 * leaving only those ones in bounds defined in context.
 */
public class DisplayProductsInCaloriesBounds implements HttpServletCommand {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        SaladService service = ApplicationConfig.getSaladService();
        int id = (Integer) req.getSession().getAttribute("id");
        Salad salad = ApplicationConfig.getSaladService().getSaladById(id);

        List<SaladComponent> components;
        try {
            long caloriesFrom = Long.parseLong(req.getParameter("from"));
            long caloriesTo = Long.parseLong(req.getParameter("to"));
            components = service.getComponentsFiltered(salad,
                    component -> Numbers.between(component.getCalories(), caloriesFrom, caloriesTo));
        } catch(NumberFormatException exception) {
            components = service.getComponentsOf(salad);
        }

        req.setAttribute("saladSummary", service.getSaladSummary(salad));
        req.setAttribute("components", components);
        req.getRequestDispatcher(ApplicationConfig.getSaladPage()).forward(req, resp);
    }
}
