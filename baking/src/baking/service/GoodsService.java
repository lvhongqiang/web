/**
 * 
 */
package baking.service;

import java.util.List;

import org.springframework.stereotype.Service;

import baking.model.Goods;

/**
 * @author lvhon
 *
 */
@Service
public class GoodsService extends BaseService {

	/**
	 * 列出所有商品
	 * @return
	 */
	public List<Goods>listAll(){
		
		return goodsDAO.findAll();
	}
}
