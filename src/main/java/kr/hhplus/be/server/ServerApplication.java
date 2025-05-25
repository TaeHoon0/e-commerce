package kr.hhplus.be.server;

import kr.hhplus.be.server.point.infrastructure.lock.PointLockManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "kr.hhplus.be.server")
@EnableJpaRepositories(basePackages = "kr.hhplus.be.server")
@EnableConfigurationProperties({
		PointLockManager.class
})
public class ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}

}
