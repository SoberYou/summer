package com.cq.summer.dao;

import com.cq.summer.datasource.DataSourceNames;
import com.cq.summer.datasource.annotation.DataSource;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SystemDao {

    String test();

    @DataSource(name = DataSourceNames.BANANA)
    String test1();

    @DataSource(name = DataSourceNames.CHERRY)
    String test2();
}
