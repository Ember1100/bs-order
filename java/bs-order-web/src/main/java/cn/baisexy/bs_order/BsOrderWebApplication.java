package cn.baisexy.bs_order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("cn.baisexy.bs_order.system.mapper")
@EnableTransactionManagement
public class BsOrderWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(BsOrderWebApplication.class, args);
    }

}
