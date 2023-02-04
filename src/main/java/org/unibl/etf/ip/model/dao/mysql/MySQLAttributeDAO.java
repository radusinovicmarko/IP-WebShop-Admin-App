package org.unibl.etf.ip.model.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.unibl.etf.ip.model.dao.GenericDAO;
import org.unibl.etf.ip.model.dto.Attribute;

public class MySQLAttributeDAO implements GenericDAO<Attribute> {
	
	private static final String GET_BY_CATEGORY = "select * from attribute a where a.category_id=?";
	private static final String INSERT = "insert into attribute (name, type, category_id) values (?, ?, ?)";

	@Override
	public List<Attribute> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<Attribute> getAllByCategoryId(Integer categoryId) {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Attribute> attributes = new ArrayList<>();
		try {
			connection = ConnectionPool.getConnectionPool().checkOut();
			ps = connection.prepareStatement(GET_BY_CATEGORY);
			ps.setInt(1, categoryId);
			rs = ps.executeQuery();
			while (rs.next()) 
				attributes.add(new Attribute(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
			ps.close();
		} catch (SQLException exp) {
		} finally {
			ConnectionPool.getConnectionPool().checkIn(connection);
		}
		return attributes;
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
	public boolean update(Integer id, Attribute item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

}
