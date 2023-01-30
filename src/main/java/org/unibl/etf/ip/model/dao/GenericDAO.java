package org.unibl.etf.ip.model.dao;

import java.util.List;

public interface GenericDAO<T> {
	
	List<T> getAll();
	
	T getById(Integer id);
	
	int add(T item);
	
	boolean update(Integer id, T item);
	
	boolean delete(Integer id);
	
}
