package com.jimcorp.tests.databases_25;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class DisplayAuthors {

	private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/books";
	private static final String USER = "jim";
	private static final String PASS = "karate98";
	
	public static void main(String[] args) {
		
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		String sql = "SELECT AuthorID, FirstName, LastName FROM Authors";
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			
			connection = DriverManager.getConnection(DATABASE_URL, USER, PASS);
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			
			ResultSetMetaData metaData = resultSet.getMetaData();
			int numberOfColumns = metaData.getColumnCount();
			System.out.println("Authors table of Books Database:\n");
			
			for(int i=1; i<=numberOfColumns; i++) {
				System.out.printf("%-8s\t", metaData.getColumnName(i));
			}
			System.out.println();
			
			while(resultSet.next()) {
				for(int i=1; i<=numberOfColumns; i++) {
					System.out.printf("%-8s\t", resultSet.getObject(i));
				}
				System.out.println();
			}
			
		} catch(SQLException sqlException) {
			sqlException.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				resultSet.close();
				statement.close();
		//		connection.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}
