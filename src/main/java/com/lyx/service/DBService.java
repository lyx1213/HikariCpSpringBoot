package com.lyx.service;

import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Service
public class DBService {

    private static final Logger logger = LoggerFactory.getLogger(DBService.class);

    @Autowired
    private HikariDataSource hikariDataSource;

    @Scheduled(cron = "0/10 * * * * ?")
    public void testHiCp() {
        logger.info("********testHiCp******");
        try {
            Connection connection = hikariDataSource.getConnection();
            String sql = "select distinct company_id from public.t_company_license";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int company_id = resultSet.getInt("company_id");
                System.out.println(company_id + "***************");
            }
            preparedStatement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
