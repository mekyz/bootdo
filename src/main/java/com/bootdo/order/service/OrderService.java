package com.bootdo.order.service;

import com.bootdo.order.domain.OrderDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author 阿钟
 * @email 1992lcg@163.com
 * @date 2018-03-19 22:36:31
 */
public interface OrderService {
	
	OrderDO get(Integer toId);
	
	List<OrderDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(OrderDO order);
	
	int update(OrderDO order);
	
	int remove(Integer toId);
	
	int batchRemove(Integer[] toIds);
}
