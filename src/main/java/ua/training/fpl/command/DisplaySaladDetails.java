package ua.training.fpl.command;

import ua.training.fpl.config.ApplicationConfig;
import ua.training.fpl.model.entity.Salad;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DisplaySaladDetails implements HttpServletCommand {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int id = (Integer) req.getSession().getAttribute("id");
        Salad salad = ApplicationConfig.getSaladService().getSaladById(id);

        req.setAttribute("salad", salad);
        req.setAttribute("components", ApplicationConfig.getSaladService().getComponentsOf(salad));
        req.getRequestDispatcher(ApplicationConfig.getSaladPage()).forward(req, resp);
    }
}
