package ua.training.fpl.controller.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Base interface for all commands used to do all the front servlet's work.
 */

@FunctionalInterface
public interface HttpServletCommand {

    void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
}
