package com.hss.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hss.base.BaseDao;
import com.hss.base.BaseServiceImpl;
import com.hss.dao.UserDao;
import com.hss.po.DiZhiEnum;
import com.hss.po.TianGanEnum;
import com.hss.po.UserPo;
import com.hss.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 代码描述:用户信息service实现类
 * FileName:com.hss.service.impl.UserServiceImpl
 *
 * @author: Dckj_Lxh
 * @date: 2018/11/16
 * @mdate:
 * @Modified By:
 * Copyright (c) 2008-2018 鼎昌科技 All Rights Reserved.
 */
@Service(value = "userService")
public class UserServiceImpl extends BaseServiceImpl<UserPo> implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    protected BaseDao<UserPo> getDao() {
        return userDao;
    }

    /**
     * 分页获取用户信息列表
     *
     * @param userPo
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public Page<UserPo> getPage(UserPo userPo, int pageNum, int pageSize) {
        Page<UserPo> p = PageHelper.startPage(pageNum, pageSize);
        String userName = userPo.getUserName();
        Condition condition = new Condition(UserPo.class);
        Example.Criteria criteria = condition.createCriteria();
        if (!StringUtils.isEmpty(userName)) {
            criteria.andLike("userName", "%" + userName + "%");
        }
        condition.setOrderByClause("create_date desc");
        List<UserPo> userPoList = userDao.selectByCondition(condition);
        return p;
    }

    /**
     * 批量删除用户信息
     *
     * @param ids
     */
    @Override
    public void batchDelUser(Long[] ids) {
        userDao.batchDelUser(ids);
    }

    /**
     * 增加一条用户信息
     *
     * @param userPo
     * @return
     */
    @Override
    public Long save(UserPo userPo) {
        return super.save(userPo);
    }


    /**
     * 修改一条用户信息
     *
     * @param userPo
     * @return
     */
    public int update(UserPo userPo) {
        UserPo po = this.get(userPo.getId());
        if (po == null) {
            throw new IllegalArgumentException("记录不存在！");
        }
        // 性别
        if (userPo.getSex() != null) {
            po.setSex(userPo.getSex());
        }

        // 用户名称
        if (!StringUtils.isEmpty(userPo.getUserName())) {
            po.setUserName(userPo.getUserName());
        }

        // 用户密码
        if (!StringUtils.isEmpty(userPo.getPassword())) {
            po.setPassword(userPo.getPassword());
        }

        // 用户地址
        if (!StringUtils.isEmpty(userPo.getAddr())) {
            po.setAddr(userPo.getAddr());
        }

        // 用户出生年月日时
        if (userPo.getBirth() != null) {
            po.setBirth(userPo.getBirth());
        }
        return super.update(po);
    }

    /**
     * 根据出生日期时间测算生辰八字
     *
     * @param birthDate
     * @return
     */
    @Override
    public String calculateEightCharacters(LocalDateTime birthDate) {
//        Calendar calendar = Calendar.getInstance();
        // 出生年月日时
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-dd-MM HH");
//        calendar.setTime(birthDate);
        // 获取年月日时
        int year = birthDate.getYear();
        int month = birthDate.getMonthValue();
        int day = birthDate.getDayOfMonth();
        int hour = birthDate.getHour();

        if (year == 0) {
            throw new IllegalArgumentException("出生日期年份填写有误");
        }
        if (month == 0) {
            throw new IllegalArgumentException("出生日期月份填写有误");
        }
        if (day == 0) {
            throw new IllegalArgumentException("出生日期天数填写有误");
        }
        int yearColumnTianGan = (year - 3) % 10;
        // 年份天干
        String yearTianGan = switchTianGan(yearColumnTianGan);
        int yearColumnDiZhi = (year - 3) % 12;
        // 年份地支
        String yearDiZHi = switchDiZhi(yearColumnDiZhi);
        // 月份干支
        String monthGanZhi  = monthTianGanDiZhi(yearColumnTianGan, month);
        return yearTianGan + yearDiZHi+"年"+monthGanZhi+"月";
    }

    /**
     * 返回年份天干的值
     *
     * @param column
     * @return
     */
    private String switchTianGan(int column) {
        switch (column) {
            case 0:
                System.out.println("癸");
                return TianGanEnum.GUI.getName();
            case 1:
                System.out.println("甲");
                return TianGanEnum.JIA.getName();
            case 2:
                System.out.println("乙");
                return TianGanEnum.YI.getName();
            case 3:
                System.out.println("丙");
                return TianGanEnum.BING.getName();
            case 4:
                System.out.println("丁");
                return TianGanEnum.DING.getName();
            case 5:
                System.out.println("戊");
                return TianGanEnum.WU.getName();
            case 6:
                System.out.println("己");
                return TianGanEnum.JI.getName();
            case 7:
                System.out.println("庚");
                return TianGanEnum.GENG.getName();
            case 8:
                System.out.println("辛");
                return TianGanEnum.XIN.getName();
            case 9:
                System.out.println("壬");
                return TianGanEnum.REN.getName();
            default:
                System.out.println("default");
                return "default";
        }
    }

    /**
     * 返回年份地支的值
     *
     * @param column
     * @return
     */
    private String switchDiZhi(int column) {
        switch (column) {
            case 0:
                return DiZhiEnum.HAI.getName();
            case 1:
                return DiZhiEnum.ZI.getName();
            case 2:
                return DiZhiEnum.CHOU.getName();
            case 3:
                return DiZhiEnum.YIN.getName();
            case 4:
                return DiZhiEnum.MAO.getName();
            case 5:
                return DiZhiEnum.CHEN.getName();
            case 6:
                return DiZhiEnum.SI.getName();
            case 7:
                return DiZhiEnum.WU.getName();
            case 8:
                return DiZhiEnum.WEI.getName();
            case 9:
                return DiZhiEnum.SHEN.getName();
            case 10:
                return DiZhiEnum.YOU.getName();
            case 11:
                return DiZhiEnum.XU.getName();
            default:
                return "default";
        }
    }

    /**
     * 获取月份的干支
     *
     * @param year
     * @param month
     * @return
     */
    private String monthTianGanDiZhi(int year, int month) {

        if (year == 1 || year == 6) {
            switch (month) {
                case 1:
                    return TianGanEnum.BING.getName() + DiZhiEnum.YIN.getName();
                case 2:
                    return TianGanEnum.DING.getName() + DiZhiEnum.MAO.getName();
                case 3:
                    return TianGanEnum.WU.getName() + DiZhiEnum.CHEN.getName();
                case 4:
                    return TianGanEnum.JI.getName() + DiZhiEnum.SI.getName();
                case 5:
                    return TianGanEnum.GENG.getName() + DiZhiEnum.WU.getName();
                case 6:
                    return TianGanEnum.XIN.getName() + DiZhiEnum.WEI.getName();
                case 7:
                    return TianGanEnum.REN.getName() + DiZhiEnum.SHEN.getName();
                case 8:
                    return TianGanEnum.GUI.getName() + DiZhiEnum.YOU.getName();
                case 9:
                    return TianGanEnum.JIA.getName() + DiZhiEnum.XU.getName();
                case 10:
                    return TianGanEnum.YI.getName() + DiZhiEnum.HAI.getName();
                case 11:
                    return TianGanEnum.BING.getName() + DiZhiEnum.ZI.getName();
                case 12:
                    return TianGanEnum.DING.getName() + DiZhiEnum.CHOU.getName();
                default:
                    return "default";
            }
        }

        if (year == 2 || year == 7) {
            switch (month) {
                case 1:
                    return TianGanEnum.WU.getName() + DiZhiEnum.YIN.getName();
                case 2:
                    return TianGanEnum.JI.getName() + DiZhiEnum.MAO.getName();
                case 3:
                    return TianGanEnum.GENG.getName() + DiZhiEnum.CHEN.getName();
                case 4:
                    return TianGanEnum.XIN.getName() + DiZhiEnum.SI.getName();
                case 5:
                    return TianGanEnum.REN.getName() + DiZhiEnum.WU.getName();
                case 6:
                    return TianGanEnum.GUI.getName() + DiZhiEnum.WEI.getName();
                case 7:
                    return TianGanEnum.JIA.getName() + DiZhiEnum.SHEN.getName();
                case 8:
                    return TianGanEnum.YI.getName() + DiZhiEnum.YOU.getName();
                case 9:
                    return TianGanEnum.BING.getName() + DiZhiEnum.XU.getName();
                case 10:
                    return TianGanEnum.DING.getName() + DiZhiEnum.HAI.getName();
                case 11:
                    return TianGanEnum.WU.getName() + DiZhiEnum.ZI.getName();
                case 12:
                    return TianGanEnum.JI.getName() + DiZhiEnum.CHOU.getName();
                default:
                    return "default";
            }
        }

        if (year == 3 || year == 8) {
            switch (month) {
                case 1:
                    return TianGanEnum.GENG.getName() + DiZhiEnum.YIN.getName();
                case 2:
                    return TianGanEnum.XIN.getName() + DiZhiEnum.MAO.getName();
                case 3:
                    return TianGanEnum.REN.getName() + DiZhiEnum.CHEN.getName();
                case 4:
                    return TianGanEnum.GUI.getName() + DiZhiEnum.SI.getName();
                case 5:
                    return TianGanEnum.JIA.getName() + DiZhiEnum.WU.getName();
                case 6:
                    return TianGanEnum.YI.getName() + DiZhiEnum.WEI.getName();
                case 7:
                    return TianGanEnum.BING.getName() + DiZhiEnum.SHEN.getName();
                case 8:
                    return TianGanEnum.DING.getName() + DiZhiEnum.YOU.getName();
                case 9:
                    return TianGanEnum.WU.getName() + DiZhiEnum.XU.getName();
                case 10:
                    return TianGanEnum.JI.getName() + DiZhiEnum.HAI.getName();
                case 11:
                    return TianGanEnum.GENG.getName() + DiZhiEnum.ZI.getName();
                case 12:
                    return TianGanEnum.XIN.getName() + DiZhiEnum.CHOU.getName();
                default:
                    return "default";
            }
        }

        if (year == 4|| year == 9) {
            switch (month) {
                case 1:
                    return TianGanEnum.REN.getName() + DiZhiEnum.YIN.getName();
                case 2:
                    return TianGanEnum.GUI.getName() + DiZhiEnum.MAO.getName();
                case 3:
                    return TianGanEnum.JIA.getName() + DiZhiEnum.CHEN.getName();
                case 4:
                    return TianGanEnum.YI.getName() + DiZhiEnum.SI.getName();
                case 5:
                    return TianGanEnum.BING.getName() + DiZhiEnum.WU.getName();
                case 6:
                    return TianGanEnum.DING.getName() + DiZhiEnum.WEI.getName();
                case 7:
                    return TianGanEnum.WU.getName() + DiZhiEnum.SHEN.getName();
                case 8:
                    return TianGanEnum.JI.getName() + DiZhiEnum.YOU.getName();
                case 9:
                    return TianGanEnum.GENG.getName() + DiZhiEnum.XU.getName();
                case 10:
                    return TianGanEnum.XIN.getName() + DiZhiEnum.HAI.getName();
                case 11:
                    return TianGanEnum.REN.getName() + DiZhiEnum.ZI.getName();
                case 12:
                    return TianGanEnum.GUI.getName() + DiZhiEnum.CHOU.getName();
                default:
                    return "default";
            }
        }

        if (year == 0|| year == 5) {
            switch (month) {
                case 1:
                    return TianGanEnum.JIA.getName() + DiZhiEnum.YIN.getName();
                case 2:
                    return TianGanEnum.YI.getName() + DiZhiEnum.MAO.getName();
                case 3:
                    return TianGanEnum.BING.getName() + DiZhiEnum.CHEN.getName();
                case 4:
                    return TianGanEnum.DING.getName() + DiZhiEnum.SI.getName();
                case 5:
                    return TianGanEnum.WU.getName() + DiZhiEnum.WU.getName();
                case 6:
                    return TianGanEnum.JI.getName() + DiZhiEnum.WEI.getName();
                case 7:
                    return TianGanEnum.GENG.getName() + DiZhiEnum.SHEN.getName();
                case 8:
                    return TianGanEnum.XIN.getName() + DiZhiEnum.YOU.getName();
                case 9:
                    return TianGanEnum.REN.getName() + DiZhiEnum.XU.getName();
                case 10:
                    return TianGanEnum.GUI.getName() + DiZhiEnum.HAI.getName();
                case 11:
                    return TianGanEnum.JIA.getName() + DiZhiEnum.ZI.getName();
                case 12:
                    return TianGanEnum.YI.getName() + DiZhiEnum.CHOU.getName();
                default:
                    return "default";
            }
        }
        return "";
    }

}
