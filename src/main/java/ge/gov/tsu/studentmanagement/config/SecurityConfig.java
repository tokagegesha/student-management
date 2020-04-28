package ge.gov.tsu.studentmanagement.config;

import ge.gov.tsu.studentmanagement.repository.security.UserRepository;
import ge.gov.tsu.studentmanagement.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@ComponentScan("ge.gov.tsu")
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    UserRepository userRepository;


    @Autowired
    MyUserDetailsService myUserDetailsService;

    @Autowired
    RestAuthenticationEntryPoint restAuthenticationEntryPoint;


    @Autowired
    private RESTAuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private RESTAuthenticationSuccessHandler authenticationSuccessHandler;


    @Bean
    public MyUserDetailsService myUserDetailsService() {
        return new MyUserDetailsService();
    }

    // @formatter:off
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/assets/**", "/index").permitAll()
                .antMatchers("/unsecured/**").permitAll()
                .antMatchers("/user/**").authenticated()
                .antMatchers("/api/**").authenticated()
                .and()
                .formLogin().loginProcessingUrl("/api/j_spring_security_check").permitAll()
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler)
                .usernameParameter("username")
                .passwordParameter("password").permitAll()
                .and().exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint).and()
                .logout()
                .permitAll()
                .logoutRequestMatcher(new AntPathRequestMatcher("/api/logout"));

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServiceBean());
    }


    @Override
    public UserDetailsService userDetailsServiceBean() throws Exception {
        return myUserDetailsService;
    }


}