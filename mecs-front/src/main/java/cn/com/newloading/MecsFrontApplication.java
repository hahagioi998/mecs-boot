package cn.com.newloading;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@MapperScan("cn.com.newloading.dao")
public class MecsFrontApplication extends SpringBootServletInitializer {
	public static void main(String[] args) {
		SpringApplication.run(MecsFrontApplication.class, args);
	}

}
