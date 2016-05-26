package hello;

import de.codecentric.boot.admin.config.EnableAdminServer;
import org.jsondoc.spring.boot.starter.EnableJSONDoc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by b.bassac on 24/05/2016.
 */
@SpringBootApplication
@EnableJpaRepositories
@EnableJSONDoc
@EnableAdminServer
public class Start {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Start.class, args);
    }

}