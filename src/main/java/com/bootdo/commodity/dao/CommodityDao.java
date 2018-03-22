package com.bootdo.commodity.dao;

import com.bootdo.commodity.domain.CommodityDO;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author 阿钟
 * @email 1992lcg@163.com
 * @date 2018-03-12 16:37:04
 */
@Mapper
public interface CommodityDao {

	CommodityDO get(Integer tcId);
	
	List<CommodityDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(CommodityDO commodity);
	
	int update(CommodityDO commodity);
	
	int remove(Integer tc_id);
	
	int batchRemove(Integer[] tcIds);
}
