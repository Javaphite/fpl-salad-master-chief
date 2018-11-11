package ua.training.fpl.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface HttpServletCommand {

    void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
}
