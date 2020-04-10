package com.example.generator.demo.service;

import com.example.generator.demo.common.ServerResponse;
import com.example.generator.demo.dto.ResultDTO;
import com.github.pagehelper.PageInfo;

import java.util.Map;

/**
 * @Title: ResultService
 * @Description: Result接口层
 * @Company: example
 * @Author: liuhf
 * @CreateTime: 2020/04/10 23:39:06
 */
public interface ResultService {

	/**
	 * @Title: list
	 * @Description: 查询Result列表
	 * @Company: example
	 * @Author: liuhf
	 * @CreateTime: 2020/04/10 23:39:06
	 *
	 * @param pageNum
	 * @param pageSize
	 * @param params
	 * @return ServerResponse<PageInfo>
	 */
	ServerResponse<PageInfo> list(Integer pageNum, Integer pageSize, Map<String, String> params);

    /**
     * @Title: select
     * @Description: 查询Result对象
     * @Company: example
     * @Author: liuhf
     * @CreateTime: 2020/04/10 23:39:06
     *
     * @param id
     * @return ServerResponse<ResultDTO>
     */
	ServerResponse<ResultDTO> select(String id);

}
