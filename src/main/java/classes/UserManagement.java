package classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import database.ConnectionDB;

public class UserManagement {
	
	public void showUsers() {
		ConnectionDB conn = new ConnectionDB();
		try {
			Statement st = conn.getConnection().createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM users");
			System.out.println("LIST OF USERS: \n");
			System.out.printf("%1s  %10s   %10s   %15s", "id", "Name", "Surname", "Email" + "\n");
			System.out.printf("-------------------------------------------------------------\n");
			while (rs.next()) {
				System.out.printf("%2d  %10s   %10s   %20s \n", rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
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
			System.out.printf("%1s  %10s   %10s   %15s   %20s", "id", "Name", "Surname", "Email", "Phone" + "\n");
			System.out.printf("------------------------------------------------------------------------------\n");
			while (rs.next()) {
				System.out.printf("%2d  %10s   %10s   %20s   %20s\n", rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), "+34 " + rs.getString(5));
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
		Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		Matcher mather = pattern.matcher(email);
        if (mather.find() == true) {
            System.out.println("The email entered is valid.");
            stmt = con.prepareStatement("INSERT INTO `users` (`Name`, `Surnames`, `Email`, `Phone`) VALUES (?,?,?,?);");
			   
    		stmt.setString(1,name);
    		stmt.setString(2,surname);
    		stmt.setString(3,email);
    		stmt.setInt(4,phone);
    		stmt.executeUpdate();
    		System.out.println("The user was created.");
        } else {
            System.out.println("The email entered is invalid.");
            return;
        }
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
	
	public String validationEmail(String email) {
		Scanner sc = new Scanner(System.in);
        do {
        	if(email.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
                System.out.println("Email is valid");
                break;
            } else {
                System.out.println("Email is invalid. Re-enter the email.");
                email = sc.next();
            }
        }while(true);
        return email;
	}
	
	public String validationPhone(String phone) {
		Scanner sc = new Scanner(System.in);
        do {
        	if(phone.matches("^[6789]\\d{8}$")) {
                System.out.println("Phone is valid");
                break;
            } else {
                System.out.println("Phone is invalid. Re-enter the phoney.");
                phone = sc.next();   
            }
        }while(true);
        return phone;
	}
}