package com.hss.controller;

import com.alibaba.fastjson.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @ProjectName: ins
 * @Package: com.hss.controller
 * @ClassName: QrCodeControllerTest
 * @Description: java类作用描述
 * @Author: hss
 * @Create_Date: 2019/1/19 16:51
 * @Update_By:
 * @Update_Date: 2019/1/19 16:51
 * U.H. All Rights Reserved.
 */
public class QrCodeControllerTest extends BaseControllerTest {
    @Autowired
    private WebApplicationContext webApp;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.webApp).build();
    }

    /**
     * 测试二维码生成
     *
     * @throws Exception
     */
    @Test
    public void testProductQrCode() throws Exception {
//      Boolean result = QRCodeUtil.zxingCodeCreate("http://www.baidu.com", "E:/files/pic/test", 500, "E:/files/pic/IMG_20180927_203546.jpg");
        RequestBuilder requestBuilder = get("/qr_code")
                .param("content", "http://sh.jumei.com/")
                .param("path", "E:/files/pic/beauty")
                .param("logoPath", "E:/files/pic/微信图片_20190120140054.jpg");
        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
    }

    /**
     * 测试二维码解析
     *
     * @throws Exception
     */
    @Test
    public void testAnalysisQrCode() throws Exception {
        RequestBuilder requestBuilder = get("/qr_code/analysis")
                .param("path", "E:/files/pic/test57.jpg");
        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
    }
}
