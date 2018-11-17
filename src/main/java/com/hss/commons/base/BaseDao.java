package com.hss.commons.base;

import tk.mybatis.mapper.common.ConditionMapper;
import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;
import tk.mybatis.mapper.common.ids.SelectByIdsMapper;

/**
 * 代码描述
 * FileName:
 *
 * @author: Dckj_Lxh
 * @date: 2018/11/16 16:39
 * @mdate: 2018/11/16 16:39
 * @Modified By:
 * Copyright (c) 2008-2018 鼎昌科技 All Rights Reserved.
 */
public interface BaseDao<T> extends SelectByIdsMapper<T>, Mapper<T>, MySqlMapper<T>, IdsMapper<T>, ConditionMapper<T> {
}
