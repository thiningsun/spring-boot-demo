package com.mp.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mp.demo.entity.ClickhouseEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface ClickHouseQueryDao  extends BaseMapper<ClickhouseEntity> {
    //两种方式，一种是sql，另一种是直接写mapper.xml文件
    @Select("SELECT query,event_time FROM clickhouse_info WHERE event_time>=#{startTime} and query like 'SELECT%' limit 10")
    List<ClickhouseEntity> selectClickHouseLog(@Param("startTime")  String startTime);

}
