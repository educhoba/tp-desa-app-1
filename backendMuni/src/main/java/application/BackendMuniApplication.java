package application;

import application.controllers.Controlador;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@SpringBootConfiguration
public class BackendMuniApplication {


    Controlador negocio;
    public static void main(String[] args) {
        SpringApplication.run(BackendMuniApplication.class, args);
    }

}
