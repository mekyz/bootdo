package com.bootdo.member.service;

import com.bootdo.member.domain.MemberDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author 阿钟
 * @email 1992lcg@163.com
 * @date 2018-03-18 21:47:36
 */
public interface MemberService {
	
	MemberDO get(Integer tmId);
	
	List<MemberDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(MemberDO member);
	
	int update(MemberDO member);
	
	int remove(Integer tmId);
	
	int batchRemove(Integer[] tmIds);
}
