package io.ehsan.snappdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value = {
		"classpath:application.properties",
		"classpath:db.properties"
})
public class SnappDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SnappDemoApplication.class, args);
	}

}
