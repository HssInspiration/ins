package com.hss.modules.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hss.commons.base.BaseDao;
import com.hss.commons.base.BaseServiceImpl;
import com.hss.modules.dao.UserDao;
import com.hss.modules.po.UserPo;
import com.hss.modules.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * 代码描述:用户信息service实现类
 * FileName:com.hss.service.impl.UserServiceImpl
 *
 * @author: Dckj_Lxh
 * @date: 2018/11/16
 * @mdate:
 * @Modified By:
 * Copyright (c) 2008-2018 鼎昌科技 All Rights Reserved.
 */
@Service(value = "userService")
public class UserServiceImpl extends BaseServiceImpl<UserPo> implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	protected BaseDao<UserPo> getDao() {
		return userDao;
	}

	/**
	 * 分页获取用户信息列表
	 *
	 * @param userPo
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@Override
	public Page<UserPo> getPage(UserPo userPo, int pageNum, int pageSize) {
		Page<UserPo> p = PageHelper.startPage(pageNum, pageSize);
		String userName = userPo.getUserName();
		Condition condition = new Condition(UserPo.class);
		Example.Criteria criteria = condition.createCriteria();
		if (!StringUtils.isEmpty(userName)) {
			criteria.andLike("userName", "%" + userName + "%");
		}
		condition.setOrderByClause("create_date desc");
		List<UserPo> userPoList = userDao.selectByCondition(condition);
		return p;
	}

	/**
	 * 批量删除用户信息
	 *
	 * @param ids
	 */
	@Override
	public void batchDelUser(Long[] ids) {
		userDao.batchDelUser(ids);
	}

	/**
	 * 增加一条用户信息
	 *
	 * @param userPo
	 * @return
	 */
	@Override
	public Long save(UserPo userPo) {
		return super.save(userPo);
	}


	/**
	 * 修改一条用户信息
	 *
	 * @param userPo
	 * @return
	 */
	public int update(UserPo userPo) {
		UserPo po = this.get(userPo.getId());
		if (po == null) {
			throw new IllegalArgumentException("记录不存在！");
		}
		// 性别
		if (userPo.getSex() != null) {
			po.setSex(userPo.getSex());
		}

		// 用户名称
		if (!StringUtils.isEmpty(userPo.getUserName())) {
			po.setUserName(userPo.getUserName());
		}

		// 用户密码
		if (!StringUtils.isEmpty(userPo.getPassword())) {
			po.setPassword(userPo.getPassword());
		}

		// 用户地址
		if (!StringUtils.isEmpty(userPo.getAddr())) {
			po.setAddr(userPo.getAddr());
		}
		return super.update(po);
	}

}
