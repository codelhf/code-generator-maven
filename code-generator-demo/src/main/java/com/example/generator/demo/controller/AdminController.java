package com.example.generator.demo.controller;

import com.example.generator.demo.common.ServerResponse;
import com.example.generator.demo.dto.AdminDTO;
import com.example.generator.demo.service.AdminService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;

import java.util.Map;

/**
 * @Title: AdminController
 * @Description: Admin控制层
 * @Company: example
 * @Author: liuhf
 * @CreateTime: 2020/04/13 16:00:43
 */
@RestController
@RequestMapping(value = "/mm/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @ApiOperation(value = "查询Admin列表")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "pageNum", value = "分页当前页码", dataType = "Integer", required = false),
        @ApiImplicitParam(name = "pageSize", value = "分页一页大小", dataType = "Integer", required = false),
        @ApiImplicitParam(name = "params", value = "其他查询参数", dataType = "Map", required = false)
    })
    @GetMapping("")
    public ServerResponse<PageInfo> list(@RequestParam("pageNum") Integer pageNum,
                                            @RequestParam("pageSize") Integer pageSize,
                                            @RequestParam("params") Map<String, String> params) {
        return adminService.list(pageNum, pageSize, params);
    }

    @ApiOperation(value = "查询Admin对象}")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "Admin主键", dataType = "Integer", required = true)
    })
    @GetMapping("/{id}")
    public ServerResponse<AdminDTO> select(@PathVariable("id") Integer id) {
        return adminService.select(id);
    }

    @ApiOperation(value = "保存Admin对象")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "adminDTO", value = "AdminDTO对象", dataType = "AdminDTO", required = true)
    })
    @PostMapping("")
    public ServerResponse<String> insert(@RequestBody AdminDTO adminDTO) {
        return adminService.insert(adminDTO);
    }

    @ApiOperation(value = "更新Admin对象")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "Admin主键", dataType = "Integer", required = true),
        @ApiImplicitParam(name = "adminDTO", value = "AdminDTO对象", dataType = "AdminDTO", required = true)
    })
    @PutMapping("/{id}")
    public ServerResponse<String> update(@PathVariable("id") Integer id,
                                           @RequestBody AdminDTO adminDTO) {
        return adminService.update(id, adminDTO);
    }

    @ApiOperation(value = "批量删除Admin对象}")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "ids", value = "Admin主键字符串,用 , 分隔", dataType = "String", required = true)
    })
    @DeleteMapping("/{ids}")
    public ServerResponse<String> delete(@PathVariable("ids") String ids) {
        return adminService.delete(ids);
    }
}
