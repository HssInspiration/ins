package com.hss.base;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hss.utils.PoDefaultUtils;

import java.io.Serializable;
import java.util.List;

/**
 * 代码描述
 * FileName:
 *
 * @author: Dckj_Lxh
 * @date: 2018/11/16 16:46
 * @mdate: 2018/11/16 16:46
 * @Modified By:
 * Copyright (c) 2008-2018 鼎昌科技 All Rights Reserved.
 */
public abstract class BaseServiceImpl<T extends BasePo> implements BaseService<T> {
	public BaseServiceImpl() {
	}

	protected abstract BaseDao<T> getDao();

	public Long save(T t) {
		Long id = PoDefaultUtils.saveDefault(t);
		this.getDao().insert(t);
		return id;
	}

	public int update(T t) {
		PoDefaultUtils.updateDefault(t);
		return this.getDao().updateByPrimaryKey(t);
	}

	public int deleteById(Long id) {
		return this.getDao().deleteByPrimaryKey(id);
	}

	public T get(Serializable id) {
		return (T)this.getDao().selectByPrimaryKey(id);
	}

	public List<T> list() {
		return this.getDao().selectAll();
	}

	public int deleteByIds(String ids) {
		return this.getDao().deleteByIds(ids);
	}

	public Page<T> listPage(int page, int limit) {
		Page<T> p = PageHelper.startPage(page, limit);
		this.getDao().selectAll();
		return p;
	}
}
