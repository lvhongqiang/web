package xx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xx.dao.BlogDao;
import xx.model.Page;

/**
 * @author lvhongqiang
 * @email lvhongqiang09@163.com
 * 2015年5月27日
 */

@Service

public class BlogService extends BaseService {
	@Autowired BlogDao blogDao;
	
	public Page list(Integer pageNo,Integer pageSize){
		return blogDao.list(pageNo, pageSize);
	}

}
