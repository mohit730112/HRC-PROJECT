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

@WebServlet("/addData")
public class addData extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		dbConnection db = new dbConnection();

		try {

			String custName = request.getParameter("customerName");
			String custNumber = request.getParameter("customerNo");
			String notes = request.getParameter("notes");
			String invoiceAmount = request.getParameter("invoiceAmount");
			long invoiceNo = Long.parseLong(request.getParameter("invoiceNo"));
			long docId = Long.parseLong(request.getParameter("docId"));
			String dueDate = request.getParameter("selectedDate");
			String postingDate = request.getParameter("postingDate");
			String docCreateDate = request.getParameter("docCreateDate");

			data newData = new data();

			
			System.out.println("INV DET"+ custName + "-" + notes + "-" + invoiceNo);
			
			newData.setCustName(custName);
			newData.setCustNumber(custNumber);
			newData.setDueInDate(dueDate);
			newData.setTotalOpenAmount(invoiceAmount);
			newData.setInvoiceId(invoiceNo);
			newData.setNotes(notes);
			newData.setDocId(docId);
			newData.setPostingDate(postingDate);
			newData.setDocCreateDate(docCreateDate);

			db.addData(newData);

			Gson gs = new Gson();

			String jsonData = gs.toJson(newData);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			out.write(jsonData);
			out.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
