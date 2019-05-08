package com.example.generator.demo.controller;

import com.example.generator.demo.common.ServerResponse;
import com.example.generator.demo.dto.PeopleDTO;
import com.example.generator.demo.service.PeopleService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;

import java.util.Map;

/**
 * @Title: PeopleController
 * @Description: People控制层
 * @Company: example
 * @Author: liuhf
 * @CreateTime: 2019-05-09 00:02:24
 */
@RestController
@RequestMapping(value = "/people")
public class PeopleController {

    @Autowired
    private PeopleService peopleService;

    @ApiOperation(value = "查询People列表")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "pageNum", value = "分页当前页码", dataType = "Integer", required = false),
        @ApiImplicitParam(name = "pageSize", value = "分页一页大小", dataType = "Integer", required = false),
        @ApiImplicitParam(name = "params", value = "其他查询参数", dataType = "Map", required = false)
    })
    @GetMapping("")
    public ServerResponse<PageInfo> list(@RequestParam("pageNum") Integer pageNum,
                                           @RequestParam("pageSize") Integer pageSize,
                                           @RequestParam("params") Map<String, String> params) {
        return peopleService.list(pageNum, pageSize, params);
    }

    @ApiOperation(value = "查询People对象}")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "People主键", dataType = "int", required = true)
    })
    @GetMapping("/{id}")
    public ServerResponse<PeopleDTO> select(@PathVariable("id") int id) {
        return peopleService.select(id);
    }

    @ApiOperation(value = "保存People对象")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "peopleDTO", value = "PeopleDTO对象", dataType = "PeopleDTO", required = true)
    })
    @PostMapping("")
    public ServerResponse<String> insert(@RequestBody PeopleDTO peopleDTO) {
        return peopleService.insert(peopleDTO);
    }

    @ApiOperation(value = "更新People对象")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "People主键", dataType = "int", required = true),
        @ApiImplicitParam(name = "peopleDTO", value = "PeopleDTO对象", dataType = "PeopleDTO", required = true)
    })
    @PutMapping("/{id}")
    public ServerResponse<String> update(@PathVariable("id") int id,
                                           @RequestBody PeopleDTO peopleDTO) {
        return peopleService.update(id, peopleDTO);
    }

    @ApiOperation(value = "批量删除People对象}")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "ids", value = "People主键字符串,用 , 分隔", dataType = "String", required = true)
    })
    @DeleteMapping("/{ids}")
    public ServerResponse<String> delete(@PathVariable("ids") String ids) {
        return peopleService.delete(ids);
    }
}
