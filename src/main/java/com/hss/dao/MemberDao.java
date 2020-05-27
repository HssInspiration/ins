package com.hss.dao;

import com.hss.base.BaseDao;
import com.hss.po.MemberPo;
import com.hss.po.UserPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.web.bind.annotation.DeleteMapping;

/**
 * @ProjectName: ins
 * @Package: com.hss.dao
 * @ClassName: MemberDao
 * @Description: java类作用描述
 * @Author: hss
 * @Create_Date: 2018/11/26 13:27
 * @Update_By:
 * @Update_Date: 2018/11/26 13:27
 * U.H. All Rights Reserved.
 */
//public interface MemberDao extends JpaRepository<MemberPo, String>, JpaSpecificationExecutor<MemberPo> {
@Mapper
public interface MemberDao extends BaseDao<MemberPo> {
    /**
     * 批量删除用户信息
     *
     * @param ids
     */
    @DeleteMapping("<script>" +
            "DELETE FROM t_member " +
            "<where>" +
            "id IN" +
            "<foreach collection=\"ids\" item=\"id\" open=\"(\" separator=\",\" close=\")\">" +
            "#{id}" +
            "</foreach>" +
            "</where>" +
            "</script>")
    void batchDelMember(@Param("ids") Long[] ids);
}