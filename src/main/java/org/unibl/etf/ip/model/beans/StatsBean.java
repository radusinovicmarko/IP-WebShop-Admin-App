package org.unibl.etf.ip.model.beans;

import java.util.List;

import org.unibl.etf.ip.model.dao.mysql.MySQLLogDAO;
import org.unibl.etf.ip.model.dto.Log;

public class StatsBean {

	public List<Log> getAll() {
		return new MySQLLogDAO().getAll();
	}

}
