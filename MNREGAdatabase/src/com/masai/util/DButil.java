package com.masai.util;

import java.sql.*;

//This package provide the utilities such as connection 

public class DButil {

	public static Connection getConnection() throws SQLException {
		
		Connection conn = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String url = "jdbc:mysql://localhost:3306/mnregadb";
		
		conn = DriverManager.getConnection(url, "root", "kapil");
		
		return conn;
	}
	
}
