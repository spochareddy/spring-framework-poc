package com.neom.framework;

import com.neom.framework.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.stream.LongStream;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@EnableSwagger2
public class NeomApplication {

	public static void main(String[] args) {

		SpringApplication.run(NeomApplication.class, args);
	}

	/*@Bean
	CommandLineRunner init(UserRepository repository) {
		return args -> {
			repository.deleteAll();
			LongStream.range(1, 11).mapToObj(
							i -> new com.neom.framework.entity.UserInfo(i, "user" + i, "user" + i + ")"))
					.map(v -> repository.save(v)).forEach(System.out::println);
		};
	}*/

}
