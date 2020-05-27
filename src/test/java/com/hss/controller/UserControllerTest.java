package com.hss.controller;

import com.alibaba.fastjson.JSONObject;
import com.hss.po.UserPo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 代码描述
 * FileName:
 *
 * @author: Dckj_Lxh
 * @date: 2018/11/17 16:30
 * @mdate: 2018/11/17 16:30
 * @Modified By:
 * Copyright (c) 2008-2018 鼎昌科技 All Rights Reserved.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

	@Autowired
	private WebApplicationContext webApp;

	private MockMvc mockMvc;

	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(this.webApp).build();
	}

	@Test
	public void testBatchDel() throws Exception {
		// 创建对象
		Long[] ids = {117875102338813952L,117875203144716288L,117875258404671488L};
		// 转成JSON格式字符串
		String requestJson = JSONObject.toJSONString(ids);

		RequestBuilder requestBuilder = delete("/user/batch/del")
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestJson);
		mockMvc.perform(requestBuilder)
				.andDo(print())
				.andExpect(status().isOk())
				.andReturn().getResponse().getContentAsString();
	}

	@Test
	public void testGetGanZhi() throws Exception {
		RequestBuilder requestBuilder = get("/user/date");
		mockMvc.perform(requestBuilder)
				.andDo(print())
				.andExpect(status().isOk())
				.andReturn().getResponse().getContentAsString();
	}
}
