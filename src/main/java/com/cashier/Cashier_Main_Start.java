package com.cashier;

import com.cashier.pojo.form.CommodityListForm;
import com.cashier.service.CommodityListService;
import com.cashier.service.impl.CommodityListImpl;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

@SpringBootApplication
@MapperScan("com.cashier.mapper")
@EnableCaching
public class Cashier_Main_Start {
    public static void main(String[] args) {
        SpringApplication.run(Cashier_Main_Start.class, args);
    }

}
