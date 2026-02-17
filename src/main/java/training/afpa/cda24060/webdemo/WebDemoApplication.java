package training.afpa.cda24060.webdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import training.afpa.cda24060.webdemo.configuration.CustomProperty;

@SpringBootApplication
public class WebDemoApplication {


    public static void main(String[] args) {

        SpringApplication.run(WebDemoApplication.class, args);
    }


}
