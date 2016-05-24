package hello;

import org.jsondoc.spring.boot.starter.EnableJSONDoc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by b.bassac on 24/05/2016.
 */
@SpringBootApplication
@EnableJpaRepositories
@EnableJSONDoc
public class Start {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Start.class, args);
    }

}