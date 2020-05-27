package com.hss.service;

import com.google.zxing.Result;

/**
 * @ProjectName: ins
 * @Package: com.hss.modules.service
 * @ClassName: QrCodeService
 * @Description: java类作用描述
 * @Author: hss
 * @Create_Date: 2019/1/19 17:06
 * @Update_By:
 * @Update_Date: 2019/1/19 17:06
 * U.H. All Rights Reserved.
 */
public interface QrCodeService {
    /**
     * 生成二维码
     *
     * @param content
     * @param path
     * @param size
     * @param logoPath
     * @return
     */
    Boolean productQrCode(String content, String path, Integer size, String logoPath);

    /**
     * 解析二维码
     *
     * @param path
     * @return
     */
    Result analysisQrCode(String path);
}
