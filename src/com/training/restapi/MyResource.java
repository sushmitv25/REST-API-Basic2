package com.training.restapi;

import static com.training.restapi.constant.ApiConstant.QUERY_Delete_RECORD;
import static com.training.restapi.constant.ApiConstant.QUERY_INSERT_RECORD;
import static com.training.restapi.constant.ApiConstant.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.owasp.esapi.errors.EncodingException;

import com.training.restapi.json.ToJSON;
import com.training.restapi.oracle.OracleConn;

/**
 * Author: Sushmit 
 * Class to Implement Web Service for Various Data Operations.
 */
@Path("myresource")
public class MyResource {
	/**
	 * This Resource is used to return all the records from the database in JSON
	 * format.
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getRecords() {
		String response = null;
		Connection conn = OracleConn.getConnection();
		try {
			PreparedStatement reader = conn.prepareStatement(QUERY_Retrieve_RECORD);
			ResultSet result = reader.executeQuery();

			ToJSON jsonObject = new ToJSON();
			JSONArray json_array = jsonObject.toJSonArray(result);
			response = json_array.toString();
		} catch (SQLException | EncodingException | JSONException e) {
			e.printStackTrace();
		}
		return response;
	}

	/**
	 * This Resource is used to take input from user and insert the data into
	 * the database
	 */
	@GET
	@Path("/Insert/{firstName}/{lastName}/{age}/{address}")
	@Produces(MediaType.TEXT_PLAIN)
	public String addRecords(@PathParam("firstName") String pFname, @PathParam("lastName") String pLname,
			@PathParam("age") int pAge, @PathParam("address") String pAddress) {
		int res = 0;
		try (Connection conn = OracleConn.getConnection()) {
			PreparedStatement reader = conn.prepareStatement(QUERY_INSERT_RECORD);
			reader.setString(1, pFname);
			reader.setString(2, pLname);
			reader.setInt(3, pAge);
			reader.setString(4, pAddress);

			res = reader.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}
		if (res > 0)
			return "Record Inserted Successfully....";
		else
			return "Sorry!!  Record not Inserted....";
	}

	/**
	 * This Resource is used to take input from user and Delete the particular
	 * Record from Database.
	 */
	@GET
	@Path("/Delete/{firstName}")
	@Produces(MediaType.TEXT_PLAIN)
	public String removeRecords(@PathParam("firstName") String pValue) {
		int res = 0;
		try (Connection conn = OracleConn.getConnection()) {
			PreparedStatement reader = conn.prepareStatement(QUERY_Delete_RECORD);
			reader.setString(1, pValue);

			res = reader.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}
		if (res > 0)
			return "Record Deleted Successfully....";
		else
			return "Sorry!!  Record not Found....";
	}

	/**
	 * This Resource takes input from user, Searches the Record in database and performs update operation.
	 */
	@GET
	@Path("/Update/{firstName}/{lastName}/{age}/{address}")
	@Produces(MediaType.TEXT_PLAIN)
	public String updateRecords(@PathParam("firstName") String pFname, @PathParam("lastName") String pLname,
			@PathParam("age") int pAge, @PathParam("address") String pAddress) {
		int res = 0;
		try (Connection conn = OracleConn.getConnection()) {
			PreparedStatement reader = conn.prepareStatement(QUERY_Update_RECORD);
			reader.setString(4, pFname);
			reader.setString(1, pLname);
			reader.setInt(2, pAge);
			reader.setString(3, pAddress);

			res = reader.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}
		if (res > 0)
			return "Record Updated Successfully....";
		else
			return "Sorry!!  Record not Found....";
	}

	/**
	 * This Resource takes user input and performs Search operation in the database.
	 */
	@GET
	@Path("/Search/{firstName}")
	@Produces(MediaType.APPLICATION_JSON)
	public String updateRecords(@PathParam("firstName") String pFname) {
		ResultSet res = null;
		String response = null;
		try (Connection conn = OracleConn.getConnection()) {
			PreparedStatement reader = conn.prepareStatement(QUERY_Search_RECORD);
			reader.setString(1, pFname);

			res = reader.executeQuery();
			ToJSON jsonObject = new ToJSON();
			JSONArray json_array = jsonObject.toJSonArray(res);
			response = json_array.toString();

		} catch (SQLException | EncodingException | JSONException e) {

			e.printStackTrace();
		}
		return response;
	}
}
