package com.bw.bitcoinexplorer;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.bw.bitcoinexplorer.dao")
@EnableFeignClients
@EnableAsync
@EnableScheduling
public class BitcoinexplorerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BitcoinexplorerApplication.class, args);
    }

}
