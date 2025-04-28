/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.global.colas.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.UserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


/**
 *

 */

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Autowired
    DataSource dataSource;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
//        auth.inMemoryAuthentication().withUser("oscar.vides.test").password("12345").roles("USERS");
        System.out.println("===================YYYYYYYYYYYYYYYYYYYYY==========>Asegurando");
        auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(passwordEncoder())
                .usersByUsernameQuery("select usuario username, contrasena password, 1 enabled,2 polinable from gc_seguridad_usuario where usuario=?")
                .authoritiesByUsernameQuery("SELECT u.usuario username, " +
                                            "  r.NOMBRE role " +
                                            " FROM GC_SEGURIDAD_ROLE r, " +
                                            "  GC_SEGURIDAD_USUARIO_ROLE ur, " +
                                            "  GC_SEGURIDAD_USUARIO u " +
                                            " WHERE r.ID_ROLE  =ur.ID_ROLE " +
                                            " AND ur.ID_USUARIO=u.ID_USUARIO " +
                                            " AND u.usuario    =?");

        
    }
    
    
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        FormLoginConfigurer formLogin = new FormLoginConfigurer();
//            http.apply(formLogin).loginPage("/login")
//            .defaultSuccessUrl("/")
//            .failureUrl("/login?error")			
//            .permitAll();
//			
//            http.csrf().disable()
//            .logout().logoutSuccessUrl("/login") 
//            .and()
//            .headers()
//            .frameOptions()
//            .disable().headers()
//            .addHeaderWriter(new XFrameOptionsHeaderWriter(XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN))
//            .and()
//            .authorizeRequests()
//            .antMatchers("/css/**","/js/**","/images/**","/fonts/**","/html/**")
//            .permitAll()
//            .anyRequest()
//            .fullyAuthenticated();
            
            http.formLogin().loginPage("/login")
                .defaultSuccessUrl("/")
                .failureUrl("/login?error")
                .permitAll()
                .and()
                .csrf().disable()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .and()
                .authorizeRequests()
                .antMatchers("/css/**","/js/**","/images/**","/fonts/**","/html/**","/rcita/**","/me/validarID/**")
                .permitAll()
                .anyRequest()
                .fullyAuthenticated();
            
                    
    }
    
    
    @Bean
    public PasswordEncoder passwordEncoder(){
            PasswordEncoder encoder = new BCryptPasswordEncoder();
            String s=encoder.encode("12345");
            System.out.println("=======================>"+s);
            if (encoder.matches("12345", s))
                System.out.println("EXITO");
            else
                System.out.println("FALLA");
            return encoder;
    }
    
}
