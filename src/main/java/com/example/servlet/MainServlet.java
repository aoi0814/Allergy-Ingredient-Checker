package com.example.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/")
public class MainServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String requestUri = request.getRequestURI();
        String contextPath = request.getContextPath();
        String path = requestUri.substring(contextPath.length());

        // Let container serve static assets under /css, /js, /images.
        if (path.startsWith("/css/") || path.startsWith("/js/") || path.startsWith("/images/")) {
            request.getServletContext().getNamedDispatcher("default").forward(request, response);
            return;
        }
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
