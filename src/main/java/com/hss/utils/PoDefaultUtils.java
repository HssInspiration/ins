package com.hss.utils;

import com.hss.base.BasePo;
import com.hss.base.IdWorker;

import java.util.Date;

/**
 * 代码描述
 * FileName:
 *
 * @author: Dckj_Lxh
 * @date: 2018/11/16 16:52
 * @mdate: 2018/11/16 16:52
 * @Modified By:
 * Copyright (c) 2008-2018 鼎昌科技 All Rights Reserved.
 */
public class PoDefaultUtils {
	public PoDefaultUtils() {
	}

	public static Long saveDefault(BasePo t) {
//		LoginUserDto loginUserDto = LoginUserUtil.getLoginUser();
//		if (loginUserDto != null) {
//			t.setCreateUsername(loginUserDto.getUsername());
//			t.setCreateUserId(loginUserDto.getId());
//		}

		if (t.getId() == null) {
			Long id = IdWorker.id.nextId();
			t.setId(id);
		}

		if (t.getCreateDate() == null) {
			t.setCreateDate(new Date());
		}

		if (t.getCreateUserId() == null) {
			t.setCreateUserId(0L);
		}

		if (t.getCreateUsername() == null) {
			t.setCreateUsername("admin");
		}

		if (t.getUpdateDate() == null) {
			t.setUpdateDate(new Date());
		}

		if (t.getUpdateUserId() == null) {
			t.setUpdateUserId(0L);
		}

		if (t.getUpdateUsername() == null) {
			t.setUpdateUsername("admin");
		}

		if (t.getRemarks() == null) {
			t.setRemarks("");
		}

		if(t.getDelFlag() == null){
			t.setDelFlag(0);
		}

		return t.getId();
	}

	public static void updateDefault(BasePo t) {
//		LoginUserDto loginUserDto = LoginUserUtil.getLoginUser();
//		if (loginUserDto != null) {
//			t.setUpdateUsername(loginUserDto.getUsername());
//			t.setUpdateUserId(loginUserDto.getId());
//		}

		if (t.getUpdateDate() == null) {
			t.setUpdateDate(new Date());
		}

		if (t.getUpdateUserId() == null) {
			t.setUpdateUserId(0L);
		}

		if (t.getUpdateUsername() == null) {
			t.setUpdateUsername("");
		}

		if (t.getRemarks() == null) {
			t.setRemarks("");
		}

	}
}
