package ge.gov.tsu.studentmanagement.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableCaching
@EnableAspectJAutoProxy
public class AppConfig {/*
    @Bean
    public MyUserDetailsService myUserDetailsService() {
        return new MyUserDetailsService();
    }*/

  /*  @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }*/
}
