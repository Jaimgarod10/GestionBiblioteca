package database;
	
import java.sql.*;

public class ConnectionDB {

	/**parameters of conection*/
	static String bd = "library";
	static String login = "root";
	static String password = "";
	static String url = "jdbc:mysql://localhost:3306/"+bd;
  
	Connection connection = null;
	
	/** Builder of Connection */
	public ConnectionDB() {
		try{
			//we obtain the driver for the mysql
			Class.forName("com.mysql.cj.jdbc.Driver");
			//we obtain the connection
			connection = DriverManager.getConnection(url,login,password);
			if (connection!=null){
				
			}else {
				System.out.println("Error: Could not connect to database");
			}
		}catch(SQLException e){
			System.out.println(e);
			}catch(ClassNotFoundException e){
				System.out.println(e);
				}catch(Exception e){
					System.out.println(e);
				}
	}
	
	/**It allows return the connection*/
	public Connection getConnection(){
		return connection;
	}
	
	public void disconnect(){
		connection = null;
	}
}