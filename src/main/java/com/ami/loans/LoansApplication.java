package com.ami.loans;

import com.ami.loans.dto.LoansContactInfoDTO;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableConfigurationProperties(value = {LoansContactInfoDTO.class})
@OpenAPIDefinition(
        info=@Info(
                title="Loans Microservices API Documentation",
                description = "Bank Loan Services API Documentation",
                version="v1",
                contact = @Contact(
                        name="Ami",
                        email="ami@gmail.com"
                ),
                license = @License(
                        name="Apache 2.0"
                )
        ),
        externalDocs = @ExternalDocumentation(
                description = "Bank Loans Microservices REST API Documentation",
                url = "http://localhost:8080/swagger-ui.html"
        )
)
public class LoansApplication {
    public static void  main(String [] args){
        SpringApplication.run(LoansApplication.class,args);
    }
}
