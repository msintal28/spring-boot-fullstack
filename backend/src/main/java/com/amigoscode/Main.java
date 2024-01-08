package com.amigoscode;

import com.amigoscode.customer.CustomerRepository;
import com.amigoscode.s3.S3Service;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    CommandLineRunner runner(
//            CustomerRepository customerRepository,
//                             S3Service s3Service
    ) {
        return args -> {
            /*
            Customer alex = new Customer(
                    "firstName1",
                    "%s.%s@gmail.com".formatted("firstName1", "lastName1"),
                    21
            );
            customerRepository.save(alex);
             */
        };
    }

}
