package com.highradius.dbConnection;

import java.sql.*;
import java.util.ArrayList;

import com.highradius.data.data;

public class dbConnection {
	private static String driver = "com.mysql.cj.jdbc.Driver";
	private static final String url = "jdbc:mysql://localhost:3306/h2h_internship";
	private static final String user = "root";
	private static final String pass = "root";

	public ArrayList<data> getData() throws Exception {
		Class.forName(driver);
		Connection con = DriverManager.getConnection(url, user, pass);
		Statement st = con.createStatement();

		String query = "SELECT name_customer,invoice_id,doc_id,due_in_date,notes,total_open_amount,cust_number FROM invoice_details";
		ResultSet rs = st.executeQuery(query);

		ArrayList<data> a = new ArrayList<>();
		while (rs.next()) {
			data p = new data();
			p.setCustName(rs.getString(1));
			p.setInvoiceId(rs.getLong(2));
			p.setDocId(rs.getLong(3));
			p.setDueInDate(String.valueOf(rs.getDate(4)));
			p.setNotes(rs.getString(5));
			p.setTotalOpenAmount(rs.getString(6));
			p.setCustNumber(rs.getString(7));
			a.add(p);
		}
		return a;

	}

	public void deleteById(String[] id) throws Exception {
		Class.forName(driver);
		Connection con = DriverManager.getConnection(url, user, pass);

		String query = "DELETE FROM invoice_details where invoice_id IN" + "(";

		for (int i = 0; i < id.length; i++) {
			if (i == id.length - 1) {
				query += id[i];
			} else {
				query += id[i] + ",";
			}
		}

		query += ")";

		PreparedStatement statement = con.prepareStatement(query);

		statement.addBatch();
		statement.executeBatch();

	}

	public data searchById(long id) throws Exception {

		Class.forName(driver);
		Connection con = DriverManager.getConnection(url, user, pass);
		Statement st = con.createStatement();

		String query = "SELECT * FROM invoice_details where invoice_id=" + id;
		ResultSet rs = st.executeQuery(query);

		data searchedData = new data();
		boolean flag = false;
		while (rs.next()) {
			searchedData.setCustName(rs.getString(3));
			searchedData.setCustNumber(rs.getString(2));
			searchedData.setDocId(rs.getLong(6));
			searchedData.setInvoiceId(rs.getLong(17));
			searchedData.setTotalOpenAmount(rs.getString(14));
			searchedData.setInvoiceCurrency(rs.getString(10));
			searchedData.setDueInDate(rs.getString(9));
			searchedData.setNotes(rs.getString(19));
			flag = true;
		}

		if (flag) {
			return searchedData;
		} else {
			return null;
		}
	}

	public void addData(data newData) throws Exception {

		Class.forName(driver);
		Connection con = DriverManager.getConnection(url, user, pass);

		String query = "INSERT INTO invoice_details (cust_number, name_customer, due_in_date, total_open_amount, invoice_id,notes,doc_id,posting_date,document_create_date)"
				+ "VALUES(?, ?, ?, ?, ?, ?, ?,?, ?)";

		PreparedStatement statement = con.prepareStatement(query);

		statement.setString(1, newData.getCustNumber());
		statement.setString(2, newData.getCustName());
		statement.setString(4, newData.getTotalOpenAmount());
		statement.setLong(5, newData.getInvoiceId());
		statement.setString(6, newData.getNotes());
		statement.setLong(7, newData.getDocId());
		Date date = Date.valueOf(newData.getDueInDate());
		statement.setDate(3, date);

		Date postingDate = Date.valueOf(newData.getPostingDate());
		statement.setDate(8, postingDate);

		Date docCreateDate = Date.valueOf(newData.getDocCreateDate());
		statement.setDate(9, docCreateDate);

		statement.addBatch();
		statement.executeBatch();

	}

	public void editData(String[] a, String notes, String amount) throws Exception {
		Class.forName(driver);
		Connection con = DriverManager.getConnection(url, user, pass);
		String query = "UPDATE invoice_details SET " + "total_open_amount='" + amount + "' , " + "notes='" + notes
				+ "' WHERE invoice_id IN " + "(";
		for (int i = 0; i < a.length; i++) {
			if (i == a.length - 1) {
				query += a[i];
			} else {
				query += a[i] + ",";
			}
		}
		query += ")";

//		System.out.println(query);

		PreparedStatement statement = con.prepareStatement(query);
		statement.addBatch();
		statement.executeBatch();
	}

}
