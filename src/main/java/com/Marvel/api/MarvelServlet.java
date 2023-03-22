package com.Marvel.api;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MarvelServlet extends HttpServlet{
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");

        try {
            String result = MarvelApi.getCharById(Integer.parseInt(id));
            if (Objects.equals(result, "El personaje no fue encontrado")){
                response.setContentType("text/plain");
            }
            else{
                response.setContentType("application/json");
            }
            response.getWriter().println(result);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid id");
        } catch (RuntimeException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        } catch (URISyntaxException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Invalid URL");
        } catch (InterruptedException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Request interrupted");
        }
    }
}
