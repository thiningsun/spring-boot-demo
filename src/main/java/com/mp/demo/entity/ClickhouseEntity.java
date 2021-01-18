package com.mp.demo.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("clickhouse_info") //@TableName中的值对应着表名
public class ClickhouseEntity {

//    @TableId
//    private String id;

    private String query;
    private String eventTime;




}
