package com.example.generator.demo.controller;

import com.example.generator.demo.common.ServerResponse;
import com.example.generator.demo.dto.ViewSysLogDTO;
import com.example.generator.demo.service.ViewSysLogService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;

import java.util.Map;

/**
 * @Title: ViewSysLogController
 * @Description: ViewSysLog控制层
 * @Company: example
 * @Author: liuhf
 * @CreateTime: 2020/06/24 18:29:01
 */
@RestController
@RequestMapping(value = "/mm/viewSysLog")
public class ViewSysLogController {

    @Autowired
    private ViewSysLogService viewSysLogService;

    @ApiOperation(value = "查询ViewSysLog列表")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "pageNum", value = "分页当前页码", dataType = "Integer", required = false),
        @ApiImplicitParam(name = "pageSize", value = "分页一页大小", dataType = "Integer", required = false),
        @ApiImplicitParam(name = "params", value = "其他查询参数", dataType = "Map", required = false)
    })
    @GetMapping("")
    public ServerResponse<PageInfo> list(@RequestParam("pageNum") Integer pageNum,
                                            @RequestParam("pageSize") Integer pageSize,
                                            @RequestParam("params") Map<String, String> params) {
        return viewSysLogService.list(pageNum, pageSize, params);
    }

    @ApiOperation(value = "查询ViewSysLog对象}")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "ViewSysLog主键", dataType = "String", required = true)
    })
    @GetMapping("/{id}")
    public ServerResponse<ViewSysLogDTO> select(@PathVariable("id") String id) {
        return viewSysLogService.select(id);
    }

}
