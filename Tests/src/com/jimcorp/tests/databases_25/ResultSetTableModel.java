package com.jimcorp.tests.databases_25;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.table.AbstractTableModel;

public class ResultSetTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;

	private Connection connection;
	private Statement statement;
	private ResultSet resultSet;
	private ResultSetMetaData metaData;
	private int numberOfRows;
	
	private boolean connectedToDatabase = false;
	
	public ResultSetTableModel(String url, String userName, String password, String query) throws SQLException {
		
		connection = DriverManager.getConnection(url, userName, password);
		statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		connectedToDatabase = true;
		
		setQuery(query);
	}
	
	
	@Override
	public Class<?> getColumnClass(int column) throws IllegalStateException {
		
		if(!connectedToDatabase) {
			throw new IllegalStateException("Not connected to database!");
		}
		
		try {
			String className = metaData.getColumnClassName(column+1);
			return Class.forName(className);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		
		return Object.class;
	}
	

	@Override
	public int getColumnCount() throws IllegalStateException {

		if(!connectedToDatabase) {
			throw new IllegalStateException("Not connected to database");
		}
		
		try {
			return metaData.getColumnCount();
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
		
		return 0;
	}
	
	
	@Override
	public String getColumnName(int column) throws IllegalStateException {
		
		if(!connectedToDatabase) {
			throw new IllegalStateException("Not connected to database");
		}
		
		try {
			return metaData.getColumnName(column+1);
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
		
		return "";
	}
	
	
	@Override
	public int getRowCount() throws IllegalStateException {
		
		if(!connectedToDatabase) {
			throw new IllegalStateException("Not connected to database");
		}
		
		return numberOfRows;
	}
	

	@Override
	public Object getValueAt(int row, int column) throws IllegalStateException {
		
		if(!connectedToDatabase) {
			throw new IllegalStateException("Not connected to database");
		}
		
		try {
			resultSet.absolute(row+1);
			return resultSet.getObject(column+1);
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
		
		return "";
	}
	
	
	public void setQuery(String query) throws SQLException, IllegalStateException {
		
		if(!connectedToDatabase) {
			throw new IllegalStateException("Not connected to database");
		}
		
		resultSet = statement.executeQuery(query);
		metaData = resultSet.getMetaData();
		resultSet.last();
		numberOfRows = resultSet.getRow();
		
		fireTableStructureChanged();
		
	}

	
	public void disconnectFromDatabase() {
		if(connectedToDatabase) {
			try {
				resultSet.close();
				statement.close();
				connection.close();
			} catch (SQLException sqlException) {
				sqlException.printStackTrace();
			} finally {
				connectedToDatabase = false;
			}
		}
	}
}
