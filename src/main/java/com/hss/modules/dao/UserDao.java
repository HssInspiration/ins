package com.hss.modules.dao;


import com.hss.commons.base.BaseDao;
import com.hss.modules.po.UserPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

/**
 * 代码描述:用户信息dao
 * FileName:com.hss.dao.UserDao
 *
 * @author: Dckj_Lxh
 * @date: 2018/11/16
 * @mdate:
 * @Modified By:
 * Copyright (c) 2008-2018 鼎昌科技 All Rights Reserved.
 */
@Mapper
public interface UserDao extends BaseDao<UserPo> {

	/**
	 * 批量删除用户信息
	 *
	 * @param ids
	 */
	@DeleteMapping("<script>" +
					"DELETE FROM t_user " +
						"<where>" +
							"id IN" +
						"<foreach collection=\"ids\" item=\"id\" open=\"(\" separator=\",\" close=\")\">" +
							"#{id}" +
						"</foreach>" +
						"</where>" +
					"</script>")
	void batchDelUser(@Param("ids") Long[] ids);

//	/**
//	 * 增加一条新的用户信息
//	 *
//	 * @param record
//	 * @return
//	 */
//	int insert(UserPo record);
//
//
//	/**
//	 * 查询所有用户信息
//	 *
//	 * @return
//	 */
//	List<UserPo> selectUsers();
}