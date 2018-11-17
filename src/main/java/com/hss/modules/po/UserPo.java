package com.hss.modules.po;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.hss.commons.base.BasePo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 代码描述:用户信息实体类
 * FileName:com.hss.model.UserDomain
 *
 * @author: Dckj_Lxh
 * @date: 2018/11/16
 * @mdate:
 * @Modified By:
 * Copyright (c) 2008-2018 鼎昌科技 All Rights Reserved.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="t_user")
public class UserPo extends BasePo {

    /**用户姓名*/
    @Column(name="user_name")
    @NotNull(message = "用户姓名不能为空")
    private String userName;

    /**用户性别*/
    @Column(name="sex")
    @NotNull(message = "用户性别不能为空")
    private Integer sex;

    /**用户年龄*/
    @Column(name="age")
    @NotNull(message = "用户年龄不能为空")
    private Integer age;

    /**用户密码*/
    @Column(name="password")
    @NotNull(message = "用户姓名不能为空")
    private String password;

    /**手机号码*/
    @Column(name="phone")
    @Pattern(regexp = "^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\\d{8}$", message = "请输入正确手机号码")
    private String phone;

    /**用户地址*/
    @Column(name="addr")
    private String addr;
}