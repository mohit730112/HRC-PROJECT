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

@WebServlet("/deleteData")
public class deleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		dbConnection db = new dbConnection();
		try {

			long length = Long.parseLong(request.getParameter("length"));
			String[] a = new String[(int) length];
			long i = 0;
			while(i < length) {
				long key = i + 1;
				String getId = request.getParameter(key+"");
				a[(int) i] = getId;
				i++;
			}
			
			System.out.println("Length="+ a.length);
			db.deleteById(a);
			
			out.close();
			
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

}
