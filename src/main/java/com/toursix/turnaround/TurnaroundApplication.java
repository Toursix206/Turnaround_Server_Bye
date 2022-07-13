package com.toursix.turnaround;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableFeignClients
@EnableJpaAuditing
@SpringBootApplication
@EnableSwagger2
public class TurnaroundApplication {

    public static void main(String[] args) {
        SpringApplication.run(TurnaroundApplication.class, args);
    }

}
