package com.mp.demo.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mp.demo.mapper.ClickHouseQueryDao;
import com.mp.demo.entity.ClickhouseEntity;
import com.mp.demo.service.ClickHouseQueryService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@DS("slave_1")
public class ClickHouseQueryServiceImpl extends ServiceImpl<ClickHouseQueryDao, ClickhouseEntity> implements ClickHouseQueryService {

    @Override
    public List<ClickhouseEntity> selectClickHouseLog(String startTime) {
        return this.baseMapper.selectClickHouseLog(startTime);
    }

}
