package tw.com.eshop.dao;

import java.util.ArrayList;

public abstract class DAO<T> {
	
	public void add(T t) {
	}
	
	public Integer addreturn(T t) {
		return null;
	};
	
	public  ArrayList<T> query(String name){
		return null;
	};
	
	public abstract ArrayList<T> queryAll();
	
	public abstract void delete(Integer id);
	
	public abstract void update(T t);

}
