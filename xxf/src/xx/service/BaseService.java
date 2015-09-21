package xx.service;

import org.springframework.beans.factory.annotation.Autowired;

import xx.dao.BaseDao;

public class BaseService {
	@Autowired protected BaseDao baseDao;
	
	public Object get(Class clazz, Integer id){
		return baseDao.get(clazz, id);
	}
	public void saveOrUpdate(Object obj){
		baseDao.saveOrUpdate(obj);
	}
	public void delete(Object obj){
		baseDao.delete(obj);
	}
}
