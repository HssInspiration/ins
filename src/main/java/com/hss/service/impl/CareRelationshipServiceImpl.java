package com.hss.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hss.base.BaseDao;
import com.hss.base.BaseServiceImpl;
import com.hss.dao.CareRelationshipDao;
import com.hss.po.CareRelationshipPo;
import com.hss.po.MemberPo;
import com.hss.service.CareRelationshipService;
import com.hss.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ProjectName: ins
 * @Package: com.hss.service.impl
 * @ClassName: CareRelationshipServiceImpl
 * @Description: java类作用描述
 * @Author: hss
 * @Create_Date: 2019/9/22 22:30
 * @Update_By:
 * @Update_Date: 2019/9/22 22:30
 * U.H. All Rights Reserved.
 */
@Service("relationshipService")
public class CareRelationshipServiceImpl extends BaseServiceImpl<CareRelationshipPo> implements CareRelationshipService {

    @Autowired
    private CareRelationshipDao relationshipDao;

    @Override
    protected BaseDao<CareRelationshipPo> getDao() {
        return relationshipDao;
    }

    @Override
    public Long save(CareRelationshipPo relationshipPo){
        return super.save(relationshipPo);
    }

    @Override
    public int update(CareRelationshipPo relationshipPo){
        CareRelationshipPo po = super.get(relationshipPo.getId());
        if(null == po){
            throw new IllegalArgumentException("记录不存在！");
        }
        po.setCategory(relationshipPo.getCategory());
        po.setContributeAmount(relationshipPo.getContributeAmount());
        po.setRelationship(relationshipPo.getRelationship());
        po.setVisitorFeatures(relationshipPo.getVisitorFeatures());
        po.setVisitorGender(relationshipPo.getVisitorGender());
        po.setVisitorName(relationshipPo.getVisitorName());
        po.setVisitReason(relationshipPo.getVisitReason());
        po.setRemarks(relationshipPo.getRemarks());
        return super.update(po);
    }

    @Override
    public Page<CareRelationshipPo> getPage(CareRelationshipPo careRelationshipPo, int pageNum, int pageSize) {
        Page<CareRelationshipPo> page = PageHelper.startPage(pageNum, pageSize);
        Condition condition = new Condition(CareRelationshipPo.class);
        Example.Criteria criteria = condition.createCriteria();
        // 根据人名模糊查询
        String visitorName = careRelationshipPo.getVisitorName();
        if (!StringUtils.isEmpty(visitorName)) {
            criteria.andLike("visitorName", "%" + visitorName + "%");
        }
        // 根据金额筛选人员
        BigDecimal amount = careRelationshipPo.getContributeAmount();
        if(null != amount){
            criteria.andEqualTo("contributeAmount", amount);
        }
        // 根据类别筛选
        Integer category = careRelationshipPo.getCategory();
        if(null != category){
            criteria.andEqualTo("category", category);
        }
        // 根据事由筛选
        Integer visitReason = careRelationshipPo.getVisitReason();
        if(null != visitReason){
            criteria.andEqualTo("visitReason", visitReason);
        }

        // 时间查询条件
        String startDate = careRelationshipPo.getStartDate();
        String endDate = careRelationshipPo.getEndDate();
        if (!StringUtils.isEmpty(startDate)) {
            criteria.andGreaterThanOrEqualTo("createDate", startDate + " 00:00:00");
        }
        if (!StringUtils.isEmpty(endDate)) {
            criteria.andLessThanOrEqualTo("createDate", endDate + " 23:59:59");
        }
        // 降序排列
        condition.setOrderByClause("create_date desc");
        // 查询集合
        List<CareRelationshipPo> relationshipPoList = relationshipDao.selectByCondition(condition);
        return page;
    }

    @Override
    public void delete(Long id){
        super.deleteById(id);
    }

    @Override
    public void batchDelCareRelationship(Long[] ids) {
        relationshipDao.batchDelCareRelationship(ids);
    }

    @Override
    public List<CareRelationshipPo> exportList(CareRelationshipPo careRelationshipPo) {
        Condition condition = new Condition(CareRelationshipPo.class);
        Example.Criteria criteria = condition.createCriteria();
        // 根据人名模糊查询
        String visitorName = careRelationshipPo.getVisitorName();
        if (!StringUtils.isEmpty(visitorName)) {
            criteria.andLike("visitorName", "%" + visitorName + "%");
        }
        // 根据金额筛选人员
        BigDecimal amount = careRelationshipPo.getContributeAmount();
        if(null != amount){
            criteria.andEqualTo("contributeAmount", amount);
        }
        // 根据类别筛选
        Integer category = careRelationshipPo.getCategory();
        if(null != category){
            criteria.andEqualTo("category", category);
        }
        // 根据事由筛选
        Integer visitReason = careRelationshipPo.getVisitReason();
        if(null != visitReason){
            criteria.andEqualTo("visitReason", visitReason);
        }

        // 时间查询条件
        String startDate = careRelationshipPo.getStartDate();
        String endDate = careRelationshipPo.getEndDate();
        if (!StringUtils.isEmpty(startDate)) {
            criteria.andGreaterThanOrEqualTo("createDate", startDate + " 00:00:00");
        }
        if (!StringUtils.isEmpty(endDate)) {
            criteria.andLessThanOrEqualTo("createDate", endDate + " 23:59:59");
        }
        // 降序排列
        condition.setOrderByClause("create_date desc");
        // 查询集合
        List<CareRelationshipPo> relationshipPoList = relationshipDao.selectByCondition(condition);

        if(!CollectionUtils.isEmpty(relationshipPoList)){
            relationshipPoList.stream().filter(bean -> {
                if (1 == bean.getVisitorGender()){
                    bean.setGender("男");
                }else{
                    bean.setGender("女");
                }
                return true;
            }).collect(Collectors.toList());
        }
        return relationshipPoList;
    }
}
