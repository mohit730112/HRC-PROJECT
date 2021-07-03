package com.highradius.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.highradius.data.data;
import com.highradius.dbConnection.dbConnection;

@WebServlet("/data")
public class servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		dbConnection db = new dbConnection();
		try {
			ArrayList<data> a = db.getData();
			
			int num = Integer.parseInt(request.getParameter("dataLength"));
			
			System.out.println("NUMMMMMMMM"+num);
			
			ArrayList<data> partData = new ArrayList<>();
			
			for(int i = (num - 20) ; i < num && i < a.size() ; i++) {
				partData.add(a.get(i));	
			}
			Gson gs = new Gson();
			
			String jsonData = gs.toJson(partData);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			out.write(jsonData);
			out.close();
			
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

}
