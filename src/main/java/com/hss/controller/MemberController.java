package com.hss.controller;

import com.github.pagehelper.Page;
import com.hss.dto.PageQueryParamDTO;
import com.hss.dto.ResponseDto;
import com.hss.po.MemberPo;
import com.hss.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ProjectName: ins
 * @Package: com.hss.controller
 * @ClassName: MemberController
 * @Description: java类作用描述
 * @Author: hss
 * @Create_Date: 2018/11/26 13:29
 * @Update_By:
 * @Update_Date: 2018/11/26 13:29
 * U.H. All Rights Reserved.
 */
@RestController
@RequestMapping("member")
public class MemberController {
    @Autowired
    private MemberService memberService;

    /**
     * 获取一条用户信息
     *
     * @param id
     * @return
     */
    @GetMapping("get/{id}")
    public ResponseDto getOne(@PathVariable Long id) {
        MemberPo memberPo = memberService.get(id);
        return ResponseDto.builder().data(memberPo).build();
    }

    /**
     * 分页获取用户信息集合
     *
     * @param memberPo
     * @param page
     * @param size
     * @return
     */
    @PostMapping("loadPage")
    public ResponseDto getPage(@RequestBody MemberPo memberPo, int page, int size) {
        Page<MemberPo> p = memberService.getPage(memberPo, page, size);
        return ResponseDto.builder().build().pageBuild(p);
    }

    @PostMapping("loadPageData")
    public ResponseDto getPageDataList(@RequestBody MemberPo memberPo) {
        List<MemberPo> p = memberService.getPageDataList(memberPo);
        return ResponseDto.builder().data(p).count(p.size()).build();
    }

    @PostMapping("loadPages")
    public ResponseDto getPageData(@PathVariable int page, @PathVariable int size, @PathVariable String name, @PathVariable String address) throws Exception {
        Page<MemberPo> p = memberService.getPagesData(page, size, name, address);
        return ResponseDto.builder().build().pageBuild(p);
    }

    /**
     * 新增一条用户信息
     *
     * @param memberPo
     * @return
     */
    @PostMapping("save")
    public ResponseDto insertUser(@RequestBody MemberPo memberPo) {
        // 无则新增
        if (memberPo.getId() == null) {
            memberService.save(memberPo);
        }
        // 有则更新
        memberService.update(memberPo);
        return ResponseDto.builder().build();
    }

    /**
     * 删除一条用户信息
     *
     * @param id
     * @return
     */
    @DeleteMapping("remove/{id}")
    public ResponseDto deleteUser(@PathVariable Long id) {
        memberService.deleteById(id);
        return ResponseDto.builder().build();
    }

    /**
     * 批量删除用户信息
     *
     * @param ids
     * @return
     */
    @DeleteMapping("batch/del")
    public ResponseDto batchDeleteUser(@RequestBody Long[] ids) {
        memberService.batchDelUser(ids);
        return ResponseDto.builder().build();
    }

}
