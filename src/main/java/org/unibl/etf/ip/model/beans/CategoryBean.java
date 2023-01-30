package org.unibl.etf.ip.model.beans;

import java.io.Serializable;
import java.util.List;

import org.unibl.etf.ip.model.dao.mysql.MySQLAttributeDAO;
import org.unibl.etf.ip.model.dao.mysql.MySQLCategoryDAO;
import org.unibl.etf.ip.model.dto.Attribute;
import org.unibl.etf.ip.model.dto.Category;

public class CategoryBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Category newCategory;
	
	private List<Attribute> newAttributes;

	public List<Category> getAll() {
		return new MySQLCategoryDAO().getAll();
	}
	
	public void add() {
		if (newCategory.getParentId() == 0)
			newCategory.setParentId(null);
		int categoryId = new MySQLCategoryDAO().add(newCategory);
		for (Attribute attribute : newAttributes) {
			attribute.setCategoryId(categoryId);
			new MySQLAttributeDAO().add(attribute);
		}
	}
	
	public boolean delete(Integer id) {
		return new MySQLCategoryDAO().delete(id);
	}

	public Category getNewCategory() {
		return newCategory;
	}

	public void setNewCategory(Category newCategory) {
		this.newCategory = newCategory;
	}

	public List<Attribute> getNewAttributes() {
		return newAttributes;
	}

	public void setNewAttributes(List<Attribute> newAttributes) {
		this.newAttributes = newAttributes;
	}
	
}
