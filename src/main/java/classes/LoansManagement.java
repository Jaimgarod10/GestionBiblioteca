package classes;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import database.ConnectionDB;

public class LoansManagement {
	
	public void showLoans() {
		ConnectionDB conn = new ConnectionDB();
		try {
			Statement st = conn.getConnection().createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM loans");
			System.out.println("LIST OF LOANS: ");
			while (rs.next()) {
				System.out.println("id: " + rs.getInt(1));
				System.out.println("User id: " + rs.getInt(2));
				System.out.println("Departure date: " + rs.getDate(3) + ", " + rs.getTime(3));
				System.out.println("Maximum date to return: " + rs.getDate(4) + ", " + rs.getTime(4));
				System.out.println("Return date: " + rs.getDate(5) + ", " + rs.getTime(5));
				System.out.println("Date of loans: " + rs.getDate(6));
				System.out.println("Date of devolution: " + rs.getDate(7));
				System.out.println();
			}
			rs.close();
			st.close();
			conn.disconnect();		
		}catch (SQLException e) {
			   System.out.println(e.getMessage());
		}
		return ;
	}
	
	public void showALoan() {
		ConnectionDB conn = new ConnectionDB();
		Scanner sc = new Scanner(System.in);	
		try {
			System.out.println("Introduce the id of the loan: ");
			String id = sc.next();
			if (id == null || id.equals("")) {
				System.out.println("Introduce the id of the loan: ");
				return;
			}
			Statement st = conn.getConnection().createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM loans WHERE id ='" + id +"'");
			while (rs.next()) {
				System.out.println("id: " + rs.getInt(1));
				System.out.println("User id: " + rs.getInt(2));
				System.out.println("Departure date: " + rs.getDate(3) + ", " + rs.getTime(3));
				System.out.println("Maximum date to return: " + rs.getDate(4) + ", " + rs.getTime(4));
				System.out.println("Return date: " + rs.getDate(5) + ", " + rs.getTime(5));
				System.out.println("Date of loans: " + rs.getDate(6));
				System.out.println("Date of devolution: " + rs.getDate(7));
				System.out.println();
			}
			rs.close();
			st.close();
			conn.disconnect();
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void createLoan() {
		
	}
	
}