package com.mp.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mp.demo.entity.ClickhouseEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ClickHouseQueryService extends IService<ClickhouseEntity> {
    List<ClickhouseEntity> selectClickHouseLog(@Param("startTime")  String startTime);
}
