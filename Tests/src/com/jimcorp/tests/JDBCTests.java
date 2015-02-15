package com.jimcorp.tests;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;


public class JDBCTests {

	public static void main(String[] args) {
		
		String URL = "jdbc:mysql://localhost/books";
		String user = "jim";
		String pass = "karate98";
		
		
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		ResultSetMetaData metaData = null;
		String sql = "SELECT Authors.AuthorID, Authors.FirstName, Authors.LastName, AuthorISBN.ISBN from Authors "
				+ "INNER JOIN AuthorISBN ON Authors.AuthorID = AuthorISBN.AuthorID "
				+ "WHERE Authors.LastName = ?";
		
		try {
			connection = connectToDatabase(URL, user, pass);
			statement = connection.prepareStatement(sql);
			statement.setString(1, "Deitel");
			resultSet = statement.executeQuery();
			metaData = resultSet.getMetaData();
			
			System.out.printf("%-8s\t%-8s\t%-8s\t%-8s%n", "AuthorID", "FirstName", "LastName", "ISBN");
			System.out.println("-----------------------------------------------------------");
			while(resultSet.next()) {
				for(int i=1; i<=metaData.getColumnCount(); i++) {
					System.out.printf("%-8s\t", resultSet.getObject(i));
				}
				System.out.println();
			}
		} catch (SQLException sqlException) {
			System.err.println("ERROR!!! Unable to connect to database. Reason: " + sqlException.getMessage());
		} finally {
			if(connection != null) {
				try {
					connection.close();
					statement.close();
					resultSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	
	public static Connection connectToDatabase(String URL, String userName, String password) throws SQLException{
		
		Connection connection = null;
		
		connection = DriverManager.getConnection(URL, userName, password);
		
		
		return connection;
	}

}
