package com.hss.base;

import com.github.pagehelper.Page;

import java.io.Serializable;
import java.util.List;

/**
 * 代码描述
 * FileName:
 *
 * @author: Dckj_Lxh
 * @date: 2018/11/16 16:45
 * @mdate: 2018/11/16 16:45
 * @Modified By:
 * Copyright (c) 2008-2018 鼎昌科技 All Rights Reserved.
 */
public interface BaseService<T extends BasePo> {
	Long save(T t);

	int update(T t);

	int deleteById(Long id);

	int deleteByIds(String ids);

	T get(Serializable id);

	List<T> list();

	Page<T> listPage(int page, int limit);
}