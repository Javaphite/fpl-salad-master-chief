package ua.training.fpl.controller.command;

import ua.training.fpl.config.ApplicationConfig;
import ua.training.fpl.model.dto.SaladComponent;
import ua.training.fpl.model.entity.Salad;
import ua.training.fpl.model.service.SaladService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Comparator;

/**
 * Command for sorting components of particular {@link Salad} by calories.
 */
public class SortProductsByCalories implements HttpServletCommand {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        SaladService service = ApplicationConfig.getSaladService();
        boolean descending = Boolean.parseBoolean(req.getParameter(ApplicationConfig.getOrderingParam()));
        int id = (Integer) req.getSession().getAttribute("id");
        Salad salad = ApplicationConfig.getSaladService().getSaladById(id);

        Comparator<SaladComponent> comparator = Comparator.comparingLong(SaladComponent::getCalories);
        req.setAttribute("saladSummary", service.getSaladSummary(salad));
        req.setAttribute("components",
                service.getComponentsSorted(salad, descending? comparator.reversed(): comparator));

        req.getRequestDispatcher(ApplicationConfig.getSaladPage()).forward(req, resp);
    }
}
