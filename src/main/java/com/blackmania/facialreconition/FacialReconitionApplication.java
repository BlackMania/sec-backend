package com.blackmania.facialreconition;

import com.blackmania.facialreconition.rest.fileHandlers.FilePropperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
        FilePropperties.class
})
public class FacialReconitionApplication {
    public static void main(String[] args) {
        SpringApplication.run(FacialReconitionApplication.class, args);
        
    }
}
