package com.example.generator.demo.controller;

import com.example.generator.demo.common.ServerResponse;
import com.example.generator.demo.dto.UserDTO;
import com.example.generator.demo.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;

import java.util.Map;

/**
 * @Title: UserController
 * @Description: User控制层
 * @Company: example
 * @Author: liuhf
 * @CreateTime: 2020/04/10 23:39:06
 */
@RestController
@RequestMapping(value = "/mm/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "查询User列表")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "pageNum", value = "分页当前页码", dataType = "Integer", required = false),
        @ApiImplicitParam(name = "pageSize", value = "分页一页大小", dataType = "Integer", required = false),
        @ApiImplicitParam(name = "params", value = "其他查询参数", dataType = "Map", required = false)
    })
    @GetMapping("")
    public ServerResponse<PageInfo> list(@RequestParam("pageNum") Integer pageNum,
                                            @RequestParam("pageSize") Integer pageSize,
                                            @RequestParam("params") Map<String, String> params) {
        return userService.list(pageNum, pageSize, params);
    }

    @ApiOperation(value = "查询User对象}")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "User主键", dataType = "Integer", required = true)
    })
    @GetMapping("/{id}")
    public ServerResponse<UserDTO> select(@PathVariable("id") Integer id) {
        return userService.select(id);
    }

    @ApiOperation(value = "保存User对象")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "userDTO", value = "UserDTO对象", dataType = "UserDTO", required = true)
    })
    @PostMapping("")
    public ServerResponse<String> insert(@RequestBody UserDTO userDTO) {
        return userService.insert(userDTO);
    }

    @ApiOperation(value = "更新User对象")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "User主键", dataType = "Integer", required = true),
        @ApiImplicitParam(name = "userDTO", value = "UserDTO对象", dataType = "UserDTO", required = true)
    })
    @PutMapping("/{id}")
    public ServerResponse<String> update(@PathVariable("id") Integer id,
                                           @RequestBody UserDTO userDTO) {
        return userService.update(id, userDTO);
    }

    @ApiOperation(value = "批量删除User对象}")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "ids", value = "User主键字符串,用 , 分隔", dataType = "String", required = true)
    })
    @DeleteMapping("/{ids}")
    public ServerResponse<String> delete(@PathVariable("ids") String ids) {
        return userService.delete(ids);
    }
}
