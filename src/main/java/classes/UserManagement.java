package classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import database.ConnectionDB;

public class UserManagement {
	
	public void showUsers() {
		ConnectionDB conn = new ConnectionDB();
		try {
			Statement st = conn.getConnection().createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM users");
			System.out.println("LIST OF USERS: ");
			while (rs.next()) {
				System.out.println("id: " + rs.getInt(1));
				System.out.println("Name: " + rs.getString(2));
				System.out.println("Username: " + rs.getString(3));
				System.out.println("Email: " + rs.getString(4));
				System.out.println("Phone: " + " +34 " + rs.getInt(5));
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
	
	public void showAUser() {
		ConnectionDB conn = new ConnectionDB();
		Scanner sc = new Scanner(System.in);	
		try {
			System.out.println("Introduce the id of the user: ");
			String id = sc.next();
			if (id == null || id.equals("")) {
				System.out.println("Introduce the id of the user: ");
				return;
			}
			Statement st = conn.getConnection().createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM users WHERE id ='" + id +"'");
			while (rs.next()) {
				System.out.println("id: " + rs.getInt(1));
				System.out.println("Name: " + rs.getString(2));
				System.out.println("Username: " + rs.getString(3));
				System.out.println("Email: " + rs.getString(4));
				System.out.println("Phone: " + " +34 " + rs.getInt(5));
				System.out.println();
			}
			rs.close();
			st.close();
			conn.disconnect();
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void createUser(String name,String surname,String email,int phone) throws SQLException{
		PreparedStatement stmt = null;
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","");
		Scanner sn = new Scanner(System.in);		   
		
		stmt = con.prepareStatement("INSERT INTO users (`Name`, `Surnames`, `Email`, `Phone`) VALUES (?,?,?,?);");
				   
		stmt.setString(1,name);
		stmt.setString(2,surname);
		stmt.setString(3,email);
		stmt.setInt(4,phone);
		stmt.executeUpdate();
	}
	
	public void deleteUser() throws SQLException {
		ConnectionDB conn = new ConnectionDB();
	    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","");
	    Scanner sc = new Scanner(System.in);
	    try {
	        System.out.println("Introduce the id of the user: ");
	        String id = sc.next();
	       
	        Statement st = conn.getConnection().createStatement();
	        PreparedStatement ps= con.prepareStatement("DELETE FROM users WHERE id= ?");
	        ps.setString(1, id);
	        ps.executeUpdate();
	        System.out.println("The user was eliminated succesfuly. ");
	       
	        ps.close();
	        st.close();
	        conn.disconnect();
	    }catch (SQLException e) {
	        System.out.println(e.getMessage());
	    }
	}
}