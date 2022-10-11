package br.com.guilhermebehs.ftgo.kitchen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
public class KitchenMsApplication {

	@LoadBalanced
	@Bean
	RestTemplate restTemplate() {
		var restTemplate = new RestTemplate();
		restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
		return restTemplate;
	}

	public static void main(String[] args) {
		SpringApplication.run(KitchenMsApplication.class, args);
	}

}
