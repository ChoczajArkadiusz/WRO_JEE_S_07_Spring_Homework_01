package pl.coderslab.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import pl.coderslab.beans.CustomerLogger;
import pl.coderslab.beans.FileCustomerLogger;

@Configuration
@ComponentScan("pl.coderslab.beans")
public class AppConfig {

    @Bean
    public CustomerLogger customerLogger() {
        return new FileCustomerLogger("fileLog.txt");
    }


}
