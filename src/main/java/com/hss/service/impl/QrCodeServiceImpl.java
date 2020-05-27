package com.hss.service.impl;

import com.google.zxing.Result;
import com.hss.service.QrCodeService;
import com.hss.utils.QRCodeUtil;
import org.springframework.stereotype.Service;

/**
 * @ProjectName: ins
 * @Package: com.hss.modules.service.impl
 * @ClassName: QrCodeServiceImpl
 * @Description: java类作用描述
 * @Author: hss
 * @Create_Date: 2019/1/19 17:05
 * @Update_By:
 * @Update_Date: 2019/1/19 17:05
 * U.H. All Rights Reserved.
 */
@Service("qrCodeService")
public class QrCodeServiceImpl implements QrCodeService {

    /**
     * 生成二维码
     *
     * @param content
     * @param path
     * @param size
     * @param logoPath
     * @return
     */
    @Override
    public Boolean productQrCode(String content, String path, Integer size, String logoPath) {
        return QRCodeUtil.zxingCodeCreate(content, path, size, logoPath);
    }

    /**
     * 解析二维码
     *
     * @param path
     * @return
     */
    @Override
    public Result analysisQrCode(String path) {
        return QRCodeUtil.zxingCodeAnalysis(path);
    }
}
