package com.uit.se.gogo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class GogoApplication {

	public static void main(String[] args) {
		// Map<String, Object> env = Dotenv.load()
		// 		.entries()
		// 		.stream()
		// 		.collect(
		// 				Collectors.toMap(DotenvEntry::getKey, DotenvEntry::getValue));
		// new SpringApplicationBuilder(GogoApplication.class)
		// 		.environment(new StandardEnvironment() {
		// 			@Override
		// 			protected void customizePropertySources(MutablePropertySources propertySources) {
		// 				super.customizePropertySources(propertySources);
		// 				propertySources.addLast(new MapPropertySource("dotenvProperties", env));
		// 			}
		// 		}).run(args);
		SpringApplication.run(GogoApplication.class, args);
	}
}
