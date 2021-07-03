package com.highradius.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.highradius.data.data;
import com.highradius.dbConnection.dbConnection;

@WebServlet("/searchData")
public class searchData extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		dbConnection db = new dbConnection();

		try {
			
			long id = Long.parseLong(request.getParameter("searchedId"));
			
			data searchedData = db.searchById(id);

			Gson gs = new Gson();

			String jsonData = gs.toJson(searchedData);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			out.write(jsonData);
			out.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
