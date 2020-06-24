package com.example.generator.demo.service;

import com.example.generator.demo.common.ServerResponse;
import com.example.generator.demo.dto.SysLogDTO;
import com.github.pagehelper.PageInfo;

import java.util.Map;

/**
 * @Title: SysLogService
 * @Description: SysLog接口层
 * @Company: example
 * @Author: liuhf
 * @CreateTime: 2020/06/24 18:29:01
 */
public interface SysLogService {

	/**
	 * @Title: list
	 * @Description: 查询SysLog列表
	 * @Company: example
	 * @Author: liuhf
	 * @CreateTime: 2020/06/24 18:29:01
	 *
	 * @param pageNum
	 * @param pageSize
	 * @param params
	 * @return ServerResponse<PageInfo>
	 */
	ServerResponse<PageInfo> list(Integer pageNum, Integer pageSize, Map<String, String> params);

    /**
     * @Title: select
     * @Description: 查询SysLog对象
     * @Company: example
     * @Author: liuhf
     * @CreateTime: 2020/06/24 18:29:01
     *
     * @param id
     * @return ServerResponse<SysLogDTO>
     */
	ServerResponse<SysLogDTO> select(Integer id);

    /**
     * @Title: insert
     * @Description: 保存SysLog对象
     * @Company: example
     * @Author: liuhf
     * @CreateTime: 2020/06/24 18:29:01
     *
     * @param sysLogDTO
     * @return ServerResponse<String>
     */
	ServerResponse<String> insert(SysLogDTO sysLogDTO);

    /**
     * @Title: update
     * @Description: 更新SysLog对象
     * @Company: example
     * @Author: liuhf
     * @CreateTime: 2020/06/24 18:29:01
     *
     * @param id
     * @param sysLogDTO
     * @return ServerResponse<String>
     */
	ServerResponse<String> update(Integer id, SysLogDTO sysLogDTO);

    /**
     * @Title: delete
     * @Description: 批量删除SysLog对象
     * @Company: example
     * @Author: liuhf
     * @CreateTime: 2020/06/24 18:29:01
     *
     * @param ids
     * @return ServerResponse<String>
     */
	ServerResponse<String> delete(String ids);
}
