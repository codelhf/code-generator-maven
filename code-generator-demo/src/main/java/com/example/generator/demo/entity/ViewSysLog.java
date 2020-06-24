package com.example.generator.demo.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Title: ViewSysLog
 * @Description: ViewSysLog实体对象
 * @Company: example
 * @Author: liuhf
 * @CreateTime: 2020/06/24 18:29:01
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ViewSysLog implements Serializable {
    private static final long serialVersionUID = 1L;

    //记录id
    @Column(name = "id")
    private Integer id;

    //权限更新的类型，1：部门，2：用户，3：权限模块，4：权限，5：角色，6角色用户关系，7角色权限关系
    @Column(name = "type")
    private Integer type;

    //基于type制定后的对象id，比如用户，权限，角色等表的主键
    @Column(name = "target_id")
    private Integer targetId;

    //旧值
    @Column(name = "old_value")
    private String oldValue;

    //新值
    @Column(name = "new_value")
    private String newValue;

    //操作者
    @Column(name = "operator")
    private String operator;

    //最后一次操作时间
    @Column(name = "operate_time")
    private Date operateTime;

    //最后一次更新者的IP
    @Column(name = "operate_ip")
    private String operateIp;

    //当前是否复原过，0：没有，1：复原过
    @Column(name = "status")
    private Integer status;

}
