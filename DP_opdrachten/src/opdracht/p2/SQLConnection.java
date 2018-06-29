package opdracht.p2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLConnection {
	private Connection conn = null;
	private String table = "";

	public SQLConnection(String DB_DRIV, String DB_URL, String DB_USER, String DB_PASS) {
		try {
			Class.forName(DB_DRIV).newInstance();
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public boolean Update(String query) throws SQLException {
		Statement stmt = conn.createStatement();
		return stmt.executeUpdate(query) == 1 ? true : false;
	}

	public ResultSet Retrieve(String query) throws SQLException {
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		if (rs != null) {
			return rs;
		} else {
			return null;
		}
	}
}
