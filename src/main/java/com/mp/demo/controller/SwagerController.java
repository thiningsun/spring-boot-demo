package com.mp.demo.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mp.demo.entity.Result;
import com.mp.demo.entity.UserInfoEntity;
import com.mp.demo.service.UserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController//将所有返回数据转换成json...
@Api(description = "swager的controller请求类")
@RequestMapping("/swager")
public class SwagerController {
    private final AtomicLong counter = new AtomicLong();

    @Autowired
    private UserInfoService userInfoService;

    @ApiOperation(value = "方法注释：hello")
    @GetMapping(value = "/hello")
    public String hello() {
        String name = "zhangsan";
        return "counter: " + counter.incrementAndGet();
    }

    @ApiOperation(value = "方法注释：user")
    @PostMapping(value = "/user")
    public Result<List<UserInfoEntity>> user(@RequestBody @ApiParam(value = "方法参数注释：UserInfoEntity", required = true) UserInfoEntity userInfoEntity) {
        System.out.println("传入参数：" + userInfoEntity.toString());
        List<UserInfoEntity> userInfoEntityList = userInfoService.list();

        IPage<UserInfoEntity> page = new Page<>();
        page.setCurrent(Integer.valueOf(1)); //当前页
        page.setSize(5);    //每页条数
        IPage<UserInfoEntity> page1 = userInfoService.page(page);
        System.out.println("每页条数 :"+page1.toString());

        Result<List<UserInfoEntity>> listResult = Result.successData(userInfoEntityList);
        return listResult;
    }
}
