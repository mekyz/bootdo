package com.bootdo.business.dao;

import com.bootdo.business.domain.BusinessDO;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author 阿钟
 * @email 1992lcg@163.com
 * @date 2018-03-11 12:15:28
 */
@Mapper
public interface BusinessDao {

    BusinessDO get(Integer tbId);

    BusinessDO getByUserId(Integer tbUserId);

    List<BusinessDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(BusinessDO business);

    int update(BusinessDO business);

    int remove(Integer tb_id);

    int batchRemove(Integer[] tbIds);

    List<BusinessDO> getBusinessByLocation(Map<String, Object> map);
}
