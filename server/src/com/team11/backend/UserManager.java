package com.team11.backend;

import com.google.gson.JsonObject;
import com.team11.backend.DatabaseHandler;

/**
 * This class manages all users. 
 */
public class UserManager {

	private static final String USERNAME = "username";
	private static final String PASSWORD = "password";
	private static final String NEW_PASSWORD = "new_password";
	private static final String FIRST_NAME = "first_name";
	private static final String LAST_NAME = "last_name";
	private static final String ADMIN_PRIVILEGES = "admin_privileges";

	public UserManager() {

	}

	public static JsonObject changePassword(JsonObject requestJson) {
		JsonObject responseJson = new JsonObject();
		if (!requestJson.has(USERNAME) || !requestJson.has(NEW_PASSWORD)) {
			responseJson = RequestHandler.getStatusFailed();
			return responseJson;
		}
		String username = requestJson.get(USERNAME).getAsString();
		String newPassword = requestJson.get(NEW_PASSWORD).getAsString();
		
		boolean success = DatabaseHandler.changePassword(username, newPassword);
		responseJson = success ? RequestHandler.getStatusSuccess() : RequestHandler.getStatusFailed();
		return responseJson;
	}

	public static JsonObject createUser(JsonObject requestJson) {
		JsonObject responseJson = new JsonObject();
		if (!requestJson.has(USERNAME) || !requestJson.has(PASSWORD) || !requestJson.has(FIRST_NAME) || !requestJson.has(LAST_NAME) || !requestJson.has(ADMIN_PRIVILEGES)) {
			responseJson = RequestHandler.getStatusFailed();
			return responseJson;
		}
		String username = requestJson.get(USERNAME).getAsString();
		String password = requestJson.get(PASSWORD).getAsString();
		String firstName = requestJson.get(FIRST_NAME).getAsString();
		String lastName = requestJson.get(LAST_NAME).getAsString();
		boolean adminPrivileges = requestJson.get(ADMIN_PRIVILEGES).getAsBoolean();
		
		boolean success = DatabaseHandler.insertUser(username, password, firstName, lastName, adminPrivileges);
		responseJson = success ? RequestHandler.getStatusSuccess() : RequestHandler.getStatusFailed();
		return responseJson;
	}

	public static JsonObject deleteUser(JsonObject requestJson) {
		JsonObject responseJson = new JsonObject();
		if (!requestJson.has(USERNAME)) {
			responseJson = RequestHandler.getStatusFailed();
			return responseJson;
		}
		String username = requestJson.get(USERNAME).getAsString();
		
		boolean success = DatabaseHandler.deleteUser(username);
		responseJson = success ? RequestHandler.getStatusSuccess() : RequestHandler.getStatusFailed();
		return responseJson;
	}

	public static JsonObject listUser(JsonObject requestJson) {
		JsonObject responseJson = new JsonObject();
		responseJson.add("Users", DatabaseHandler.getUsers());
		return responseJson;
	}
}
