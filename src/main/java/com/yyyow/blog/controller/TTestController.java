package com.yyyow.blog.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yyyow.blog.common.utils.PageUtils;
import com.yyyow.blog.common.utils.R;
import com.yyyow.blog.entity.TTestEntity;
import com.yyyow.blog.service.ITTestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 
 * @since 2021-07-31
 */
@Api(tags = "测试")
@RestController
@RequestMapping("/api/v1")
public class TTestController {

    @Autowired
    ITTestService itTestService;

    @ApiOperation(value = "测试列表", notes = "测试列表", httpMethod = "GET")
    @GetMapping("/test")
    public R test(){

        PageHelper.startPage(1,10);

        return R.ok(R.SUCCESS,"查询成功",new PageUtils(new PageInfo<>(itTestService.list())));
    }
}
