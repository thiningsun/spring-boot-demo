package com.mp.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description 学生信息实体类
 * @Author Sans
 * @CreateTime 2019/5/26 21:41
 */
@Data
@TableName("user_info") //@TableName中的值对应着表名
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoEntity {

    /**
     * 主键
     * TableId中可以决定主键的类型,不写会采取默认值,默认值可以在yml中配置
     * AUTO: 数据库ID自增
     * INPUT: 用户输入ID
     * ID_WORKER: 全局唯一ID，Long类型的主键
     * ID_WORKER_STR: 字符串全局唯一ID
     * UUID: 全局唯一ID，UUID类型的主键
     * NONE: 该类型为未设置主键类型
     */
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "主键Id")
    private Long id;
    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名")
    private String name;
    /**
     * 年龄
     */
    @ApiModelProperty(value = "年龄")
    private Integer age;
    /**
     * 技能
     */
    @ApiModelProperty(value = "技能")
    private String skill;
    /**
     * 评价
     */
    @ApiModelProperty(value = "评价")
    private String evaluate;
    /**
     * 分数
     */
    @ApiModelProperty(value = "分数")
    private Long fraction;

//    private String appId;

}
