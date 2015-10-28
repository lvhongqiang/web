/**
 * 
 */
package xx.dao;

import org.springframework.stereotype.Repository;

import xx.model.Page;

/**
 * @author lvhongqiang
 * @email lvhongqiang09@163.com
 * 2015年5月27日
 */
@Repository
public class BlogDao extends BaseDao {

	public Page list(Integer pageNo,Integer pageSize){
		if(pageNo==null)pageNo=1;
		if(pageSize==null)pageSize=10;
		String hql="from Blog order by createTime desc";
		return findPage(hql, "select count(*) "+hql, pageNo, pageSize);
	}
}
