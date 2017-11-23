package com.training.restapi.oracle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static com.training.restapi.constant.ApiConstant.*;

/**
 * @author Sushmit
 * Class to get Connection to database server.
 */
public class OracleConn {
	public static Connection conn;
	public static Connection getConnection() {
		
		try {
			Class.forName(Driver_NAME);
			conn=DriverManager.getConnection(URL,USER_NAME, PASSWORD);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
}
