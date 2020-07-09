package com.erpweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

/*exclude = SecurityAutoConfiguration.class*/
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class ServiciosErpApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(ServiciosErpApplication.class, args);
	}


}
