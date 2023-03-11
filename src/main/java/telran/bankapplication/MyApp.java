package telran.bankapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import java.util.Arrays;

@ConfigurationPropertiesScan
@SpringBootApplication
public class MyApp {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(MyApp.class);
        ConfigurableApplicationContext ctx = app.run(args);
        Environment env = ctx.getEnvironment();
        System.out.println("Loaded properties: " + Arrays.toString(env.getActiveProfiles()));
    }
}