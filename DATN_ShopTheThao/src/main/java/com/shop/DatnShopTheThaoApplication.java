package com.shop;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DatnShopTheThaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DatnShopTheThaoApplication.class, args);
//		Runtime rt = Runtime.getRuntime();
//		try {
//		    rt.exec("cmd /c start msedge http://localhost:8080/home");
//		} catch (IOException e) {
//		    e.printStackTrace();
//		}
	}

}
