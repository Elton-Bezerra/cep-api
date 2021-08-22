package br.com.boroco.cepapi.adapters.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CacheCleanerJob {
	
	private Logger logger = LoggerFactory.getLogger("jsonLogger");

	@CacheEvict(value = "ceps", allEntries = true)
	@Scheduled(fixedDelay = 10 * 60 * 1000 ,  initialDelay = 60000)
	public void reportCacheEvict() {
	    logger.info("Cleaning Cache");
	}
}
