package com.myy.bpds.projectservice;

import com.myy.bpds.common.dto.BpdsContext;
import com.myy.bpds.common.utils.BpdsContextHolder;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.myy.bpds.projectservice.mapper")
public class ProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectApplication.class, args);
        BpdsContextHolder.set(new BpdsContext());
    }

}
