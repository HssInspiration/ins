package com.hss.service.impl;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hss.base.BaseDao;
import com.hss.base.BaseServiceImpl;
import com.hss.dao.MemberDao;
import com.hss.dto.PageQueryParamDTO;
import com.hss.po.MemberPo;
import com.hss.po.UserPo;
import com.hss.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ProjectName: ins
 * @Package: com.hss.service.impl
 * @ClassName: MemberServiceImpl
 * @Description: java类作用描述
 * @Author: hss
 * @Create_Date: 2019/5/21 9:30
 * @Update_By:
 * @Update_Date: 2019/5/21 9:30
 * U.H. All Rights Reserved.
 */

@Service("memberServiceImpl")
public class MemberServiceImpl extends BaseServiceImpl<MemberPo> implements MemberService {

    @Autowired
    private MemberDao memberDao;

    @Override
    protected BaseDao<MemberPo> getDao() {
        return memberDao;
    }


    /**
     * 分页获取用户信息列表
     *
     * @param memberPo
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public Page<MemberPo> getPage(MemberPo memberPo, int pageNum, int pageSize) {
        Page<MemberPo> p = PageHelper.startPage(pageNum, pageSize);
        String name = memberPo.getName();
        Condition condition = new Condition(MemberPo.class);
        Example.Criteria criteria = condition.createCriteria();
        if (!StringUtils.isEmpty(name)) {
            criteria.andLike("name", "%" + name + "%");
        }
        condition.setOrderByClause("create_date desc");
        List<MemberPo> memberPoList = memberDao.selectByCondition(condition);
        return p;
    }

    @Override
    public Page<MemberPo> getPageData(PageQueryParamDTO dto) throws Exception {
        Page<MemberPo> p = PageHelper.startPage(dto.getPage(), dto.getSize());
        String name = null;
        Condition condition = new Condition(MemberPo.class);
        Example.Criteria criteria = condition.createCriteria();
        Object queryData = dto.getQuery();
        // TODO 前端页面传入多个查询参数时无法解析
        if(queryData != null){
            System.out.println(queryData.getClass());
            System.out.println(queryData);
            Map<String, Object> mapTypes = objectToMap(queryData);
            if(!CollectionUtils.isEmpty(mapTypes)){
                System.out.println(mapTypes.get("name"));
                System.out.println(mapTypes.get("address"));
            }
        }
        condition.setOrderByClause("create_date desc");
        List<MemberPo> memberPoList = memberDao.selectByCondition(condition);
        return p;
    }

    @Override
    public Page<MemberPo> getPagesData(int page, int size, String name, String address) throws Exception {
        Page<MemberPo> p = PageHelper.startPage(page, size);
        Condition condition = new Condition(MemberPo.class);
        Example.Criteria criteria = condition.createCriteria();
        if (!StringUtils.isEmpty(name)) {
            criteria.andLike("name", "%" + name + "%");
        }
        if (!StringUtils.isEmpty(address)) {
            criteria.andLike("address", "%" + address + "%");
        }
        condition.setOrderByClause("create_date desc");
        List<MemberPo> memberPoList = memberDao.selectByCondition(condition);
        return p;
    }

    private static Map<String, Object> objectToMap(Object obj) throws Exception {
        if(obj == null)
            return null;

        Map<String, Object> map = new HashMap<String, Object>();

        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor property : propertyDescriptors) {
            String key = property.getName();
            if (key.compareToIgnoreCase("class") == 0) {
                continue;
            }
            Method getter = property.getReadMethod();
            Object value = getter!=null ? getter.invoke(obj) : null;
            map.put(key, value);
        }

        return map;
    }

    /**
     * 批量删除用户信息
     *
     * @param ids
     */
    @Override
    public void batchDelUser(Long[] ids) {
        memberDao.batchDelMember(ids);
    }

    @Override
    public List<MemberPo> getPageDataList(MemberPo memberPo) {
        String name = memberPo.getName();
        Condition condition = new Condition(MemberPo.class);
        Example.Criteria criteria = condition.createCriteria();
        if (!StringUtils.isEmpty(name)) {
            criteria.andLike("name", "%" + name + "%");
        }
        condition.setOrderByClause("create_date desc");
        List<MemberPo> memberPoList = memberDao.selectByCondition(condition);
        return memberPoList;
    }

    /**
     * 增加一条用户信息
     *
     * @param memberPo
     * @return
     */
    @Override
    public Long save(MemberPo memberPo) {
        if(memberPo.getDate() == null){
            memberPo.setDate(new Date());
        }
        return super.save(memberPo);
    }

    /**
     * 修改一条用户信息
     *
     * @param memberPo
     * @return
     */
    public int update(MemberPo memberPo) {
        MemberPo po = this.get(memberPo.getId());
        if (po == null) {
            throw new IllegalArgumentException("记录不存在！");
        }
        // 性别
        if (memberPo.getSex() != null) {
            po.setSex(memberPo.getSex());
        }

        // 用户名称
        if (!StringUtils.isEmpty(memberPo.getName())) {
            po.setName(memberPo.getName());
        }

        // 日期
        if (memberPo.getDate() != null) {
            po.setDate(memberPo.getDate());
        }
        return super.update(po);
    }
}
