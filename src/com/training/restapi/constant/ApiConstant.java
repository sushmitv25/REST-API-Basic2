package com.training.restapi.constant;

/**
 * @author Sushmit
 * Class for Constant values used in application.
 */
public class ApiConstant {
	public static final String Driver_NAME = "oracle.jdbc.driver.OracleDriver";
	public static final String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
	public static final String USER_NAME = "c##TRAINING_DATA";
	public static final String PASSWORD = "admin123";
	public static final String QUERY_Retrieve_RECORD = "select * from gen_info";
	public static final String QUERY_INSERT_RECORD = "insert into gen_info values(?,?,?,?)";
	public static final String QUERY_Delete_RECORD = "delete from gen_info where first_name=?";
	public static final String QUERY_Update_RECORD = "update gen_info set last_name=?,age=?,address=? where first_name=? ";
	public static final String QUERY_Search_RECORD = "select * from gen_info where first_name=?";
}
