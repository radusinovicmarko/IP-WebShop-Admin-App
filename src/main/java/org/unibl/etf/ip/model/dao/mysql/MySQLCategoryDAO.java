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
import org.unibl.etf.ip.model.dto.Category;

public class MySQLCategoryDAO implements GenericDAO<Category> {
	
	private static final String GET_ALL = "select * from category";
	private static final String GET_BY_ID = "select * from category c where c.id=?";
	private static final String INSERT = "insert into category (name, parent_id) values (?, ?)";
	private static final String UPDATE = "update category c set name=? where c.id=?";
	private static final String DELETE = "delete from category where id=?";
	private static final String DELETE_PRODUCTS = "delete p.* from product p inner join product_category pc "
			+ "on p.id=pc.product_id where pc.category_id=?";
	private static final String RECURSIVE = "with recursive cte (id, name, parent_id) as ("
			+ "  select     id,"
			+ "             name,"
			+ "             parent_id"
			+ "  from       category"
			+ "  where      parent_id=?"
			+ "  union all"
			+ "  select     c.id,"
			+ "             c.name,"
			+ "             c.parent_id"
			+ "  from       category c"
			+ "  inner join cte"
			+ "          on c.parent_id = cte.id"
			+ ")"
			+ "select * from cte";

	@Override
	public List<Category> getAll() {
		Connection connection = null;
		Statement s = null;
		ResultSet rs = null;
		List<Category> list = new ArrayList<>();
		try {
			connection = ConnectionPool.getConnectionPool().checkOut();
			s = connection.createStatement();
			rs = s.executeQuery(GET_ALL);
			while (rs.next()) 
				list.add(new Category(rs.getInt(1), rs.getString(2), rs.getInt(3)));
			s.close();
		} catch (SQLException exp) {
			return list;
		} finally {
			ConnectionPool.getConnectionPool().checkIn(connection);
		}
		return list;
	}

	@Override
	public Category getById(Integer id) {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Category category = null;
		try {
			connection = ConnectionPool.getConnectionPool().checkOut();
			ps = connection.prepareStatement(GET_BY_ID);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) 
				category = new Category(rs.getInt(1), rs.getString(2), rs.getInt(3));
			ps.close();
		} catch (SQLException exp) {
		} finally {
			ConnectionPool.getConnectionPool().checkIn(connection);
		}
		return category;
	}

	@Override
	public int add(Category item) {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet generatedKeys = null;
		int id = -1;
		try {
			connection = ConnectionPool.getConnectionPool().checkOut();
			ps = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, item.getName());
			if (item.getParentId() == null)
				ps.setNull(2, Types.NULL);
			else
				ps.setInt(2, item.getParentId());
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
	public boolean update(Integer id, Category item) {
		Connection connection = null;
		PreparedStatement ps = null;
		boolean success = false;
		try {
			connection = ConnectionPool.getConnectionPool().checkOut();
			ps = connection.prepareStatement(UPDATE);
			ps.setString(1, item.getName());
			ps.setInt(2, id);
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
		ResultSet rs = null;
		boolean successCategory = false;
		boolean successProduct = false;
		boolean successSub = true;
		try {
			connection = ConnectionPool.getConnectionPool().checkOut();
			List<Integer> subcategories = new ArrayList<>();
			ps = connection.prepareStatement(RECURSIVE);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) 
				subcategories.add(rs.getInt(1));
			ps.close();
			ps = connection.prepareStatement(DELETE_PRODUCTS);
			ps.setInt(1, id);
			ps.executeUpdate();
			if (ps.getUpdateCount() == 1)
				successProduct = true;
			ps.close();
			for (Integer subId : subcategories) {
				ps = connection.prepareStatement(DELETE_PRODUCTS);
				ps.setInt(1, subId);
				ps.executeUpdate();
				if (ps.getUpdateCount() != 1)
					successSub = false;
				ps.close();
			}
			ps = connection.prepareStatement(DELETE);
			ps.setInt(1, id);
			ps.executeUpdate();
			if (ps.getUpdateCount() == 1)
				successCategory = true;
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getConnectionPool().checkIn(connection);
		}
		return successCategory && successProduct && successSub;
	}

}
