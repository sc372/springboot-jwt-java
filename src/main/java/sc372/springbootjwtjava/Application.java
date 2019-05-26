package sc372.springbootjwtjava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// import org.springframework.boot.SpringApplication;
// import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
// import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.boot.builder.SpringApplicationBuilder;
// import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
// import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

// @EnableJpaAuditing
@SpringBootApplication
// @EnableAutoConfiguration
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
