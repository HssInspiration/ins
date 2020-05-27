package com.hss.service;

import com.github.pagehelper.Page;
import com.hss.base.BaseService;
import com.hss.dto.PageQueryParamDTO;
import com.hss.po.MemberPo;
import com.hss.po.UserPo;

import java.util.List;

/**
 * @ProjectName: ins
 * @Package: com.hss.service
 * @ClassName: MemberService
 * @Description: java类作用描述
 * @Author: hss
 * @Create_Date: 2019/5/21 9:28
 * @Update_By:
 * @Update_Date: 2019/5/21 9:28
 * U.H. All Rights Reserved.
 */
public interface MemberService extends BaseService<MemberPo> {
    /**
     * 分页获取用户信息集合
     *
     * @param memberPo
     * @param pageNum
     * @param pageSize
     * @return
     */
    Page<MemberPo> getPage(MemberPo memberPo, int pageNum, int pageSize);

    /**
     * 分页获取用户信息集合
     * @param dto 查询参数集合
     * @return
     */
    Page<MemberPo> getPageData(PageQueryParamDTO dto) throws Exception;

    /**
     * 分页获取用户信息集合
     * @param page
     * @param size
     * @param name
     * @param address
     * @return
     * @throws Exception
     */
    Page<MemberPo> getPagesData(int page, int size, String name, String address) throws Exception;

    void batchDelUser(Long[] ids);

    /**
     *
     * @param memberPo
     * @return
     */
    List<MemberPo> getPageDataList(MemberPo memberPo);
}
