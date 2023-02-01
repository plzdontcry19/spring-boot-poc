package com.demo.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.demo.backend.bean.Docker;
import com.demo.backend.bean.BeanConfig;

@SpringBootApplication
public class BackendApplication {

	public static ApplicationContext retriveXmlBasedContext() {
		// XML Based Configuration
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		return context;

	}

	public static ApplicationContext retriveAnnotationBasedContext() {
		// Annotation Based Configuration
		ApplicationContext context = new AnnotationConfigApplicationContext(BeanConfig.class);
		return context;

	}

	public static void main(String[] args) {
		ApplicationContext context = retriveAnnotationBasedContext();
		Docker docker = context.getBean(Docker.class);
		docker.setName("helo");
		System.out.println(docker);

		docker.say("world!");
		System.out.println("pill: " + docker.retrivePill());
		SpringApplication.run(BackendApplication.class, args);
	}

}
