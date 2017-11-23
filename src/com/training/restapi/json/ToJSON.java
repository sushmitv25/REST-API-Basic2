package com.training.restapi.json;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.owasp.esapi.errors.EncodingException;

/**
 * @author Sushmit
 * Class to Convert the returned ResultSet from the database to JSON format.
 */
public class ToJSON {

	/**
	 * Method to convert resultset to JSON format.
	 */
	public JSONArray toJSonArray(ResultSet result) throws JSONException, EncodingException {

		JSONArray jsonArray = new JSONArray();
		String temp=null;
		try {
			ResultSetMetaData resMetaData = result.getMetaData();

			while (result.next()) {

				int numColums = resMetaData.getColumnCount();
				JSONObject jsObj = new JSONObject();

				for (int i = 1; i < numColums + 1; i++) {
					String columName = resMetaData.getColumnName(i);
					/*if (resMetaData.getColumnType(i) == java.sql.Types.VARCHAR) {
						
						temp=result.getString(columName);
						temp=ESAPI.encoder().canonicalize(temp);
						temp=ESAPI.encoder().encodeForHTML(temp);*/
						
						temp=result.getString(columName);
						jsObj.put(columName, temp);
				}
				jsonArray.put(jsObj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return jsonArray;
	}
}
