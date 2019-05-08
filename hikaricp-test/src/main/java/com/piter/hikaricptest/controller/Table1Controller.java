package com.piter.hikaricptest.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.additional.query.impl.QueryChainWrapper;
import com.piter.hikaricptest.entity.Table1;
import com.piter.hikaricptest.mapper.Table1Mapper;
import com.piter.hikaricptest.service.impl.Table1ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.table.TableModel;

/**
 * <p>
 * 'testdb.mtview' is not BASE TABLE 前端控制器
 * </p>
 *
 * @author jobob
 * @since 2019-04-26
 */
@RestController
@RequestMapping("/table1")
public class Table1Controller{

    @Autowired
    private Table1ServiceImpl table1Service;

    @Autowired
    private Table1Mapper table1Mapper;

    @RequestMapping("/test1")
    public Object test(){
       return table1Mapper.selectList(new QueryWrapper<Table1>().eq("name","aaa"));
    }

    @RequestMapping("/test2")
    public Object test2(){
        return table1Mapper.selectByName("aaa");
    }

    @RequestMapping("/test3")
    public Object test3(){
        Table1 param = new Table1();
        param.setName("aaa");
        return table1Mapper.delete(Wrappers .<Table1>lambdaQuery(param));
    }

}

