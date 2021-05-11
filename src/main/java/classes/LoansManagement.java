package classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import database.ConnectionDB;

public class LoansManagement {
	
	public void showLoans() {
		ConnectionDB conn = new ConnectionDB();
		try {
			Statement st = conn.getConnection().createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM loans");
			System.out.println("LIST OF LOANS: \n");
			System.out.printf("%1s  %12s   %20s   %30s", "id", "User id", "Departure date ", "Maximum date to return" + "\n");
			System.out.printf("----------------------------------------------------------------------------------\n");
			while (rs.next()) {
				System.out.printf("%2d  %10s   %25s   %25s \n", rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
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
			System.out.printf("%1s  %12s   %20s   %30s", "id", "User id", "Departure date ", "Maximum date to return" + "\n");
			System.out.printf("----------------------------------------------------------------------------------\n");
			while (rs.next()) {
				System.out.printf("%2d  %10s   %25s   %25s \n", rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
			}
			rs.close();
			st.close();
			conn.disconnect();
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	 public void createALoan(int userId,String Departure_date,String Maximum_date_to_return) throws SQLException {
         PreparedStatement stmt = null;
         Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","");
         Scanner sn = new Scanner(System.in);       
                            
         stmt = con.prepareStatement("INSERT INTO `loans` (`User ID`, `Departure date`, `Maximum date to return`) VALUES (?,?,?);");
                   
         stmt.setInt(1,userId);
         stmt.setString(2,Departure_date);
         stmt.setString(3,Maximum_date_to_return);
         stmt.executeUpdate();
        
         System.out.println("The loan was created.");
     }
	 
	 public static String getCurrentDateTime() {
         String date_format = "yyyy-MM-dd HH:mm:ss";
         DateTimeFormatter formatter = DateTimeFormatter.ofPattern(date_format);
         LocalDateTime dateTimeNow = LocalDateTime.now();
         return formatter.format(dateTimeNow);
     }
    
  public void returnLoan() {
      ConnectionDB conn = new ConnectionDB();
         Scanner sc = new Scanner(System.in);   
         try {
             System.out.println("Introduce the user's name: ");
             String title = sc.next();
             if (title == null || title.equals("")) {
                 System.out.println("Introduce the user's name:");
                 return;
             }
             Statement st = conn.getConnection().createStatement();
             ResultSet rs = st.executeQuery("SELECT `id` FROM users WHERE `Name` LIKE '%" + title +"%'");
             while (rs.next()) {
                 System.out.println("Id: " + rs.getString(1));
             }
             System.out.println("Introduce it's corresponding id: ");
             String id = sc.next();
             if (id == null || id.equals("")) {
                 System.out.println("Introduce it's corresponding id: ");
                 return;
             }
             ResultSet rh = st.executeQuery("SELECT books.Title, loans.id\r\n"
                     + "FROM loans\r\n"
                     + "JOIN instance ON instance.id =loans.`instance_id`\r\n"
                     + "JOIN books ON books.id = instance.id_books\r\n"
                     + "WHERE `User ID` LIKE '%" + id +"%'");
             while (rh.next()) {
                 System.out.println("Title: " + rh.getString(1) + " Loan: " + rh.getString(2));
             }
             System.out.println("Choose a loan: ");
             String loan = sc.next();
             if (loan == null || loan.equals("")) {
                 System.out.println("Introduce it's corresponding id: ");
                 return;
             }
             String dateHour = getCurrentDateTime();
             System.out.println(dateHour);
             st.executeUpdate("UPDATE `loans` SET `Return date`='" + dateHour +"' WHERE `id`='" + loan +"'");
             System.out.println("The loan was returned. ");
             rs.close();
             rh.close();
             st.close();
             conn.disconnect();
         }catch (SQLException e) {
             System.out.println(e.getMessage());
         }
  	}
}