package com.projekt.projekt;

import java.util.Properties;

import com.projekt.projekt.Controllers.DatabaseController;
import com.projekt.projekt.Controllers.SecurityController;
import com.projekt.projekt.Controllers.WebController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@SpringBootApplication(scanBasePackages={"com.projekt.projekt.Controllers.WebController", " com.projekt.projekt.Controllers.DatabaseController", "com.projekt.projekt.SecurityController"})
public class ProjektApplication {


	@Bean
 	public SecurityController myConfig() {
		SecurityController myConfig = new SecurityController();
    	return myConfig;
 	}

	@Bean
	public WebController webContr(){
		WebController webcontr = new WebController();
		return webcontr;
	}
	@Bean
    public DatabaseController db(){
        DatabaseController db = new DatabaseController();
        return db;
    }
	public static void main(String... args) {
		SpringApplication.run(ProjektApplication.class, args);
	}

	@Bean
	public JavaMailSender getJavaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);
		
		mailSender.setUsername("my.gmail@gmail.com");
		mailSender.setPassword("password");
		
		Properties props = mailSender.getJavaMailProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.debug", "true");
		
		return mailSender;
	}
}
