package com.epiuse.mtaservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication

@ComponentScan({ "com.epiuse.mtaservice", "com.epiuse.mtaservice.rest" })
public class MtaServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MtaServiceApplication.class, args);
	}

}
