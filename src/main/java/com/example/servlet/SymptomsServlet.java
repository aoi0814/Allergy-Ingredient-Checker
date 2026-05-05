package com.example.servlet;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.service.AllergyService;

@WebServlet("/symptoms")
public class SymptomsServlet extends HttpServlet {
	private AllergyService allergyService = new AllergyService();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String[] selectedAllergens = request.getParameterValues("allergens");
		List<String> allergens = Arrays.asList(selectedAllergens);

		Map<String, Map<String, List<String>>> symptoms = allergyService.symptoms(allergens);
		

		request.setAttribute("symptoms", symptoms);
		request.getRequestDispatcher("/symptoms.jsp").forward(request, response);
	}
}