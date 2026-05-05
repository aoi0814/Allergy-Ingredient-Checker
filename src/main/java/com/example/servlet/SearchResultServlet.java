package com.example.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.service.AllergyService;

@WebServlet("/search")
public class SearchResultServlet extends HttpServlet {
	private AllergyService allergyService = new AllergyService();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String dish = request.getParameter("dish");
		List<String> allergens = allergyService.allergen(dish);

		request.setAttribute("dish", dish);
		request.setAttribute("allergens", allergens);
		request.getRequestDispatcher("/searchResult.jsp").forward(request, response);
	}
	
	
}