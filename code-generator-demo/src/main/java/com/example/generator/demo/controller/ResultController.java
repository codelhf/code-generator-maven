package com.example.generator.demo.controller;

import com.example.generator.demo.common.ServerResponse;
import com.example.generator.demo.dto.ResultDTO;
import com.example.generator.demo.service.IResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;

import java.util.Map;

/**
 * @Title: ResultController
 * @Description: Result控制层
 * @Company: example
 * @Author: liuhf
 * @CreateTime: 2019-04-11 16:10:39
 */
@RestController
@RequestMapping(value = "/result")
public class ResultController {

    @Autowired
    private IResultService iResultService;

    @ApiOperation(value = "查询Result列表")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "pageNum", value = "分页当前页码", dataType = "Integer", required = false),
        @ApiImplicitParam(name = "pageSize", value = "分页一页大小", dataType = "Integer", required = false),
        @ApiImplicitParam(name = "params", value = "其他查询参数", dataType = "Map", required = false),
        })
    @GetMapping("")
    public ServerResponse<Object> list(@RequestParam("pageNum") Integer pageNum,
                                       @RequestParam("pageSize") Integer pageSize,
                                       @RequestParam("params") Map<String, String> params) {
        return iResultService.list(pageNum, pageSize, params);
    }


    @ApiOperation(value = "查询Result对象")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "Result主键", dataType = "String", required = true),
        })
    @GetMapping("/{id}")
    public ServerResponse<ResultDTO> select(@PathVariable("id") String id) {
        return iResultService.select(id);
    }



}
