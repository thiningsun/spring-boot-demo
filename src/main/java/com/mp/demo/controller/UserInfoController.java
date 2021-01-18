package com.mp.demo.controller;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mp.demo.entity.UserInfoEntity;
import com.mp.demo.service.UserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

/**
 * @Description UserInfoController
 * @Author Sans
 * @CreateTime 2019/6/8 16:27
 */
@RestController
@RequestMapping("/userInfo")
@Slf4j
@Api(description = "userInfo的controller层")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    /**
     * 根据ID获取用户信息
     * @Author Sans
     * @CreateTime 2019/6/8 16:34
     * @Param  userId  用户ID
     * @Return UserInfoEntity 用户实体
     */
    @DS("slave_1")//使用slave数据源，不注明使用默认数据源
    @GetMapping("/getInfo/{userId}")
    @ApiOperation(value = "slave数据源查询")
    public UserInfoEntity getInfo(@ApiParam(value = "传入参数userId")@PathVariable("userId") String userId){
        UserInfoEntity userInfoEntity = userInfoService.getById(userId);
        return userInfoEntity;
    }

    /**
     * 查询全部信息
     * @Author Sans
     * @CreateTime 2019/6/8 16:35
     * @Param  userId  用户ID
     * @Return List<UserInfoEntity> 用户实体集合
     */
    @ApiOperation(value = "查询全部信息")
    @GetMapping("/getList")
    public List<UserInfoEntity> getList(){
        List<UserInfoEntity> userInfoEntityList = userInfoService.list();
        return userInfoEntityList;
    }

    /**
     * 分页查询全部数据
     * @Author Sans
     * @CreateTime 2019/6/8 16:37
     * @Return IPage<UserInfoEntity> 分页数据
     */
    @ApiOperation(value = "分页查询全部数据")
    @GetMapping("/getInfoListPage/{start}")
    public IPage<UserInfoEntity> getInfoListPage(@ApiParam(value = "分页查询的起始页")@PathVariable("start") String start){
        //需要在Config配置类中配置分页插件
        IPage<UserInfoEntity> page = new Page<>();
        page.setCurrent(Integer.valueOf(start)); //当前页
        page.setSize(5);    //每页条数

        QueryWrapper<UserInfoEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("age", 18);
//        queryWrapper.lambda().between(UserInfoEntity::getAge,10,18).eq(UserInfoEntity::getAge, 18)
//                ;

        page = userInfoService.page(page, queryWrapper);

        return page;
    }

    /**
     * 根据指定字段查询用户信息集合
     * @Author Sans
     * @CreateTime 2019/6/8 16:39
     * @Return Collection<UserInfoEntity> 用户实体集合
     */
    @ApiOperation(value = "根据指定字段查询用户信息集合")
    @GetMapping("/getListMap")
    public Collection<UserInfoEntity> getListMap(){
        Map<String,Object> map = new HashMap<>();
        //kay是字段名 value是字段值
        map.put("age",20);
        Collection<UserInfoEntity> userInfoEntityList = userInfoService.listByMap(map);
        return userInfoEntityList;
    }

    /**
     * 新增用户信息
     * @Author Sans
     * @CreateTime 2019/6/8 16:40
     */
    @ApiOperation(value = "新增用户信息")
    @GetMapping("/saveInfo")
    public String saveInfo(){
        UserInfoEntity userInfoEntity = new UserInfoEntity();
        userInfoEntity.setName("小龙");
        userInfoEntity.setSkill("JAVA");
        userInfoEntity.setAge(18);
        userInfoEntity.setFraction(59L);
        userInfoEntity.setEvaluate("该学生是一个在改BUG的码农");
        userInfoService.save(userInfoEntity);
        log.info("插入{}数据成功,{}", 1, userInfoEntity.toString());
        return "成功";
    }


    /**
     * 批量新增用户信息
     * @Author Sans
     * @CreateTime 2019/6/8 16:42
     */
    @ApiOperation(value = "批量新增用户信息")
    @GetMapping("/saveInfoList")
    public void saveInfoList(){
        //创建对象
        UserInfoEntity sans = new UserInfoEntity();
        sans.setName("Sans");
        sans.setSkill("睡觉");
        sans.setAge(18);
        sans.setFraction(60L);
        sans.setEvaluate("Sans是一个爱睡觉,并且身材较矮骨骼巨大的骷髅小胖子");
        UserInfoEntity papyrus = new UserInfoEntity();
        papyrus.setName("papyrus");
        papyrus.setSkill("JAVA");
        papyrus.setAge(18);
        papyrus.setFraction(58L);
        papyrus.setEvaluate("Papyrus是一个讲话大声、个性张扬的骷髅，给人自信、有魅力的骷髅小瘦子");
        //批量保存
        List<UserInfoEntity> list =new ArrayList<>();
        list.add(sans);
        list.add(papyrus);
        userInfoService.saveBatch(list);
        log.info("插入{}数据成功", list.size());
    }
    /**
     * 更新用户信息
     * @Author Sans
     * @CreateTime 2019/6/8 16:47
     */
    @ApiOperation(value = "更新用户信息")
    @GetMapping("/updateInfo")
    public void updateInfo(){
        //根据实体中的ID去更新,其他字段如果值为null则不会更新该字段,参考yml配置文件
        UserInfoEntity userInfoEntity = new UserInfoEntity();
        userInfoEntity.setId(1L);
        userInfoEntity.setAge(19);
        userInfoService.updateById(userInfoEntity);
    }
    /**
     * 新增或者更新用户信息
     * @Author Sans
     * @CreateTime 2019/6/8 16:50
     */
    @ApiOperation(value = "新增或者更新用户信息")
    @GetMapping("/saveOrUpdateInfo")
    public void saveOrUpdate(){
        //传入的实体类userInfoEntity中ID为null就会新增(ID自增)
        //实体类ID值存在,如果数据库存在ID就会更新,如果不存在就会新增
        UserInfoEntity userInfoEntity = new UserInfoEntity();
        userInfoEntity.setId(1L);
        userInfoEntity.setAge(20);
        userInfoService.saveOrUpdate(userInfoEntity);
    }
    /**
     * 根据ID删除用户信息
     * @Author Sans
     * @CreateTime 2019/6/8 16:52
     */
    @ApiOperation(value = "根据ID删除用户信息")
    @GetMapping("/deleteInfo/{userId}")
    public String deleteInfo(@ApiParam(value = "传入userId")@PathVariable("userId") String userId){
        userInfoService.removeById(userId);
        return "删除userid=" + userId + ",数据成功";
    }


    /**
     * 根据ID批量删除用户信息
     * @Author Sans
     * @CreateTime 2019/6/8 16:55
     */
    @ApiOperation(value = "根据ID批量删除用户信息")
    @GetMapping("/deleteInfoList")
    public void deleteInfoList(){
        List<String> userIdlist = new ArrayList<>();
        userIdlist.add("12");
        userIdlist.add("13");
        userInfoService.removeByIds(userIdlist);
    }


    /**
     * 根据指定字段删除用户信息
     * @Author Sans
     * @CreateTime 2019/6/8 16:57
     */
    @ApiOperation(value = "根据指定字段删除用户信息")
    @GetMapping("/deleteInfoMap")
    public void deleteInfoMap(){
        //kay是字段名 value是字段值
        Map<String,Object> map = new HashMap<>();
        map.put("skill", null);

        map.put("fraction",60L);
        userInfoService.removeByMap(map);
    }

}