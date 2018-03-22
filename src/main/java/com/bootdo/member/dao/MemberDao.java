package com.bootdo.member.dao;

import com.bootdo.member.domain.MemberDO;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author 阿钟
 * @email 1992lcg@163.com
 * @date 2018-03-18 21:47:36
 */
@Mapper
public interface MemberDao {

	MemberDO get(Integer tmId);
	
	List<MemberDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(MemberDO member);
	
	int update(MemberDO member);
	
	int remove(Integer tm_id);
	
	int batchRemove(Integer[] tmIds);
}
