package classes;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import database.ConnectionDB;

public class BookManagement {

	public void showAllBooks() {
		
		ConnectionDB conn = new ConnectionDB();
		
		try {
			Statement st = conn.getConnection().createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM books");
			System.out.println("LIST OF BOOKS: ");
			while (rs.next()) {
				System.out.println("id: " + rs.getInt(1));
				System.out.println("Title: " + rs.getString(2));
				System.out.println("Description: " + rs.getString(3));
				System.out.println("Number of sheets: " + rs.getInt(4));
				System.out.println("ISBN: " + rs.getString(5));
				System.out.println("Editorial: " + rs.getString(6));
				System.out.println("Edition: " + rs.getInt(7));
				System.out.println("Date of publication: " + rs.getDate(8));
				System.out.println("id_instance: " + rs.getInt(9));
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
	
	public void createBook(String title,String descripcion,int num_sheets,String isbn, String editorial,int edition,int id_instance,int year, int month, int day) throws SQLException {
		PreparedStatement stmt = null;
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","");
		Scanner sn = new Scanner(System.in);		   
		Date dateP = new Date(year,month,day);
		
		int y = year - 1900;
        int m = month - 1;
			        
		stmt = con.prepareStatement("INSERT INTO `books` (`Title`, `Description`, `Number of sheets`, `ISBN`, `Editorial`, `Edition`,`Date of publication`, `id_instance`) VALUES (?,?,?,?,?,?,?,?);");
				   
		stmt.setString(1,title);
		stmt.setString(2,descripcion);
		stmt.setInt(3,num_sheets);
		stmt.setString(4,isbn);
		stmt.setString(5,editorial);
		stmt.setInt(6,edition);
		stmt.setDate(7,dateP);
		stmt.setInt(8,id_instance);
		stmt.executeUpdate();
	}

	public void deleteBook() throws SQLException {
		ConnectionDB conn = new ConnectionDB();
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","");
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("Introduce the id of the book: ");
            String id = sc.next();
           
            Statement st = conn.getConnection().createStatement();
            PreparedStatement ps= con.prepareStatement("DELETE FROM books WHERE id= ?");
            ps.setString(1, id);
            ps.executeUpdate();
            System.out.println("The book was eliminated. ");
           
            ps.close();
            st.close();
            conn.disconnect();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
	}
	
	public void showABook() {
		ConnectionDB conn = new ConnectionDB();
		Scanner sc = new Scanner(System.in);	
		try {
			System.out.println("Introduce the id of the book: ");
			String id = sc.next();
			if (id == null || id.equals("")) {
				System.out.println("Introduce the id of the book: ");
				return;
			}
			Statement st = conn.getConnection().createStatement();
			Statement st1 = conn.getConnection().createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM books WHERE id ='" + id +"'");
			ResultSet rs1 = st1.executeQuery("SELECT  authors.name FROM authors,books_authors,books WHERE books.id='"+ id + "' AND books_authors.id_authors = books.id AND authors.id = books_authors.id_authors");
			while (rs.next()) {
				System.out.println("id: " + rs.getInt(1));
				System.out.println("Title: " + rs.getString(2));
				System.out.println("Description: " + rs.getString(3));
				System.out.println("Number of sheets: " + rs.getInt(4));
				System.out.println("ISBN: " + rs.getString(5));
				System.out.println("Editorial: " + rs.getString(6));
				System.out.println("Edition: " + rs.getInt(7));
				System.out.println("Date of publication: " + rs.getDate(8));
				System.out.println("id_instance: " + rs.getInt(9));
				System.out.println();
			}
			while(rs1.next()){
				System.out.println("Authors: " + rs1.getString(1));
				System.out.println();
			}
			rs.close();
			st.close();
			conn.disconnect();
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void lookForBookT() {
		ConnectionDB conn = new ConnectionDB();
		Scanner sc = new Scanner(System.in);	
		try {
			System.out.println("Introduce the title of the book: ");
			String title = sc.next();
			if (title == null || title.equals("")) {
				System.out.println("Introduce the title of the book: ");
				return;
			}
			Statement st = conn.getConnection().createStatement();
			ResultSet rs = st.executeQuery("SELECT id, Title FROM books WHERE Title LIKE '%" + title +"%'");
			while (rs.next()) { 
				System.out.println("Id: " + rs.getString(1) + ", Title: " + rs.getString(2));
				System.out.println();
			}
			rs.close();
			st.close();
			conn.disconnect();
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void lookForBookA() {
		ConnectionDB conn = new ConnectionDB();
		Scanner sc = new Scanner(System.in);	
		try {
			System.out.println("Introduce the authors of the book: ");
			String authors = sc.next();
			if (authors == null || authors.equals("")) {
				System.out.println("Introduce the authors of the book: ");
				return;
			}
			Statement st = conn.getConnection().createStatement();
			ResultSet rs = st.executeQuery("SELECT  books.Title FROM authors,books_authors,books WHERE authors.name ='" + authors + "' AND books_authors.id_authors = books.id AND authors.id = books_authors.id_authors");
			while (rs.next()) { 
				System.out.println("Title: " + rs.getString(1));
			}
			rs.close();
			st.close();
			conn.disconnect();
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void modifyBook(int id,String Title, String Description, int Number_of_sheets, String ISBN) throws SQLException {
		
		ConnectionDB conn = new ConnectionDB();
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","");
        try {
            Statement st = conn.getConnection().createStatement();
            PreparedStatement ps= con.prepareStatement("UPDATE books SET `Title`=?, `Description`=?, `Number of sheets`=?, `ISBN`=?  WHERE `id`=?");
            ps.setString(1, Title);
            ps.setString(2, Description);
            ps.setInt(3, Number_of_sheets);
            ps.setString(4, ISBN);
            ps.setInt(5, id);
            ps.executeUpdate();
            ps.close();
            st.close();
            conn.disconnect();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
		
	}
	
}
