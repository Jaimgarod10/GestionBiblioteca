package classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import database.ConnectionDB;

public class BooksManagement {
	
	public String ValidationIsbn(String isbn) {
		Scanner sc = new Scanner(System.in);
        do {
        	if(isbn.matches("^[0-Z]{13}$")) {
                System.out.println("ISBN is valid");
                break;
            } else {
                System.out.println("ISBN is invalid. Re-enter the isbn.");
                isbn = sc.next();
            }
        }while(true);
        return isbn;
	}
	

	public void showAllBooks() {
		
		ConnectionDB conn = new ConnectionDB();
	       
        try {
            Statement st = conn.getConnection().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM books");
            System.out.println("LIST OF BOOKS: \n");
            System.out.printf("%1s  %12s   %15s   %25s", "id", "Title", "ISBN", "Description" + "\n");
            System.out.printf("----------------------------------------------------------------------------------\n");
            while (rs.next()) {
                System.out.printf("%2d  %15s   %15s   %20s \n", rs.getInt(1), rs.getString(2), rs.getString(5), rs.getString(3));
            }
            rs.close();
            st.close();
            conn.disconnect();       
        }catch (SQLException e) {
               System.out.println(e.getMessage());
        }
        return ;
	}
	
	public void showAllAuthors() {
		
		ConnectionDB conn = new ConnectionDB();
		
		try {
			Statement st = conn.getConnection().createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM authors");
			System.out.println("List of Authors: ");
			while (rs.next()) {
				System.out.println("id: " + rs.getInt(1));
				System.out.println("Name: " + rs.getString(2));
				System.out.println("Surname: " + rs.getString(3));
				System.out.println("Age: " + rs.getInt(4));
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
	
	public void createBook(String title, String description, int number_of_sheets, String isbn, String editorial, int edition, String date_of_publication, int id_author) throws SQLException {
       ConnectionDB conn = new ConnectionDB();
       Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","");
       Scanner sc = new Scanner(System.in);
       try {
           Statement st = conn.getConnection().createStatement();
           PreparedStatement ps= con.prepareStatement("INSERT INTO `books` (`Title`, `Description`, `Number of sheets`, `ISBN`, `Editorial`, `Edition`,`Date of publication`, `id_instance`) VALUES (?,?,?,?,?,?,?,?);");
           ps.setString(1, title);
           ps.setString(2, description);
           ps.setInt(3, number_of_sheets);
           ps.setString(4, isbn);
           ps.setString(5, editorial);
           ps.setInt(6, edition);
           ps.setString(7, date_of_publication);
           ps.setInt(8, id_author);
           ps.executeUpdate();
           System.out.println("The book was created. ");
           ps.close();
           st.close();
           conn.disconnect();
       }catch (SQLException e) {
           System.out.println(e.getMessage());
       }
   }

	public void deleteBook() throws SQLException {
		ConnectionDB conn = new ConnectionDB();
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","");
		Scanner sc = new Scanner(System.in);
		try {
			System.out.println("Introduce the id of the book: ");
			String id = sc.next();
			
			Statement st = conn.getConnection().createStatement();
			PreparedStatement ps= con.prepareStatement("DELETE FROM books WHERE id='" + id +"'");
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
				System.out.println("id_author: " + rs.getInt(9));
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
	public void modifyBook(int id, String title, String description, int number_of_sheets, String isbn, int editorial, int edition, String date_of_publication) throws SQLException {
		ConnectionDB conn = new ConnectionDB();
	    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","");
	    Scanner sc = new Scanner(System.in);
	    try {
	    	Statement st = conn.getConnection().createStatement();
	    	PreparedStatement ps= con.prepareStatement("UPDATE `books` SET `Title`=?, `Description`=?, `Number of sheets`=?, `ISBN`=?, `Editorial`=?, `Edition`=?, `Date of publication`=?  WHERE `id`=?");
	    	
	    	ps.setString(1, title);
	    	ps.setString(2, description);
	    	ps.setInt(3, number_of_sheets);
	    	ps.setString(4, isbn);
	    	ps.setInt(5, editorial);
	    	ps.setInt(6, edition);
	    	ps.setString(7, date_of_publication);
	    	ps.setInt(8, id);       
            System.out.println("The book was updated."); 
	    	ps.executeUpdate();
	    	ps.close();
	    	st.close();
	    	conn.disconnect();
	    }catch (SQLException e) {
	    	System.out.println(e.getMessage());      
	    }
	}
}
