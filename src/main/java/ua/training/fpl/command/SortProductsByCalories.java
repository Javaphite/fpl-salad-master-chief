package ua.training.fpl.command;

import ua.training.fpl.Configuration;
import ua.training.fpl.dto.SaladComponent;
import ua.training.fpl.model.entity.Salad;
import ua.training.fpl.model.service.SaladService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Comparator;

public class SortProductsByCalories implements HttpServletCommand {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        SaladService service = Configuration.getSaladService();
        boolean descending = Boolean.parseBoolean(req.getParameter(Configuration.getOrderingParam()));
        int id = (Integer) req.getSession().getAttribute("saladId");
        Salad salad = Configuration.getSaladService().getSaladById(id);

        req.setAttribute("salad", salad);
        req.setAttribute("components", service.getComponentsSorted(salad,
                Comparator.comparingLong(SaladComponent::getCalories)));
        req.getRequestDispatcher(Configuration.getSaladPage()).forward(req, resp);
    }
}
