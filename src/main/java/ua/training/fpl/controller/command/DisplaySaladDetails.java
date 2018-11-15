package ua.training.fpl.controller.command;

import ua.training.fpl.config.ApplicationConfig;
import ua.training.fpl.model.entity.Salad;
import ua.training.fpl.model.service.SaladService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Command to display information about all products
 * in particular {@link Salad} instance.
 */
public class DisplaySaladDetails implements HttpServletCommand {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        SaladService service = ApplicationConfig.getSaladService();
        int id = (Integer) req.getSession().getAttribute("id");
        Salad salad = service.getSaladById(id);

        req.setAttribute("saladSummary", service.getSaladSummary(salad));
        req.setAttribute("components", service.getComponentsOf(salad));
        req.getRequestDispatcher(ApplicationConfig.getSaladPage()).forward(req, resp);
    }
}
