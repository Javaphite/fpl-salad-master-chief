package ua.training.fpl.command;

import ua.training.fpl.config.ApplicationConfig;
import ua.training.fpl.model.entity.Salad;
import ua.training.fpl.model.service.SaladService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class DisplaySaladDetails implements HttpServletCommand {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        SaladService service = ApplicationConfig.getSaladService();
        int id = (Integer) req.getSession().getAttribute("id");
        Salad salad = Objects.requireNonNull(service.getSaladById(id));

        req.setAttribute("saladSummary", service.getSaladSummary(salad));
        req.setAttribute("components", service.getComponentsOf(salad));
        req.getRequestDispatcher(ApplicationConfig.getSaladPage()).forward(req, resp);
    }
}
