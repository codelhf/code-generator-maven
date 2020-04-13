package com.example.generator.demo.controller;

import com.example.generator.demo.common.ServerResponse;
import com.example.generator.demo.dto.PrizeDTO;
import com.example.generator.demo.service.PrizeService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;

import java.util.Map;

/**
 * @Title: PrizeController
 * @Description: Prize控制层
 * @Company: example
 * @Author: liuhf
 * @CreateTime: 2020/04/13 16:00:44
 */
@RestController
@RequestMapping(value = "/mm/prize")
public class PrizeController {

    @Autowired
    private PrizeService prizeService;

    @ApiOperation(value = "查询Prize列表")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "pageNum", value = "分页当前页码", dataType = "Integer", required = false),
        @ApiImplicitParam(name = "pageSize", value = "分页一页大小", dataType = "Integer", required = false),
        @ApiImplicitParam(name = "params", value = "其他查询参数", dataType = "Map", required = false)
    })
    @GetMapping("")
    public ServerResponse<PageInfo> list(@RequestParam("pageNum") Integer pageNum,
                                            @RequestParam("pageSize") Integer pageSize,
                                            @RequestParam("params") Map<String, String> params) {
        return prizeService.list(pageNum, pageSize, params);
    }

    @ApiOperation(value = "查询Prize对象}")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "Prize主键", dataType = "Integer", required = true)
    })
    @GetMapping("/{id}")
    public ServerResponse<PrizeDTO> select(@PathVariable("id") Integer id) {
        return prizeService.select(id);
    }

    @ApiOperation(value = "保存Prize对象")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "prizeDTO", value = "PrizeDTO对象", dataType = "PrizeDTO", required = true)
    })
    @PostMapping("")
    public ServerResponse<String> insert(@RequestBody PrizeDTO prizeDTO) {
        return prizeService.insert(prizeDTO);
    }

    @ApiOperation(value = "更新Prize对象")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "Prize主键", dataType = "Integer", required = true),
        @ApiImplicitParam(name = "prizeDTO", value = "PrizeDTO对象", dataType = "PrizeDTO", required = true)
    })
    @PutMapping("/{id}")
    public ServerResponse<String> update(@PathVariable("id") Integer id,
                                           @RequestBody PrizeDTO prizeDTO) {
        return prizeService.update(id, prizeDTO);
    }

    @ApiOperation(value = "批量删除Prize对象}")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "ids", value = "Prize主键字符串,用 , 分隔", dataType = "String", required = true)
    })
    @DeleteMapping("/{ids}")
    public ServerResponse<String> delete(@PathVariable("ids") String ids) {
        return prizeService.delete(ids);
    }
}
