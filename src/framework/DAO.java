package framework;

import java.util.List;

public interface DAO <T>{
	
	// CREATE
	public void create(T t);
	
	// RETRIEVE
	public T read(long codigo);
	
	public List<T> readAll();
	
	// UPDATE
	public void update(T t);
	
	// DELETE
	public void delete(long codigo);
}
