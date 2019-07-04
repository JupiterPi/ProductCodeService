package kaye.productcodeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;

@SpringBootApplication
public class ProductCodeService {
    public static void main (String[] args) {
        SpringApplication.run(ProductCodeService.class, args);
    }
}