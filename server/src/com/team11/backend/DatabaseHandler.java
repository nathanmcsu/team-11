package com.team11.backend;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mysql.jdbc.Statement;
import com.team11.backend.RequestHandler;

import java.sql.PreparedStatement;

/**
 * This class handles all queries to the database
 */
public class DatabaseHandler {
	// JDBC driver and database name
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/United_Way_Andar_DB";
	// Database credentials
	static final String USER = "root";
	static final String PASS = "root";
	// Database column numbers
	static final int FIRST = 1;
	static final int SECOND = 2;
	static final int THIRD = 3;
	static final int FOURTH = 4;
	static final int FIFTH = 5;
	static final int SIXTH = 6;
	static final int SEVENTH = 7;
	static final int EIGHTH = 8;
	static final int NINTH = 9;
	static final int TENTH = 10;
	
	public DatabaseHandler() {

	}
	
	// InventoryOutput Queries
	public static boolean insertInventoryOutput(String funds, String focus, String outcome, String funding, int programAndar, float yearlyAllocation, String grantStart, String grantEnd, String description, String planner) {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "INSERT INTO AndarDataOutput (funds, focus, outcome, funding, program_andar, yearly_allocation, grant_start, grant_end, description, planner, grant_year) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ON DUPLICATE KEY UPDATE funds = VALUES(funds), focus= VALUES(focus), outcome= VALUES(outcome), funding= VALUES(funding), yearly_allocation = VALUES(yearly_allocation), grant_start = VALUES(grant_start), grant_end= VALUES(grant_end), description= VALUES(description), planner= VALUES(planner)";
		boolean success = true;
		try {
			conn = getConnection();

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, funds);
			stmt.setString(2, focus);
			stmt.setString(3, outcome);
			stmt.setString(4, funding);
			stmt.setInt(5, programAndar);
			stmt.setFloat(6, yearlyAllocation);
			stmt.setString(7, grantStart);
			stmt.setString(8, grantEnd);
			stmt.setString(9, description);
			stmt.setString(10, planner);
			stmt.setString(11, grantStart.substring(0,4));
			int count = stmt.executeUpdate();
			success = count > 0;
		} catch (SQLException e) {
			System.out.println("Fail: insertInventoryOutput");
			success = false;
		} catch (ClassNotFoundException e) {
			success = false;
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// Do nothing
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// Do nothing
				}
			}
		}
		
		return success;
	}
	
	public static boolean insertTargetPopulation(int programAndar, String population) {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		String sql = "INSERT INTO TargetPopulation (andar_id, population) VALUES (?, ?) ON DUPLICATE KEY UPDATE population = population";
		boolean success = true;
		try {
			conn = getConnection();

			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, programAndar);
			stmt.setString(2, population);
			int count = stmt.executeUpdate();
			success = count > 0;
		} catch (SQLException e) {
			System.out.println("Fail: insertTargetPop");
			success = false;
		} catch (ClassNotFoundException e) {
			success = false;
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// Do nothing
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// Do nothing
				}
			}
		}
		
		return success;
	}
	
	public static boolean insertProgramElement(int programAndar, String element, int level) {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "INSERT INTO ProgramElement (andar_id, element, level) VALUES (?, ?, ?) ON DUPLICATE KEY UPDATE level=VALUES(level)";
		boolean success = true;
		try {
			conn = getConnection();

			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, programAndar);
			stmt.setString(2, element);
			stmt.setInt(3, level);
			int count = stmt.executeUpdate();
			success = count > 0;
		} catch (SQLException e) {
			System.out.println("Fail: insertProgramElement");
			success = false;
		} catch (ClassNotFoundException e) {
			success = false;
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// Do nothing
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// Do nothing
				}
			}
		}
		
		return success;
	}
	
	public static boolean insertProgramSubElement(int programAndar, String element, String subElement) {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "INSERT INTO ProgramSubElement (andar_id, element, subElement) VALUES (?, ?, ?) ON DUPLICATE KEY UPDATE andar_id=andar_id";
		boolean success = true;
		try {
			conn = getConnection();

			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, programAndar);
			stmt.setString(2, element);
			stmt.setString(3, subElement);
			int count = stmt.executeUpdate();
			success = count > 0;
		} catch (SQLException e) {
			System.out.println("Fail: insertProgramSubElement");
			success = false;
		} catch (ClassNotFoundException e) {
			success = false;
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// Do nothing
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// Do nothing
				}
			}
		}
		
		return success;
	}
	
	public static boolean insertGeoArea(int programAndar, String area, int level) {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "INSERT INTO GeoArea (andar_id, area, level) VALUES (?, ?, ?) ON DUPLICATE KEY UPDATE level=VALUES(level)";
		boolean success = true;
		try {
			conn = getConnection();

			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, programAndar);
			stmt.setString(2, area);
			stmt.setInt(3, level);
			int count = stmt.executeUpdate();
			success = count > 0;
		} catch (SQLException e) {
			System.out.println("Fail: insertGeo");
			success = false;
		} catch (ClassNotFoundException e) {
			success = false;
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// Do nothing
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// Do nothing
				}
			}
		}
		
		return success;
	}
	
	public static boolean insertMunicipality(int programAndar, String municipality, int focusPercent) {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "INSERT INTO Municipality (andar_id, municipality, focus_percentage) VALUES (?, ?, ?) ON DUPLICATE KEY UPDATE focus_percentage = VALUES(focus_percentage)";
		boolean success = true;
		try {
			conn = getConnection();

			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, programAndar);
			stmt.setString(2, municipality);
			stmt.setInt(3, focusPercent);
			int count = stmt.executeUpdate();
			success = count > 0;
		} catch (SQLException e) {
			System.out.println("Fail: insertMuncipality");
			success = false;
		} catch (ClassNotFoundException e) {
			success = false;
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// Do nothing
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// Do nothing
				}
			}
		}
		
		return success;
	}
	
	public static boolean insertDonorEngagement(int programAndar, String engagement, String description) {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "INSERT INTO DonorEngagement (andar_id, engagement, description) VALUES (?, ?, ?) ON DUPLICATE KEY UPDATE description = VALUES(description)";
		boolean success = true;
		try {
			conn = getConnection();

			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, programAndar);
			stmt.setString(2, engagement);
			stmt.setString(3, description);
			int count = stmt.executeUpdate();
			success = count > 0;
		} catch (SQLException e) {
			System.out.println("Fail: insertDonor");
			success = false;
		} catch (ClassNotFoundException e) {
			success = false;
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// Do nothing
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// Do nothing
				}
			}
		}
		
		return success;
	}
	
	public static boolean insertOutput(int programAndar, String grantStart_year, String type, int value) {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "INSERT INTO Outputs (andar_id, grant_year, type, value) VALUES (?, ?, ?, ?) ON DUPLICATE KEY UPDATE value=VALUES(value)";
		boolean success = true;
		try {
			conn = getConnection();

			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, programAndar);
			stmt.setString(2, grantStart_year);
			stmt.setString(3, type);
			stmt.setInt(4, value);
			int count = stmt.executeUpdate();
			success = count > 0;
		} catch (SQLException e) {
			System.out.println("Fail: insertOutput");
			success = false;
		} catch (ClassNotFoundException e) {
			success = false;
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// Do nothing
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// Do nothing
				}
			}
		}
		
		return success;
	}

	public static boolean insertAreaDirectory(String geoArea, String municipality) {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "INSERT INTO AreaDirectory (geoArea, municipality) VALUES (?, ?) ON DUPLICATE KEY UPDATE municipality=municipality";
		boolean success = true;
		try {
			conn = getConnection();

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, geoArea);
			stmt.setString(2, municipality);
			int count = stmt.executeUpdate();
			success = count > 0;
		} catch (SQLException e) {
			System.out.println("Fail: insertAreaDirectory");
			success = false;
		} catch (ClassNotFoundException e) {
			success = false;
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// Do nothing
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// Do nothing
				}
			}
		}
		
		return success;
	}
	
	public static boolean insertAgency(int agencyAndar, String name) {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "INSERT INTO Agency (id, name) VALUES (?, ?) ON DUPLICATE KEY UPDATE name=VALUES(name)";
		boolean success = true;
		try {
			conn = getConnection();

			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, agencyAndar);
			stmt.setString(2, name);
			int count = stmt.executeUpdate();
			success = count > 0;
		} catch (SQLException e) {
			success = false;
		} catch (ClassNotFoundException e) {
			success = false;
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// Do nothing
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// Do nothing
				}
			}
		}
		
		return success;
	}
		
	public static boolean insertProgram(int programAndar, int agencyAndar, String name, String website, String description, int numLocations) {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "INSERT INTO Program (id, agency_andar, name, website, description, num_locations) VALUES (?, ?, ?, ?, ?, ?) ON DUPLICATE KEY UPDATE agency_andar=VALUES(agency_andar), name=VALUES(name), website=VALUES(website), description=VALUES(description), num_locations=VALUES(num_locations)";
		boolean success = true;
		try {
			conn = getConnection();

			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, programAndar);
			stmt.setInt(2, agencyAndar);
			stmt.setString(3, name);
			stmt.setString(4, website);
			stmt.setString(5, description);
			stmt.setInt(6, numLocations);
			int count = stmt.executeUpdate();
			success = count > 0;
		} catch (SQLException e) {
			success = false;
		} catch (ClassNotFoundException e) {
			success = false;
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// Do nothing
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// Do nothing
				}
			}
		}
		
		return success;
	}
	
	public static boolean insertLocation(int programAndar, String name, String postal) {
		
		Double lat = null, lon = null;
		
		boolean success = true;
		
		String URL = "http://maps.googleapis.com/maps/api/geocode/json?sensor=false&address=" + postal;
		URL = URL.replaceAll("\\s+","");
		System.out.println(URL);
		try {

			 InputStream URLStream = new URL(URL).openStream();
			 BufferedReader reader = new BufferedReader(new InputStreamReader(URLStream));
		     StringBuffer buffer = new StringBuffer();
		     int read;
		     char[] chars = new char[1024];
		     while ((read = reader.read(chars)) != -1){
		        buffer.append(chars, 0, read); 
		     }
		     
		     String jsonText = buffer.toString();
		    
		     System.out.println(jsonText);
		     
		     JsonObject json = new JsonObject();
		     json = new JsonParser().parse(jsonText).getAsJsonObject();
		     
		     if(json != null && json.get("status").getAsString().equals("OK")) {
			     JsonArray results = json.get("results").getAsJsonArray();
			     JsonObject temp = results.get(0).getAsJsonObject();
			     temp = temp.get("geometry").getAsJsonObject().get("location").getAsJsonObject();
			     
			     lat = temp.get("lat").getAsDouble();
			     lon = temp.get("lng").getAsDouble(); 
		     }
		     
			Connection conn = null;
			PreparedStatement stmt = null;
			String sql = "REPLACE INTO Location (andar_id, name, postal, lat, lon) VALUES (?, ?, ?, ?, ?)";

			try {
				conn = getConnection();
	
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, programAndar);
				stmt.setString(2, name);
				if (postal.equals("")) {
					stmt.setNull(3, Types.VARCHAR);
					stmt.setNull(4, Types.DOUBLE);
					stmt.setNull(5, Types.DOUBLE);
				} else if (lat == null || lon == null){
					stmt.setString(3, postal);
					stmt.setNull(4, Types.DOUBLE);
					stmt.setNull(5, Types.DOUBLE);
				} else {
					stmt.setString(3, postal);
					stmt.setDouble(4, lat);
					stmt.setDouble(5, lon);
				}
				stmt.executeUpdate();
				success = true;
			} catch (SQLException e) {
				success = false;
			} catch (ClassNotFoundException e) {
				success = false;
			} finally {
				if (stmt != null) {
					try {
						stmt.close();
					} catch (SQLException e) {
						// Do nothing
					}
				}
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						// Do nothing
					}
				}
			}
		
		} catch (IOException e) {
			// Do nothing
		}
		return success;
	}
	
	// Clear the database
	public static JsonObject clearDatabase() {

		//String TurnOffSafeUpdates = "SET SQL_SAFE_UPDATES=0;";
		//String TurnOnSafeUpdates = "SET SQL_SAFE_UPDATES=1;";
		
		String clearAndarData = "DELETE FROM AndarDataOutput";
		String clearAreaDir = "DELETE FROM AreaDirectory";
		String clearDonorEngagement = "DELETE FROM DonorEngagement";
		String clearGeoArea = "DELETE FROM GeoArea";
		String clearMuncipality = "DELETE FROM Municipality";
		String clearOutput = "DELETE FROM Outputs";
		String clearProgramElement = "DELETE FROM ProgramElement";
		String clearProgramSubElement = "DELETE FROM ProgramSubElement";
		String clearTargetPop = "DELETE FROM TargetPopulation";

		String clearLocations = "DELETE FROM Location";
		String clearProgram = "DELETE FROM Program";
		String clearAgency = "DELETE FROM Agency";

		Connection conn = null;
		Statement stmt = null;

		try {
			conn = getConnection();

			conn.createStatement().executeUpdate(clearLocations);
			conn.createStatement().executeUpdate(clearProgram);
			conn.createStatement().executeUpdate(clearAgency);
			conn.createStatement().executeUpdate(clearGeoArea);
			conn.createStatement().executeUpdate(clearTargetPop);
			conn.createStatement().executeUpdate(clearProgramSubElement);
			conn.createStatement().executeUpdate(clearProgramElement);
			conn.createStatement().executeUpdate(clearOutput);
			conn.createStatement().executeUpdate(clearMuncipality);
			conn.createStatement().executeUpdate(clearDonorEngagement);
			conn.createStatement().executeUpdate(clearAreaDir);
			conn.createStatement().executeUpdate(clearAndarData);
			
			return RequestHandler.getStatusSuccess();
		} catch (SQLException e) {
			System.out.println(e);
			return RequestHandler.getStatusFailed();
		} catch (ClassNotFoundException e) {
			System.out.println(e);
			return RequestHandler.getStatusFailed();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// Do nothing
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// Do nothing
				}
			}
		}
	}
	
	
	// Get Dashboard Queries
	
	public static JsonArray getProgram() {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "SELECT * FROM Program";
		JsonArray program = new JsonArray();
		try {
			conn = getConnection();

			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				JsonObject row = new JsonObject();
				int id = rs.getInt("id");
				int agency_andar = rs.getInt("agency_andar");
				String name = rs.getString("name");
				String website = rs.getString("website");
				String description = rs.getString("description");
				int num_locations = rs.getInt("num_locations");
				row.addProperty("id", id);
				row.addProperty("agency_andar", agency_andar);
				row.addProperty("name", name);
				row.addProperty("website", website);
				row.addProperty("description", description);
				row.addProperty("num_locations", num_locations);
				
				program.add(row);
			}
		} catch (SQLException e) {
			// Do nothing
		} catch (ClassNotFoundException e) {
			// Do nothing
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// Do nothing
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// Do nothing
				}
			}
		}

		return program;
	}
	
	public static JsonArray getLocation() {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "SELECT * FROM Location";
		JsonArray location = new JsonArray();
		try {
			conn = getConnection();

			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				JsonObject row = new JsonObject();
				int andar_id = rs.getInt("andar_id");
				String name = rs.getString("name");
				String postal = rs.getString("postal");
				double lat = rs.getDouble("lat");
				double lon = rs.getDouble("lon");
				row.addProperty("andar_id", andar_id);
				row.addProperty("name", name);
				row.addProperty("postal", postal);
				row.addProperty("lat", lat);
				row.addProperty("lon", lon);
				
				location.add(row);
			}
		} catch (SQLException e) {
			// Do nothing
		} catch (ClassNotFoundException e) {
			// Do nothing
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// Do nothing
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// Do nothing
				}
			}
		}

		return location;
	}
	
	public static JsonArray getAgency() {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "SELECT * FROM Agency";
		JsonArray agency = new JsonArray();
		try {
			conn = getConnection();

			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				JsonObject row = new JsonObject();
				int id = rs.getInt("id");
				String name = rs.getString("name");
				row.addProperty("id", id);
				row.addProperty("name", name);
				
				agency.add(row);
			}
		} catch (SQLException e) {
			// Do nothing
		} catch (ClassNotFoundException e) {
			// Do nothing
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// Do nothing
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// Do nothing
				}
			}
		}

		return agency;
	}
	
	public static JsonArray getInventoryOutput() {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "SELECT * FROM AndarDataOutput";
		JsonArray inventoryOutput = new JsonArray();
		try {
			conn = getConnection();

			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				JsonObject row = new JsonObject();
				String funds = rs.getString("funds");
				String focus = rs.getString("focus");
				String outcome = rs.getString("outcome");
				String funding = rs.getString("funding");
				int program_andar = rs.getInt("program_andar");
				float yearly_allocation = rs.getFloat("yearly_allocation");
				String grant_date = rs.getDate("grant_start").toString();
				String grant_end = rs.getDate("grant_end").toString();
				String description = rs.getString("description");
				String planner = rs.getString("planner");
				String grant_year = rs.getString("grant_year");
				row.addProperty("funds", funds);
				row.addProperty("focus", focus);
				row.addProperty("outcome", outcome);
				row.addProperty("funding", funding);
				row.addProperty("program_andar", program_andar);
				row.addProperty("yearly_allocation", yearly_allocation);
				row.addProperty("grant_date", grant_date);
				row.addProperty("grant_end", grant_end);
				row.addProperty("description", description);
				row.addProperty("planner", planner);
				row.addProperty("grant_year", grant_year);
				
				inventoryOutput.add(row);
			}
		} catch (SQLException e) {
			// Do nothing
		} catch (ClassNotFoundException e) {
			// Do nothing
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// Do nothing
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// Do nothing
				}
			}
		}

		return inventoryOutput;
	}
	
	public static JsonArray getTargetPopulation() {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "SELECT * FROM TargetPopulation";
		JsonArray targetPopulation = new JsonArray();
		try {
			conn = getConnection();

			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				JsonObject row = new JsonObject();
				int andar_id = rs.getInt("andar_id");
				String population = rs.getString("population");
				row.addProperty("population", population);
				row.addProperty("andar_id", andar_id);
				
				targetPopulation.add(row);
			}
		} catch (SQLException e) {
			// Do nothing
		} catch (ClassNotFoundException e) {
			// Do nothing
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// Do nothing
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// Do nothing
				}
			}
		}

		return targetPopulation;
	}
	
	public static JsonArray getProgramElement() {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "SELECT * FROM ProgramElement";
		JsonArray programElement = new JsonArray();
		try {
			conn = getConnection();

			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				JsonObject row = new JsonObject();
				int andar_id = rs.getInt("andar_id");
				String element = rs.getString("element");
				int level = rs.getInt("level");
				row.addProperty("andar_id", andar_id);
				row.addProperty("element", element);
				row.addProperty("level", level);
				
				programElement.add(row);
			}
		} catch (SQLException e) {
			// Do nothing
		} catch (ClassNotFoundException e) {
			// Do nothing
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// Do nothing
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// Do nothing
				}
			}
		}

		return programElement;
	}
	
	public static JsonArray getProgramSubElement() {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "SELECT * FROM ProgramSubElement";
		JsonArray programSubElement = new JsonArray();
		try {
			conn = getConnection();

			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				JsonObject row = new JsonObject();
				int andar_id = rs.getInt("andar_id");
				String element = rs.getString("element");
				String subElement = rs.getString("subElement");
				row.addProperty("andar_id", andar_id);
				row.addProperty("element", element);
				row.addProperty("subElement", subElement);
				
				programSubElement.add(row);
			}
		} catch (SQLException e) {
			// Do nothing
		} catch (ClassNotFoundException e) {
			// Do nothing
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// Do nothing
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// Do nothing
				}
			}
		}

		return programSubElement;
	}
	
	public static JsonArray getGeoArea() {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "SELECT * FROM GeoArea";
		JsonArray geoArea = new JsonArray();
		try {
			conn = getConnection();

			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				JsonObject row = new JsonObject();
				int andar_id = rs.getInt("andar_id");
				String area = rs.getString("area");
				int level = rs.getInt("level");
				row.addProperty("andar_id", andar_id);
				row.addProperty("area", area);
				row.addProperty("level", level);
				
				geoArea.add(row);
			}
		} catch (SQLException e) {
			// Do nothing
		} catch (ClassNotFoundException e) {
			// Do nothing
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// Do nothing
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// Do nothing
				}
			}
		}

		return geoArea;
	}
	
	public static JsonArray getMunicipality() {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "SELECT * FROM Municipality";
		JsonArray municipalityTable = new JsonArray();
		try {
			conn = getConnection();

			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				JsonObject row = new JsonObject();
				int andar_id = rs.getInt("andar_id");
				String municipality = rs.getString("municipality");
				int focus_percentage = rs.getInt("focus_percentage");
				row.addProperty("andar_id", andar_id);
				row.addProperty("municipality", municipality);
				row.addProperty("focus_percentage", focus_percentage);
				
				municipalityTable.add(row);
			}
		} catch (SQLException e) {
			// Do nothing
		} catch (ClassNotFoundException e) {
			// Do nothing
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// Do nothing
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// Do nothing
				}
			}
		}

		return municipalityTable;
	}
	
	public static JsonArray getAreaDirectory() {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "SELECT * FROM AreaDirectory";
		JsonArray areaDirectory = new JsonArray();
		try {
			conn = getConnection();

			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				JsonObject row = new JsonObject();
				String geoArea = rs.getString("geoArea");
				String municipality = rs.getString("municipality");
				row.addProperty("geoArea", geoArea);
				row.addProperty("municipality", municipality);
				
				areaDirectory.add(row);
			}
		} catch (SQLException e) {
			// Do nothing
		} catch (ClassNotFoundException e) {
			// Do nothing
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// Do nothing
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// Do nothing
				}
			}
		}

		return areaDirectory;
	}
	
	public static JsonArray getDonorEngagement() {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "SELECT * FROM DonorEngagement";
		JsonArray donorEngagement = new JsonArray();
		try {
			conn = getConnection();

			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				JsonObject row = new JsonObject();
				int andar_id = rs.getInt("andar_id");
				String engagement = rs.getString("engagement");
				String description = rs.getString("description");
				row.addProperty("andar_id", andar_id);
				row.addProperty("engagement", engagement);
				row.addProperty("description", description);
				
				donorEngagement.add(row);
			}
		} catch (SQLException e) {
			// Do nothing
		} catch (ClassNotFoundException e) {
			// Do nothing
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// Do nothing
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// Do nothing
				}
			}
		}

		return donorEngagement;
	}
	
	public static JsonArray getOutputs() {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "SELECT * FROM Outputs";
		JsonArray outputs = new JsonArray();
		try {
			conn = getConnection();

			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				JsonObject row = new JsonObject();
				int andar_id = rs.getInt("andar_id");
				String type = rs.getString("type");
				int value = rs.getInt("value");
				String grant_year = rs.getString("grant_year");
				
				row.addProperty("andar_id", andar_id);
				row.addProperty("type", type);
				row.addProperty("value", value);
				row.addProperty("grant_year", grant_year);
				
				outputs.add(row);
			}
		} catch (SQLException e) {
			// Do nothing
		} catch (ClassNotFoundException e) {
			// Do nothing
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// Do nothing
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// Do nothing
				}
			}
		}

		return outputs;
	}


	public static JsonArray getMostRecentImport() {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "SELECT * FROM Log WHERE action = 'Import Success' ORDER BY id DESC LIMIT 1";
		JsonArray recentImport = new JsonArray();

		try {
			conn = getConnection();

			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				JsonObject event = new JsonObject();
				int id = rs.getInt("id");
				String username = rs.getString("username");
				String action = rs.getString("action");
				String date_time = rs.getString("date_time");
				event.addProperty("id", id);
				event.addProperty("username", username);
				event.addProperty("action", action);
				event.addProperty("date_time", date_time);

				recentImport.add(event);
			}
		} catch (SQLException e) {
			JsonObject queryFailed = RequestHandler.getStatusFailed();
			recentImport.add(queryFailed);
		} catch (ClassNotFoundException e) {
			JsonObject queryFailed = RequestHandler.getStatusFailed();
			recentImport.add(queryFailed);
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// Do nothing
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// Do nothing
				}
			}
		}

		return recentImport;
	}
	
	// Monitoring tool log
	public static boolean insertLogEvent(String username, String action, String date_time) {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "INSERT INTO Log (username, action, date_time) VALUES (?, ?, ?)";
		boolean success = true;
		try {
			conn = getConnection();

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);
			stmt.setString(2, action);
			stmt.setString(3, date_time);
			int count = stmt.executeUpdate();
			success = count > 0;
		} catch (SQLException e) {
			success = false;
		} catch (ClassNotFoundException e) {
			success = false;
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// Do nothing
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// Do nothing
				}
			}
		}
		return success;
	}

	public static JsonArray getLogEvents() {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "SELECT * FROM Log ORDER BY id DESC LIMIT 25";
		JsonArray logEvents = new JsonArray();

		try {
			conn = getConnection();

			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				JsonObject event = new JsonObject();
				int id = rs.getInt("id");
				String username = rs.getString("username");
				String action = rs.getString("action");
				String date_time = rs.getString("date_time");
				event.addProperty("id", id);
				event.addProperty("username", username);
				event.addProperty("action", action);
				event.addProperty("date_time", date_time);

				logEvents.add(event);
			}
		} catch (SQLException e) {
			JsonObject queryFailed = RequestHandler.getStatusFailed();
			logEvents.add(queryFailed);
		} catch (ClassNotFoundException e) {
			JsonObject queryFailed = RequestHandler.getStatusFailed();
			logEvents.add(queryFailed);
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// Do nothing
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// Do nothing
				}
			}
		}

		return logEvents;
	}
	
	// User Queries
	public static boolean insertUser(String username, String password, String firstName, String lastName, boolean adminPrivileges) {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "INSERT INTO Users (username, password, first_name, last_name, admin_privileges) VALUES (?, ?, ?, ?, ?)";
		boolean success = true;
		try {
			conn = getConnection();

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);
			stmt.setString(2, password);
			stmt.setString(3, firstName);
			stmt.setString(4, lastName);
			stmt.setBoolean(5, adminPrivileges);
			int count = stmt.executeUpdate();
			success = count > 0;
		} catch (SQLException e) {
			success = false;
		} catch (ClassNotFoundException e) {
			success = false;
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// Do nothing
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// Do nothing
				}
			}
		}
		
		return success;
	}

	public static JsonArray getUsers() {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "SELECT * FROM Users";
		JsonArray userInfo = new JsonArray();

		try {
			conn = getConnection();

			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				JsonObject user = new JsonObject();
				int id = rs.getInt("id");
				String username = rs.getString("username");
				String first = rs.getString("first_name");
				String last = rs.getString("last_name");
				user.addProperty("id", id);
				user.addProperty("user", username);
				user.addProperty("first_name", first);
				user.addProperty("last_name", last);

				userInfo.add(user);
			}
		} catch (SQLException e) {
			JsonObject queryFailed = RequestHandler.getStatusFailed();
			userInfo.add(queryFailed);
		} catch (ClassNotFoundException e) {
			JsonObject queryFailed = RequestHandler.getStatusFailed();
			userInfo.add(queryFailed);
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// Do nothing
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// Do nothing
				}
			}
		}

		return userInfo;
	}

	public static JsonObject verifyUser(String user, String password) {
		JsonObject responseJson = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "SELECT * FROM Users WHERE username=? AND password=?";
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(FIRST, user);
			stmt.setString(SECOND, password);
			ResultSet rs = stmt.executeQuery();

			if (rs.isBeforeFirst()) {
				responseJson = RequestHandler.getStatusSuccess();
				rs.next();
				Boolean isAdmin = rs.getBoolean("admin_privileges");
				responseJson.addProperty("admin", isAdmin);
			} else {
				responseJson = RequestHandler.getStatusFailed();
			}
		} catch (SQLException e) {
			responseJson = RequestHandler.getStatusFailed();
		} catch (ClassNotFoundException e) {
			responseJson = RequestHandler.getStatusFailed();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// Do nothing
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// Do nothing
				}
			}
		}

		return responseJson;
	}

	public static boolean changePassword(String user, String newPassword) {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "UPDATE Users SET password=? WHERE username=?";
		boolean success = true;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, newPassword);
			stmt.setString(2, user);
			int count = stmt.executeUpdate();
			success = count > 0;
		} catch (SQLException e) {
			success = false;
		} catch (ClassNotFoundException e) {
			success = false;
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// Do nothing
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// Do nothing
				}
			}
		}
		return success;
	}

	public static boolean deleteUser(String user) {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "DELETE FROM Users WHERE username=?";
		boolean success = true;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, user);
			int count = stmt.executeUpdate();
			success = count > 0;
		} catch (SQLException e) {
			success = false;
		} catch (ClassNotFoundException e) {
			success = false;
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// Do nothing
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// Do nothing
				}
			}
		}
		return success;
	}

	private static Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName(JDBC_DRIVER);
		Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

		return conn;
	}

}