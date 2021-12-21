package com.projekt.projekt;

import com.projekt.projekt.Controllers.DatabaseController;
import com.projekt.projekt.Controllers.SecurityController;
import com.projekt.projekt.Controllers.WebController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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

}
