package com.hss.po;

import com.hss.base.BasePo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.math.BigDecimal;

/**
 * @ProjectName: ins
 * @Package: com.hss.po
 * @ClassName: CareRelationshipPo
 * @Description: 情账关系实体类
 * @Author: hss
 * @Create_Date: 2019/9/22 22:07
 * @Update_By:
 * @Update_Date: 2019/9/22 22:07
 * U.H. All Rights Reserved.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_care_relationship")
public class CareRelationshipPo extends BasePo {
    /**
     * 来访者名称
     */
    @Column(name = "visitor_name")
    private String visitorName;

    /**
     * 贡献金额
     */
    @Column(name = "contribute_amount")
    private BigDecimal contributeAmount;

    /**
     * 来访者性别
     */
    @Column(name = "visitor_gender")
    private Integer visitorGender;

    /**
     * 来访者与家庭关系
     */
    @Column(name = "relationship")
    private String relationship;

    /**
     * 来访者特征描述
     */
    @Column(name = "visitor_features")
    private String visitorFeatures;

    /**
     * 事由（丧葬-0，婚娶-1，生子-2，乔迁-3，探病-4，其他-5）
     */
    @Column(name="visit_reason")
    private Integer visitReason;

    /**
     * 类别（0/1 -- 收/支）
     */
    @Column(name="category")
    private Integer category;

    /**
     * 导出后的性别中文显示
     */
    @Transient
    private String gender;

    /**
     * 开始时间
     */
    @Transient
    private String startDate;
    /**
     * 结束时间
     */
    @Transient
    private String endDate;

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }
}
