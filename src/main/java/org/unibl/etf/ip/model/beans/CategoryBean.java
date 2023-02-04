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

	private Category updateCategory;

	private List<Attribute> updateCategoryAttributes;

	private List<Attribute> newAttributes;

	public List<Category> getAll() {
		return new MySQLCategoryDAO().getAll();
	}
	
	public boolean getById(Integer id) {
		Category temp = new MySQLCategoryDAO().getById(id);
		if (temp == null)
			return false;
		List<Attribute> attrs = new MySQLAttributeDAO().getAllByCategoryId(id);
		if (attrs.size() == 0)
			return false;
		updateCategory = temp;
		updateCategoryAttributes = attrs;
		return true;
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
	
	public void update(Integer id, Category category) {
		new MySQLCategoryDAO().update(id, category);
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

	public Category getUpdateCategory() {
		return updateCategory;
	}

	public void setUpdateCategory(Category updateCategory) {
		this.updateCategory = updateCategory;
	}

	public List<Attribute> getUpdateCategoryAttributes() {
		return updateCategoryAttributes;
	}

	public void setUpdateCategoryAttributes(List<Attribute> updateCategoryAttributes) {
		this.updateCategoryAttributes = updateCategoryAttributes;
	}

}
