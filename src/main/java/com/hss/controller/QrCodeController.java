package com.hss.controller;

import com.google.zxing.Result;
import com.hss.dto.ResponseDto;
import com.hss.service.QrCodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ProjectName: ins
 * @Package: com.hss.controller
 * @ClassName: QrcodeController
 * @Description: java类作用描述
 * @Author: hss
 * @Create_Date: 2019/1/19 16:46
 * @Update_By:
 * @Update_Date: 2019/1/19 16:46
 * U.H. All Rights Reserved.
 */
@Slf4j
@RestController
@RequestMapping("/qr_code")
public class QrCodeController {
    @Autowired
    private QrCodeService qrCodeService;

    /**
     * 生成二维码
     *
     * @param content
     * @param path
     * @param size
     * @param logoPath
     * @return
     */
    @GetMapping
    public ResponseDto productCode(String content, String path, Integer size, String logoPath) {
//      Boolean result = QRCodeUtil.zxingCodeCreate("http://www.baidu.com", "E:/files/pic/test", 500, "E:/files/pic/IMG_20180927_203546.jpg");
        if (size == null) {
            size = 500;
        }
        Boolean result = qrCodeService.productQrCode(content, path, size, logoPath);
        return ResponseDto.builder().data(result).build();
    }

    /**
     * 解析二维码
     *
     * @param path
     * @return
     */
    @GetMapping("/analysis")
    public ResponseDto analysisCode(String path) {
        Result result = qrCodeService.analysisQrCode(path);
        System.err.println("二维码解析内容：" + result.toString());
        log.info("二维码解析内容：" + result.toString());
        return ResponseDto.builder().data(result).build();
    }

}
