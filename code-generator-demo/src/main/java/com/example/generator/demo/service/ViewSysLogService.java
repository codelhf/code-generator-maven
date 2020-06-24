package com.example.generator.demo.service;

import com.example.generator.demo.common.ServerResponse;
import com.example.generator.demo.dto.ViewSysLogDTO;
import com.github.pagehelper.PageInfo;

import java.util.Map;

/**
 * @Title: ViewSysLogService
 * @Description: ViewSysLog接口层
 * @Company: example
 * @Author: liuhf
 * @CreateTime: 2020/06/24 18:29:01
 */
public interface ViewSysLogService {

	/**
	 * @Title: list
	 * @Description: 查询ViewSysLog列表
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
     * @Description: 查询ViewSysLog对象
     * @Company: example
     * @Author: liuhf
     * @CreateTime: 2020/06/24 18:29:01
     *
     * @param id
     * @return ServerResponse<ViewSysLogDTO>
     */
	ServerResponse<ViewSysLogDTO> select(String id);

}
