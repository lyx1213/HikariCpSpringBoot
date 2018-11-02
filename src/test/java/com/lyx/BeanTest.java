package com.lyx;

import com.alibaba.fastjson.JSON;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BeanTest {

    @Autowired
    DataSourceProperties dataSourceProperties;
    @Autowired
    ApplicationContext applicationContext;
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    //@Ignore
    public void contentTest(){

        DataSource dataSource = applicationContext.getBean(DataSource.class);
        System.out.println(dataSource);
        System.out.println(dataSource.getClass().getName());
        System.out.println(dataSourceProperties);

        //JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);


        System.out.println("jdbcTemplate datasource:"+jdbcTemplate.getDataSource());

        List<Map<String, Object>> mapList = jdbcTemplate.queryForList("select * from public.t_company_license");
        String s = JSON.toJSONString(mapList);
        System.out.println(s);


    }



}
