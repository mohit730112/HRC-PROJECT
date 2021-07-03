package com.highradius.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.highradius.data.data;
import com.highradius.dbConnection.dbConnection;

@WebServlet("/edit")
public class editServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		dbConnection db = new dbConnection();
		try {

			long length = Long.parseLong(request.getParameter("length"));
			String notes = request.getParameter("notes");
			String totalOpenAmount = request.getParameter("amount");
			
			String[] a = new String[(int) length];
			long i = 0;
			while(i < length) {
				long key = i + 1;
				String getId = request.getParameter(key+"");
				a[(int) i] = getId;
				i++;
			}
			
			
			
			System.out.println("Length="+ a.length);
//			System.out.println("notes:"+notes + "-" + "toa: "+ totalOpenAmount);
			db.editData(a,notes,totalOpenAmount);
			
			out.close();
			
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

}
