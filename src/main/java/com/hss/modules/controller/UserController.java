package com.hss.modules.controller;

import com.github.pagehelper.Page;
import com.hss.commons.dto.ResponseDto;
import com.hss.modules.po.UserPo;
import com.hss.modules.service.UserService;
import org.apache.tomcat.jni.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;

/**
 * 代码描述:用户信息Controller
 * FileName:com.hss.controller.UserController
 *
 * @author: Dckj_Lxh
 * @date: 2018/11/16
 * @mdate:
 * @Modified By:
 * Copyright (c) 2008-2018 鼎昌科技 All Rights Reserved.
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private UserService userService;

	/**
	 * 获取一条用户信息
	 *
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	public ResponseDto getOne(@PathVariable Long id) {
		UserPo userPo = userService.get(id);
		return ResponseDto.builder().data(userPo).build();
	}

	/**
	 * 分页获取用户信息集合
	 *
	 * @param userPo
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@GetMapping("/page")
	public ResponseDto getPage(UserPo userPo, int pageNum, int pageSize) {
		Page<UserPo> p = userService.getPage(userPo, pageNum, pageSize);
		return ResponseDto.builder().build().pageBuild(p);
	}

	/**
	 * 新增一条用户信息
	 *
	 * @param userPo
	 * @return
	 */
	@PostMapping("/insert")
	public ResponseDto insertUser(UserPo userPo) {
		userService.save(userPo);
		return ResponseDto.builder().build();
	}

	/**
	 * 更新一条用户信息
	 *
	 * @param userPo
	 * @param id
	 * @return
	 */
	@PutMapping("/{id}")
	public ResponseDto updateUser(UserPo userPo, @PathVariable Long id) {
		userPo.setId(id);
		userService.update(userPo);
		return ResponseDto.builder().build();
	}

	/**
	 * 删除一条用户信息
	 *
	 * @param id
	 * @return
	 */
	@DeleteMapping("/{id}")
	public ResponseDto deleteUser(@PathVariable Long id) {
		userService.deleteById(id);
		return ResponseDto.builder().build();
	}

	/**
	 * 批量删除用户信息
	 * @param ids
	 * @return
	 */
	@DeleteMapping("/batch/del")
	public ResponseDto batchDeleteUser(@RequestBody Long[] ids){
		userService.batchDelUser(ids);
		return ResponseDto.builder().build();
	}

}
