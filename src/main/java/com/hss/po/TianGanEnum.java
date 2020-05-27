package com.hss.po;

/**
 * @ProjectName: ins
 * @Package: com.hss.po
 * @ClassName: TianGanEnum
 * @Description: java类作用描述
 * @Author: hss
 * @Create_Date: 2019/1/19 22:34
 * @Update_By:
 * @Update_Date: 2019/1/19 22:34
 * U.H. All Rights Reserved.
 */
public enum TianGanEnum {
    GUI(0, "癸"),
    JIA(1, "甲"),
    YI(2, "乙"),
    BING(3, "丙"),
    DING(4, "丁"),
    WU(5, "戊"),
    JI(6, "己"),
    GENG(7, "庚"),
    XIN(8, "辛"),
    REN(9, "壬");
    private Integer code;
    private String  name;

    private TianGanEnum(Integer code, String name) {
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
