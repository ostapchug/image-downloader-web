package com.example.imagedownloaderjsp.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.imagedownloaderjsp.model.Parser;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/search")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = request.getParameter("url"); 
		
		Parser parser = new Parser();
		List<String> imageURLs = parser.getImageURLs(url);
		String [] images = imageURLs.toArray(new String[imageURLs.size()]);
		
		request.setAttribute("images", images);
				
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/images.jsp");  
		requestDispatcher.forward(request, response); 
	}

}
