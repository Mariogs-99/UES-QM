/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.global.colas.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 *
 * @author Oscar Vides
 */
@Configuration
@EnableJpaRepositories(basePackages = {"sv.global"},
        includeFilters =
        @ComponentScan.Filter(pattern = ".*.repositories.*", type = FilterType.REGEX))
@ComponentScan(basePackages = "sv.global",
        useDefaultFilters = false,
        includeFilters =
        @ComponentScan.Filter(pattern = ".*.components.*", type = FilterType.REGEX))
public class RepositoryConfig {
    
}
