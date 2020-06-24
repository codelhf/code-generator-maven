package com.example.generator.demo.controller;

import com.example.generator.demo.common.ServerResponse;
import com.example.generator.demo.dto.SysLogDTO;
import com.example.generator.demo.service.SysLogService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;

import java.util.Map;

/**
 * @Title: SysLogController
 * @Description: SysLog控制层
 * @Company: example
 * @Author: liuhf
 * @CreateTime: 2020/06/24 18:29:01
 */
@RestController
@RequestMapping(value = "/mm/sysLog")
public class SysLogController {

    @Autowired
    private SysLogService sysLogService;

    @ApiOperation(value = "查询SysLog列表")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "pageNum", value = "分页当前页码", dataType = "Integer", required = false),
        @ApiImplicitParam(name = "pageSize", value = "分页一页大小", dataType = "Integer", required = false),
        @ApiImplicitParam(name = "params", value = "其他查询参数", dataType = "Map", required = false)
    })
    @GetMapping("")
    public ServerResponse<PageInfo> list(@RequestParam("pageNum") Integer pageNum,
                                            @RequestParam("pageSize") Integer pageSize,
                                            @RequestParam("params") Map<String, String> params) {
        return sysLogService.list(pageNum, pageSize, params);
    }

    @ApiOperation(value = "查询SysLog对象}")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "SysLog主键", dataType = "Integer", required = true)
    })
    @GetMapping("/{id}")
    public ServerResponse<SysLogDTO> select(@PathVariable("id") Integer id) {
        return sysLogService.select(id);
    }

    @ApiOperation(value = "保存SysLog对象")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "sysLogDTO", value = "SysLogDTO对象", dataType = "SysLogDTO", required = true)
    })
    @PostMapping("")
    public ServerResponse<String> insert(@RequestBody SysLogDTO sysLogDTO) {
        return sysLogService.insert(sysLogDTO);
    }

    @ApiOperation(value = "更新SysLog对象")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "SysLog主键", dataType = "Integer", required = true),
        @ApiImplicitParam(name = "sysLogDTO", value = "SysLogDTO对象", dataType = "SysLogDTO", required = true)
    })
    @PutMapping("/{id}")
    public ServerResponse<String> update(@PathVariable("id") Integer id,
                                           @RequestBody SysLogDTO sysLogDTO) {
        return sysLogService.update(id, sysLogDTO);
    }

    @ApiOperation(value = "批量删除SysLog对象}")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "ids", value = "SysLog主键字符串,用 , 分隔", dataType = "String", required = true)
    })
    @DeleteMapping("/{ids}")
    public ServerResponse<String> delete(@PathVariable("ids") String ids) {
        return sysLogService.delete(ids);
    }
}
