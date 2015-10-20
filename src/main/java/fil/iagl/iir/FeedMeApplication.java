package fil.iagl.iir;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.test.context.web.WebAppConfiguration;

@SpringBootApplication
@WebAppConfiguration
public class FeedMeApplication {

  public static void main(final String[] args) {
    SpringApplication.run(FeedMeApplication.class, args);
  }
}
