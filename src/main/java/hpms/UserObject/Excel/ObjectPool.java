package hpms.UserObject.Excel;

import java.util.ArrayList;
import java.util.List;

public class ObjectPool<T>  {	
	
	private List<T> poolList = new ArrayList<T>();
	private Class<T> classObj;
	private int maxPoolSize = 10;	

	
	public ObjectPool(Class<T> classObj) {
		this.classObj = classObj;
	}
	
	public ObjectPool(Class<T> classObj, int maxPoolSize) {
		this.classObj = classObj;
		this.maxPoolSize = maxPoolSize;
	}
		
	
	//객체 생성
	public T borrowObject() throws InstantiationException, IllegalAccessException {
		
		//풀사이즈 초과
		if (poolList.size() > maxPoolSize) {
			throw new PoolSizeOutException("PoolSizeOutException");
		}
		
		T newInstance = classObj.newInstance();
		poolList.add(newInstance);
		return newInstance;		
	}
		
	
	//객체 반환
	public boolean returnObject(T obj) {
		return poolList.remove(obj);
	}
	
	
	//객체 유효성 검사 (풀에 있는 객체인지)
	public boolean invalidateObject(T obj) {
		for(T data : poolList) {
			if (data != null && data.equals(obj)) {
				return true;
			}	
		}
		return false;		
	}
	
	
	public T getObject() {
		
		if (poolList.size() < 1) {
			return null;
		}

		//Top Pop
		T instance = poolList.get(poolList.size() - 1);		
		returnObject(instance);
		
		return instance;
	}	
	public int CurrentPoolSize(){
		return poolList.size();
		
	}
		
	
	static class PoolSizeOutException extends RuntimeException {

		public PoolSizeOutException(String message) {
			super(message);
		}
		
	}
	
}