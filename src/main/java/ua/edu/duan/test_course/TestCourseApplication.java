package ua.edu.duan.test_course;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.retry.annotation.EnableRetry;

@EnableJpaRepositories
@EnableRetry
@SpringBootApplication
public class TestCourseApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestCourseApplication.class, args);
	}

}
