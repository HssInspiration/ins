package com.hss.po;

/**
 * @ProjectName: ins
 * @Package: com.hss.po
 * @ClassName: DiZhiEnum
 * @Description: java类作用描述
 * @Author: hss
 * @Create_Date: 2019/1/19 22:49
 * @Update_By:
 * @Update_Date: 2019/1/19 22:49
 * U.H. All Rights Reserved.
 */
public enum DiZhiEnum {
    HAI(0, "亥"),
    ZI(1, "子"),
    CHOU(2, "丑"),
    YIN(3, "寅"),
    MAO(4, "卯"),
    CHEN(5, "辰"),
    SI(6, "巳"),
    WU(7, "午"),
    WEI(8, "未"),
    SHEN(9, "申"),
    YOU(10, "酉"),
    XU(11, "戌");
    private Integer code;
    private String  name;

    private DiZhiEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
