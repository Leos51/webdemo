package training.afpa.cda24060.webdemo.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "training.afpa.cda24060.webdemo")
@Data
public class CustomProperty {
    private String apiURL;
}
