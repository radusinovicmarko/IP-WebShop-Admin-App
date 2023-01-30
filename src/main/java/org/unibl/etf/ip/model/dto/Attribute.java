package org.unibl.etf.ip.model.dto;

public class Attribute {
	
	private Integer id;
	
	private String name;
	
	private String type;
	
	private Integer categoryId;

	public Attribute() {
	}

	public Attribute(Integer id, String name, String type, Integer categoryId) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.categoryId = categoryId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

}
