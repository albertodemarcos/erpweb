package com.erpweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*exclude = SecurityAutoConfiguration.class*/
@SpringBootApplication()
public class ServiciosErpApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(ServiciosErpApplication.class, args);
	}


}
