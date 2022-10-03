package br.com.guilhermebehs.ftgo.kitchen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class KitchenMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(KitchenMsApplication.class, args);
	}

}
