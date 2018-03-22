package com.bootdo.commodity.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.commodity.dao.CommodityDao;
import com.bootdo.commodity.domain.CommodityDO;
import com.bootdo.commodity.service.CommodityService;



@Service
public class CommodityServiceImpl implements CommodityService {
	@Autowired
	private CommodityDao commodityDao;
	
	@Override
	public CommodityDO get(Integer tcId){
		return commodityDao.get(tcId);
	}
	
	@Override
	public List<CommodityDO> list(Map<String, Object> map){
		return commodityDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return commodityDao.count(map);
	}
	
	@Override
	public int save(CommodityDO commodity){
		return commodityDao.save(commodity);
	}
	
	@Override
	public int update(CommodityDO commodity){
		return commodityDao.update(commodity);
	}
	
	@Override
	public int remove(Integer tcId){
		return commodityDao.remove(tcId);
	}
	
	@Override
	public int batchRemove(Integer[] tcIds){
		return commodityDao.batchRemove(tcIds);
	}
	
}
