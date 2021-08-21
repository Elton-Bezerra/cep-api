package br.com.boroco.cepapi.adapters.configuration;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.Executor;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.RestTemplate;

import br.com.boroco.cepapi.CEPApiApplication;
import br.com.boroco.cepapi.adapters.outbound.http.ViaCEPServiceImpl;
import br.com.boroco.cepapi.adapters.outbound.persistence.CEPRepositoryImpl;
import br.com.boroco.cepapi.core.services.CepServiceImpl;

@Configuration
@ComponentScan(basePackageClasses = CEPApiApplication.class)
public class BeanConfiguration {

    @Bean
    CepServiceImpl cepServiceImpl(CEPRepositoryImpl repository, ViaCEPServiceImpl remoteCepService) {
        return new CepServiceImpl(repository, remoteCepService);
    }
    
    @Bean(name="restclient")
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
    	return builder
    			.setConnectTimeout(Duration.of(200, ChronoUnit.MILLIS))
    			.setReadTimeout(Duration.of(500, ChronoUnit.MILLIS))
    			.build();
    }
    
    @Bean(name = "threadPoolTaskExecutor")
    public Executor threadPoolTaskExecutor() {
        return new ThreadPoolTaskExecutor();
    }
    
}

