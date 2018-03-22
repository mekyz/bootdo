package com.bootdo.business.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bootdo.business.dao.BusinessDao;
import com.bootdo.business.domain.BusinessDO;
import com.bootdo.business.service.BusinessService;


@Service
public class BusinessServiceImpl implements BusinessService {
    @Autowired
    private BusinessDao businessDao;

    @Override
    public BusinessDO get(Integer tbId) {
        return businessDao.get(tbId);
    }

    @Override
    public BusinessDO getByUserId(Integer tbUserId) {
        return businessDao.getByUserId(tbUserId);
    }

    @Override
    public List<BusinessDO> getBusinessByLocation(Map<String, Object> map) {
        return businessDao.getBusinessByLocation(map);
    }

    @Override
    public List<BusinessDO> list(Map<String, Object> map) {
        return businessDao.list(map);
    }

    @Override
    public int count(Map<String, Object> map) {
        return businessDao.count(map);
    }

    @Override
    public int save(BusinessDO business) {
        return businessDao.save(business);
    }

    @Override
    public int update(BusinessDO business) {
        return businessDao.update(business);
    }

    @Override
    public int remove(Integer tbId) {
        return businessDao.remove(tbId);
    }

    @Override
    public int batchRemove(Integer[] tbIds) {
        return businessDao.batchRemove(tbIds);
    }

}
