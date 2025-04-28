/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.global.colas.config;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.thymeleaf.extras.springsecurity3.dialect.SpringSecurityDialect;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import sv.global.colas.components.AppInfoInterceptor;

/**
 *
 * @author Oscar Vides
 */
@Configuration
@EnableWebMvc
@EnableSpringDataWebSupport
@ComponentScan(basePackages = "sv.global.colas",
        useDefaultFilters = false,
        includeFilters = @ComponentScan.Filter(pattern = ".*.controllers.*", type = FilterType.REGEX))
@PropertySource(value = "classpath:application.properties", ignoreResourceNotFound = false)
// no es necesario ya que sprint lo agrega por defecto A.N 09/01/2024 
public class WebConfig extends WebMvcConfigurerAdapter {

    @Autowired
    Environment env;

    @Autowired
    private AppInfoInterceptor appInfoInterceptor;

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new StringHttpMessageConverter());
        converters.add(new GsonHttpMessageConverter());
        super.configureMessageConverters(converters);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");
        registry.addInterceptor(localeChangeInterceptor);
//		registry.addInterceptor(trazabilidadInterceptor);
        registry.addInterceptor(appInfoInterceptor);
    }

    @Bean
    public String appName() {
        return env.getProperty("app.fullName");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/js/**").addResourceLocations(new String[]{"classpath:/static/js/"});
        registry.addResourceHandler("/css/**").addResourceLocations(new String[]{"classpath:/static/css/"});
        registry.addResourceHandler("/images/**").addResourceLocations(new String[]{"classpath:/static/images/"});
        registry.addResourceHandler("/fonts/**").addResourceLocations(new String[]{"classpath:/static/fonts/"});
        registry.addResourceHandler("/tmp/**").addResourceLocations(new String[]{"classpath:/static/tmp/"});
        registry.addResourceHandler("/sonido/**").addResourceLocations(new String[]{"classpath:/static/sonido/"});
        registry.addResourceHandler("/contenido/**").addResourceLocations(new String[]{"classpath:/static/contenido/"});

        // Sirve archivos desde la nueva ruta externa en Linux
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:/var/uploads/");
    }

    @Bean
    public LocaleResolver localeResolver() {

        CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
        cookieLocaleResolver.setDefaultLocale(StringUtils
                .parseLocaleString("es"));
        return cookieLocaleResolver;
    }

    @Bean
    public ViewResolver viewResolver() {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setTemplateMode("HTML5");
        templateResolver.setPrefix("templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setCharacterEncoding("UTF-8");
        SpringTemplateEngine engine = new SpringTemplateEngine();
        engine.setTemplateResolver(templateResolver);
        engine.addDialect(new SpringSecurityDialect());
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(engine);
        return viewResolver;
    }

    @Bean
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setDefaultEncoding("utf-8");
        //52428800
        resolver.setMaxUploadSize(1610612736);
        //2097152
        resolver.setMaxInMemorySize(20971520);
        return resolver;
    }

}
