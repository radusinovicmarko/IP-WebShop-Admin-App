package org.unibl.etf.ip.model.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.unibl.etf.ip.model.dao.GenericDAO;
import org.unibl.etf.ip.model.dto.Attribute;

public class MySQLAttributeDAO implements GenericDAO<Attribute> {
	
	private static final String INSERT = "insert into attribute (name, type, category_id) values (?, ?, ?)";

	@Override
	public List<Attribute> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Attribute getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int add(Attribute item) {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet generatedKeys = null;
		int id = -1;
		try {
			connection = ConnectionPool.getConnectionPool().checkOut();
			ps = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, item.getName());
			ps.setString(2, item.getType());
			ps.setInt(3, item.getCategoryId());
			ps.executeUpdate();
			generatedKeys = ps.getGeneratedKeys();
			if (generatedKeys.next())
				id = generatedKeys.getInt(1);
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getConnectionPool().checkIn(connection);
		}
		return id;
	}

	@Override
	public Attribute update(Integer id, Attribute item) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

}
