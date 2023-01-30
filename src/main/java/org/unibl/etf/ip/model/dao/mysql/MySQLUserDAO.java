package org.unibl.etf.ip.model.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.unibl.etf.ip.model.dao.GenericDAO;
import org.unibl.etf.ip.model.dto.User;

public class MySQLUserDAO implements GenericDAO<User> {
	
	private static final String GET_ALL = "select * from user";
	private static final String GET_BY_ID = "select * from user u where u.id=?";
	private static final String INSERT = "insert into user (firstName, lastName, username, password, avatarURL, email, activated, contactPhone, location, deleted)"
			+ "values (?, ?, ?, ?, ?, ?, 1, ?, ?, 0)";
	private static final String UPDATE = "update user u set firstName=?, lastName=?, username=?, password=?, avatarURL=?, email=?, "
			+ "activated=?, contactPhone=?, location=?, deleted=? where u.id=?";
	private static final String DELETE = "update user u set deleted=1 where u.id=?";
	
	@Override
	public List<User> getAll() {
		Connection connection = null;
		Statement s = null;
		ResultSet rs = null;
		List<User> list = new ArrayList<>();
		try {
			connection = ConnectionPool.getConnectionPool().checkOut();
			s = connection.createStatement();
			rs = s.executeQuery(GET_ALL);
			while (rs.next()) 
				list.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getBoolean(8), rs.getString(9), rs.getString(10), rs.getBoolean(11)));
			s.close();
		} catch (SQLException exp) {
			return list;
		} finally {
			ConnectionPool.getConnectionPool().checkIn(connection);
		}
		return list;
	}

	@Override
	public User getById(Integer id) {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = null;
		try {
			connection = ConnectionPool.getConnectionPool().checkOut();
			ps = connection.prepareStatement(GET_BY_ID);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) 
				user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getBoolean(8), rs.getString(9), rs.getString(10), rs.getBoolean(11));
			ps.close();
		} catch (SQLException exp) {
		} finally {
			ConnectionPool.getConnectionPool().checkIn(connection);
		}
		return user;
	}

	@Override
	public int add(User item) {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet generatedKeys = null;
		int id = -1;
		try {
			connection = ConnectionPool.getConnectionPool().checkOut();
			ps = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, item.getFirstName());
			ps.setString(2, item.getLastName());
			ps.setString(3, item.getUsername());
			ps.setString(4, item.getPassword());
			if (item.getAvatarUrl() != null)
				ps.setString(5, item.getAvatarUrl());
			else
				ps.setNull(5, Types.NULL);
			ps.setString(6, item.getEmail());
			ps.setString(7, item.getContactPhone());
			ps.setString(8, item.getLocation());
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
	public boolean update(Integer id, User item) {
		Connection connection = null;
		PreparedStatement ps = null;
		boolean success = false;
		try {
			connection = ConnectionPool.getConnectionPool().checkOut();
			ps = connection.prepareStatement(UPDATE);
			ps.setString(1, item.getFirstName());
			ps.setString(2, item.getLastName());
			ps.setString(3, item.getUsername());
			ps.setString(4, item.getPassword());
			if (item.getAvatarUrl() != null)
				ps.setString(5, item.getAvatarUrl());
			else
				ps.setNull(5, Types.NULL);
			ps.setString(6, item.getEmail());
			ps.setBoolean(7, item.isActivated());
			ps.setString(8, item.getContactPhone());
			ps.setString(9, item.getLocation());
			ps.setBoolean(10, item.isDeleted());
			ps.setInt(11, id);
			ps.executeUpdate();
			if (ps.getUpdateCount() == 1)
				success = true;
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getConnectionPool().checkIn(connection);
		}
		return success;
	}

	@Override
	public boolean delete(Integer id) {
		Connection connection = null;
		PreparedStatement ps = null;
		boolean success = false;
		try {
			connection = ConnectionPool.getConnectionPool().checkOut();
			ps = connection.prepareStatement(DELETE);
			ps.setInt(1, id);
			ps.executeUpdate();
			if (ps.getUpdateCount() == 1)
				success = true;
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getConnectionPool().checkIn(connection);
		}
		return success;
	}

}
