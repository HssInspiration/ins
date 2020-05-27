package com.hss.dao;

import com.hss.base.BaseDao;
import com.hss.po.CareRelationshipPo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;

/**
 * @ProjectName: ins
 * @Package: com.hss.dao
 * @ClassName: CareRelationshipDao
 * @Description: java类作用描述
 * @Author: hss
 * @Create_Date: 2019/9/22 22:33
 * @Update_By:
 * @Update_Date: 2019/9/22 22:33
 * U.H. All Rights Reserved.
 */
public interface CareRelationshipDao extends BaseDao<CareRelationshipPo> {

    /**
     * 批量删除用户信息
     *
     * @param ids
     */
    @Delete("<script>" +
            "DELETE FROM t_care_relationship " +
            "<where>" +
            "id IN" +
            "<foreach collection=\"ids\" item=\"id\" open=\"(\" separator=\",\" close=\")\">" +
            "#{id}" +
            "</foreach>" +
            "</where>" +
            "</script>")
    void batchDelCareRelationship(@Param("ids") Long[] ids);
}
