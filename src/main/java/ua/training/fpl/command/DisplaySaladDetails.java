package ua.training.fpl.command;

import ua.training.fpl.Configuration;
import ua.training.fpl.model.entity.Salad;
import ua.training.fpl.model.service.SaladService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DisplaySaladDetails implements HttpServletCommand {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int id = (Integer) req.getSession().getAttribute("saladId");
        Salad salad = Configuration.getSaladService().getSaladById(id);

        req.setAttribute("salad", salad);
        req.setAttribute("components", Configuration.getSaladService().getComponentsOf(salad));
        req.getRequestDispatcher(Configuration.getSaladPage()).forward(req, resp);
    }
}
