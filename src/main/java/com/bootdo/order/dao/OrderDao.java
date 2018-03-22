package com.bootdo.order.dao;

import com.bootdo.order.domain.OrderDO;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author 阿钟
 * @email 1992lcg@163.com
 * @date 2018-03-19 22:36:31
 */
@Mapper
public interface OrderDao {

	OrderDO get(Integer toId);
	
	List<OrderDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(OrderDO order);
	
	int update(OrderDO order);
	
	int remove(Integer to_id);
	
	int batchRemove(Integer[] toIds);
}
