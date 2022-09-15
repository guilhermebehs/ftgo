package br.com.guilhermebehs.payment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@SpringBootApplication
public class PaymentMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentMsApplication.class, args);
	}

}
