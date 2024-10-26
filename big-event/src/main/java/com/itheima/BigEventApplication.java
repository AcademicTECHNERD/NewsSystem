package com.itheima;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @SpringBootApplication 是以下三个注解的结合
 * @Configuration: 表示该类是一个配置类，能够定义 Spring 的 bean 配置。
 * 它的作用类似于传统 Spring 中的 XML 配置文件。
 *
 * @EnableAutoConfiguration: 启用 Spring Boot 的自动配置机制。
 * Spring Boot 会根据项目的依赖和配置，自动配置应用所需的各种组件。例如，如果项目中有 spring-web 依赖，Spring Boot 会自动配置嵌入式的 Tomcat 服务器。
 *
 * @ComponentScan: 指定 Spring 应用上下文的扫描路径，
 * Spring Boot 会自动扫描该包及其子包下的组件（如 @Controller、@Service 等）。
 * 默认情况下，它会从注解所在的类的包开始扫描。
 */

@SpringBootApplication
public class BigEventApplication {
    public static void main(String[] args) {
        SpringApplication.run(BigEventApplication.class,args);
    }
}
