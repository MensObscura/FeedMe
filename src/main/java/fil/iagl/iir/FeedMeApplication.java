package fil.iagl.iir;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAutoConfiguration
@SpringBootApplication
public class FeedMeApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeedMeApplication.class, args);
    }
}
