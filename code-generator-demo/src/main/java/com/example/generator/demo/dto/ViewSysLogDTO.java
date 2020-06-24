package com.example.generator.demo.dto;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * @Title: ViewSysLogDTO
 * @Description: ViewSysLogDTO对象
 * @Company: example
 * @Author: liuhf
 * @CreateTime: 2020/06/24 18:29:01
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ViewSysLogDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    //记录id
    private Integer id;

    //权限更新的类型，1：部门，2：用户，3：权限模块，4：权限，5：角色，6角色用户关系，7角色权限关系
    private Integer type;

    //基于type制定后的对象id，比如用户，权限，角色等表的主键
    private Integer targetId;

    //旧值
    private String oldValue;

    //新值
    private String newValue;

    //操作者
    private String operator;

    //最后一次操作时间
    private Date operateTime;

    //最后一次更新者的IP
    private String operateIp;

    //当前是否复原过，0：没有，1：复原过
    private Integer status;

}
