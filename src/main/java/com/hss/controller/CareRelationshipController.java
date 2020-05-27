package com.hss.controller;

import com.github.pagehelper.Page;
import com.hss.dto.ResponseDto;
import com.hss.po.CareRelationshipPo;
import com.hss.service.CareRelationshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ProjectName: ins
 * @Package: com.hss.controller
 * @ClassName: CareRelationshipController
 * @Description: java类作用描述
 * @Author: hss
 * @Create_Date: 2019/9/25 23:19
 * @Update_By:
 * @Update_Date: 2019/9/25 23:19
 * U.H. All Rights Reserved.
 */
@RestController
@RequestMapping("/relationship")
public class CareRelationshipController {

    @Autowired
    private CareRelationshipService relationshipService;

    /**
     * 增加一个访客信息
     * @param careRelationshipPo 访客实体
     * @return ResponseDto
     */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody CareRelationshipPo careRelationshipPo){
        relationshipService.save(careRelationshipPo);
        return ResponseDto.builder().build();
    }

    /**
     * 删除一个访客信息
     * @param id 访客id
     * @return ResponseDto
     */
    @DeleteMapping("/{id}")
    public ResponseDto delete(@PathVariable Long id){
        relationshipService.delete(id);
        return ResponseDto.builder().build();
    }

    /**
     * 分页获取访客列表
     * @param relationshipPo 访客实体（携带查询参数）
     * @param page 页码
     * @param limit 每页大小
     * @return 访客列表
     */
    @GetMapping("/page")
    public ResponseDto getPage(CareRelationshipPo relationshipPo,int page, int limit){
        Page<CareRelationshipPo> p = relationshipService.getPage(relationshipPo, page, limit);
        return ResponseDto.builder().build().pageBuild(p);
    }

    /**
     * 更新一条访客信息
     * @param relationshipPo 访客信息实体
     * @param id 访客id
     * @return 更新结果
     */
    @PutMapping("/{id}")
    public ResponseDto update(@RequestBody CareRelationshipPo relationshipPo, @PathVariable("id") Long id){
        relationshipPo.setId(id);
        relationshipService.update(relationshipPo);
        return ResponseDto.builder().build();
    }

    /**
     * 批量删除访客信息
     *
     * @param ids
     * @return 删除结果
     */
    @DeleteMapping("/batch/del")
    public ResponseDto batchDeleteUser(@RequestBody Long[] ids) {
        relationshipService.batchDelCareRelationship(ids);
        return ResponseDto.builder().build();
    }

    /**
     * 导出为excel表格数据
     * @return
     */
    @PostMapping("/export")
    public ResponseDto exportList(@RequestBody CareRelationshipPo careRelationshipPo){
        List<CareRelationshipPo> list = relationshipService.exportList(careRelationshipPo);
        return ResponseDto.builder().data(list).build();
    }

}
