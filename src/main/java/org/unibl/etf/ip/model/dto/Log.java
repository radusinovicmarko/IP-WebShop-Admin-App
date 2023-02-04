package org.unibl.etf.ip.model.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class Log implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private Timestamp dated;

	private String logger;

	private String level;

	private String message;

	public Log() {
	}

	public Log(Integer id, Timestamp dated, String logger, String level, String message) {
		super();
		this.id = id;
		this.dated = dated;
		this.logger = logger;
		this.level = level;
		this.message = message;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Timestamp getDated() {
		return dated;
	}

	public void setDated(Timestamp dated) {
		this.dated = dated;
	}

	public String getLogger() {
		return logger;
	}

	public void setLogger(String logger) {
		this.logger = logger;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
