package com.hss.service;

import com.github.pagehelper.Page;
import com.hss.base.BaseService;
import com.hss.po.CareRelationshipPo;
import com.hss.po.MemberPo;

import java.util.List;

/**
 * @ProjectName: ins
 * @Package: com.hss.service
 * @ClassName: CareRelationshipService
 * @Description: java类作用描述
 * @Author: hss
 * @Create_Date: 2019/9/22 22:28
 * @Update_By:
 * @Update_Date: 2019/9/22 22:28
 * U.H. All Rights Reserved.
 */
public interface CareRelationshipService  extends BaseService<CareRelationshipPo> {
    /**
     * 分页获取careRelationshipPo集合
     *
     * @param careRelationshipPo
     * @param pageNum
     * @param pageSize
     * @return
     */
    Page<CareRelationshipPo> getPage(CareRelationshipPo careRelationshipPo, int pageNum, int pageSize);

    void delete(Long id);

    /**
     * 批量删除
     * @param ids
     */
    void batchDelCareRelationship(Long[] ids);

    /**
     * 按条件查询情账集合
     * @param careRelationshipPo
     * @return List<CareRelationshipPo>
     */
    List<CareRelationshipPo> exportList(CareRelationshipPo careRelationshipPo);
}
