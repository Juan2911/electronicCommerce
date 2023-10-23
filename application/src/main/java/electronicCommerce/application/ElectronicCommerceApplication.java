package electronicCommerce.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "electronicCommerce.persistence.h2.repositories")
@EntityScan(basePackages = "electronicCommerce.persistence.h2.entities")
@ComponentScan(basePackages = {"electronicCommerce"})
public class ElectronicCommerceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ElectronicCommerceApplication.class, args);
    }

}
