package br.com.boroco.cepapi.adapters.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import br.com.boroco.cepapi.CEPApiApplication;
import br.com.boroco.cepapi.core.services.CepServiceImpl;

@Configuration
@ComponentScan(basePackageClasses = CEPApiApplication.class)
public class BeanConfiguration {

    @Bean
    CepServiceImpl cepServiceImpl() {
        return new CepServiceImpl();
    }
}
