package com.hss.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hss.base.BasePo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @ProjectName: ins
 * @Package: com.hss.po
 * @ClassName: MemberPo
 * @Description: java类作用描述
 * @Author: hss
 * @Create_Date: 2018/11/26 13:15
 * @Update_By:
 * @Update_Date: 2018/11/26 13:15
 * U.H. All Rights Reserved.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_member")
public class MemberPo extends BasePo {

    /**
     * 注册日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    @Temporal(TemporalType.DATE)
    @Column(name = "date")
    private Date date;

    /**
     * 姓名
     */
    @Column(name = "name", length = 50)
    @NotNull(message = "用户姓名不能为空")
    private String name;

    /**
     * 性别
     */
    @Column(name = "sex")
    private Integer sex;

    /**
     * 住址
     */
    @Column(name="address")
    private String address;
}
