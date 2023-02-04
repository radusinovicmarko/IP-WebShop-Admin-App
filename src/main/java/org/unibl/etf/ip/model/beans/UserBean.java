package org.unibl.etf.ip.model.beans;

import java.io.Serializable;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.unibl.etf.ip.model.dao.mysql.MySQLUserDAO;
import org.unibl.etf.ip.model.dto.User;

public class UserBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private User user;
	
	public List<User> getAll() {
		return new MySQLUserDAO().getAll();
	}
	
	public boolean getById(Integer id) {
		User temp = new MySQLUserDAO().getById(id);
		if (temp == null)
			return false;
		user = temp;
		return true;
	}
	
	public void add(User user) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
		user.setPassword(encoder.encode(user.getPassword()));
		if ("".equals(user.getAvatarUrl()))
			user.setAvatarUrl(null);
		new MySQLUserDAO().add(user);
	}
	
	public void update(Integer id, User user, String newPassword) {
		if (newPassword != null || !"".equals(newPassword)) 
			user.setPassword(new BCryptPasswordEncoder(10).encode(newPassword));
		else
			user.setPassword(this.user.getPassword());
		if ("".equals(user.getAvatarUrl()))
			user.setAvatarUrl(null);
		new MySQLUserDAO().update(id, user);
	}
	
	public boolean delete(Integer id) {
		return new MySQLUserDAO().delete(id);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
