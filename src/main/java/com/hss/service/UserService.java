package com.hss.service;

import com.github.pagehelper.Page;
import com.hss.base.BaseService;
import com.hss.po.UserPo;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;

/**
 * 代码描述:用户信息service接口
 * FileName:com.hss.service.UserService
 *
 * @author: Dckj_Lxh
 * @date: 2018/11/16
 * @mdate:
 * @Modified By:
 * Copyright (c) 2008-2018 鼎昌科技 All Rights Reserved.
 */
public interface UserService extends BaseService<UserPo> {

    /**
     * 分页获取用户信息集合
     *
     * @param userPo
     * @param pageNum
     * @param pageSize
     * @return
     */
    Page<UserPo> getPage(UserPo userPo, int pageNum, int pageSize);

    /**
     * 批量删除用户信息
     *
     * @param ids
     */
    void batchDelUser(@Param("ids") Long[] ids);

    /**
     * 根据出生日期时间测算生辰八字
     *
     * @param birthDate
     * @return
     */
    String calculateEightCharacters(LocalDateTime birthDate);
}
