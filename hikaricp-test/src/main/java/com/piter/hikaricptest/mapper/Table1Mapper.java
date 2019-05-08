package com.piter.hikaricptest.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.piter.hikaricptest.entity.Table1;

import java.util.List;

/**
 * <p>
 * 'testdb.mtview' is not BASE TABLE Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2019-04-26
 */
public interface Table1Mapper extends BaseMapper<Table1> {

    int deleteByName(String name);

    List<Table1> selectByName(String aaa);
}
