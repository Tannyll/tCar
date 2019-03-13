package com.emirci;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan
@SpringBootApplication
@EnableJpaRepositories
@EnableAutoConfiguration
public class App {

    //private static Logger logger = (Logger) LoggerFactory.getLogger(IHeyoApplication.class);
//
    public static void main(String[] args) {

        SpringApplication.run(App.class, args);

    }


}


//
//@Configuration
//@ComponentScan(basePackages="com.emirci")
//@EnableAutoConfiguration
//public class Application {
//
//    //private static Logger logger = LoggerFactory.getLogger(BootApp.class);
//
//
//    public static void main(String[] args) {
//		new SpringApplicationBuilder().sources(Application.class).web(true)
//				.logStartupInfo(true).showBanner(true).run(args);
//	}
//}


