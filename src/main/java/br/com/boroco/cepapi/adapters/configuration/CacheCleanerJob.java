package br.com.boroco.cepapi.adapters.configuration;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CacheCleanerJob {

	@CacheEvict(value = "ceps", allEntries = true)
	@Scheduled(fixedDelay = 10 * 60 * 1000 ,  initialDelay = 60000)
	public void reportCacheEvict() {
	    System.out.println("Cleaning Cache");
	}
}
