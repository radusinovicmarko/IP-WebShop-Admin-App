package org.unibl.etf.ip.model.dao.mysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.unibl.etf.ip.model.dto.Log;

public class MySQLLogDAO {
	
	private static final String GET_ALL = "select * from logs order by dated desc";

	public List<Log> getAll() {
		Connection connection = null;
		Statement s = null;
		ResultSet rs = null;
		List<Log> list = new ArrayList<>();
		try {
			connection = ConnectionPool.getConnectionPool().checkOut();
			s = connection.createStatement();
			rs = s.executeQuery(GET_ALL);
			while (rs.next()) 
				list.add(new Log(rs.getInt(1), rs.getTimestamp(2), rs.getString(3), rs.getString(4), rs.getString(5)));
			s.close();
		} catch (SQLException exp) {
			return list;
		} finally {
			ConnectionPool.getConnectionPool().checkIn(connection);
		}
		return list;
	}
}
