package com.example.servlet;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.service.AllergyService;

@WebServlet("/alternative")
public class AlternativeServlet extends HttpServlet {
    private AllergyService allergyService = new AllergyService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String dish = request.getParameter("dish");
        String[] selectedAllergens = request.getParameterValues("allergens");
        List<String> allergens = Arrays.asList(selectedAllergens);

        String alternative = allergyService.alternative(dish, allergens);
        String alternative2 = allergyService.alternative2(dish, allergens);
        
        System.out.println(alternative2);
        request.setAttribute("alternative", alternative);
        request.setAttribute("alternative2", alternative2);
        request.getRequestDispatcher("/alternative.jsp").forward(request, response);
    }


}
