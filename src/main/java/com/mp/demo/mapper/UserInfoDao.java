package com.mp.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mp.demo.entity.UserInfoEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @Description 用户信息DAO
 * @Author Sans
 * @CreateTime 2019/6/8 16:24
 */
@Mapper
public interface UserInfoDao extends BaseMapper<UserInfoEntity> {

    /**
     * 查询大于该分数的学生
     * @Author Sans
     * @CreateTime 2019/6/9 14:28
     * @Param  page  分页参数
     * @Param  fraction  分数
     * @Return IPage<UserInfoEntity> 分页数据
     */
    //两种方式，一种是sql，另一种是直接写mapper.xml文件,同时存在则优先加载xml
    @Select("SELECT * FROM user_info WHERE fraction > #{fraction}")
    IPage<UserInfoEntity> selectUserInfoByGtFraction(IPage<UserInfoEntity> page,@Param("fraction")  Long fraction);

}
