package com.example.restmvc.restmvcdemo;

import com.example.restmvc.restmvcdemo.model.Note;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.context.ConfigurableWebApplicationContext;
import org.springframework.web.context.WebApplicationContext;

@SpringBootApplication
public class RestmvcdemoApplication {

    public static void main(String[] args) {

        SpringApplication.run(RestmvcdemoApplication.class, args);

//        System.out.println(SpringApplication.DEFAULT_CONTEXT_CLASS);
//        System.out.println(SpringApplication.DEFAULT_WEB_CONTEXT_CLASS);
//
//        ConfigurableApplicationContext cwc = new SpringApplicationBuilder(RestmvcdemoApplication.class).context();
//
//        Note note1 = (Note) cwc.getBean("note");
//        Note note2 = (Note) cwc.getBean("note");
//
//        System.out.println("n1:n2 =" + note1.hashCode() + " " + note2.hashCode());

    }

    /*
    This will ensure that custom configurations which extend the spring auto configuration
    are picked up
     */
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//        return application.sources(RestmvcdemoApplication.class);
//    }
}
