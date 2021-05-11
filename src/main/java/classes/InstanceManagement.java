package classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import database.ConnectionDB;

public class InstanceManagement {

	public void showInstances() {
		ConnectionDB conn = new ConnectionDB();
	       
        try {
            Statement st = conn.getConnection().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM instance");
            System.out.println("LIST OF INSTANCES: \n");
            System.out.printf("%1s  %12s   %15s", "id","Date of admission", "Id_book: " + "\n");
            System.out.printf("------------------------------------------------\n");
            while (rs.next()) {
                System.out.printf("%2d  %15s   %10d  \n", rs.getInt(1), rs.getString(2), rs.getInt(3));
            }
            rs.close();
            st.close();
            conn.disconnect();       
        }catch (SQLException e) {
               System.out.println(e.getMessage());
        }
        return ;
	}
	
	public void showAInstance() {
        ConnectionDB conn = new ConnectionDB();
        Scanner sc = new Scanner(System.in);    
        try {
            System.out.println("Introduce the id of the instance: ");
            String id = sc.next();
            if (id == null || id.equals("")) {
                System.out.println("Introduce the id of the instance: ");
                return;
            }
            Statement st = conn.getConnection().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM instance WHERE id ='" + id +"'");
            while (rs.next()) {
                System.out.println("id: " + rs.getInt(1));
                System.out.println("date of admission: " + rs.getDate(2));
                System.out.println("id_book: " + rs.getInt(3));
                System.out.println("quantity: " + rs.getInt(4));            
                System.out.println();
            }
            rs.close();
            st.close();
            conn.disconnect();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
	
	BooksManagement l = new BooksManagement();
  
    public void createAInstance() throws SQLException {
            PreparedStatement stmt = null;
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","");
            Scanner sc = new Scanner(System.in);   
            int opcion =0;
           
            System.out.println("Introduce the date of admision: ");
            String date_of_admission = sc.next();
           
            l.showAllBooks();
            System.out.println("Introduce the id of book: ");
            Integer id_books = sc.nextInt();
           
            System.out.println("Introduce the quantity of book: ");
            Integer quantity = sc.nextInt();
           
            do {
                stmt = con.prepareStatement("INSERT INTO `instance` (`Date of admission`, `id_books`) VALUES (?,?);");  
                stmt.setString(1,date_of_admission);
                stmt.setInt(2,id_books);
                stmt.executeUpdate();
                opcion ++;
            } while (opcion != quantity);
            System.out.println("The instances were created.");     
    }
    
    public void modifyInstance(int id_books, String Date_of_admission, int quantity) throws SQLException {
        ConnectionDB conn = new ConnectionDB();
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","");
        Scanner sc = new Scanner(System.in);
        try {
            Statement st = conn.getConnection().createStatement();
            PreparedStatement ps= con.prepareStatement("UPDATE `instance` SET `Date of admission`=?, `quantity`=?  WHERE `id_books`=?");
            ps.setString(1, Date_of_admission);
            ps.setInt(2, quantity);
            ps.setInt(3, id_books);
            ps.executeUpdate();
            System.out.println("The instance was modified. ");
            ps.close();
            st.close();
            conn.disconnect();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }   
    }
}
