package com.demo.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.demo.backend.bean.Docker;
import com.demo.backend.bean.Nurse;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		
		// XML Based Configuration
		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml")) {
			Docker docker = context.getBean(Docker.class);
			docker.heal();
			System.out.println("getName: " + docker.getName());
			Nurse nurse = docker.getNurse();
			nurse.heal();
		}

		SpringApplication.run(BackendApplication.class, args);
	}

}
