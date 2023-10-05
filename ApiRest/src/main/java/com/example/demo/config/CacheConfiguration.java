package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.redisson.config.Config;
import org.redisson.jcache.configuration.RedissonConfiguration;
import io.github.bucket4j.distributed.proxy.ProxyManager;
import io.github.bucket4j.grid.jcache.JCacheProxyManager;

import javax.cache.CacheManager;
import javax.cache.Caching;

@Configuration
public class CacheConfiguration {
     /*
    @Value("${spring.data.redis.host}")
    private String redisHost;

    @Value("${spring.data.redis.port}")
    private int redisPort;

    @Bean
    public Config config() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://" + redisHost + ":" + redisPort);
        return config;
    }

    @Bean
    public CacheManager cacheManager(Config config) {
        CacheManager manager = Caching.getCachingProvider().getCacheManager();
        manager.createCache("cache", RedissonConfiguration.fromConfig(config));
        return manager;
    }

    @Bean
    ProxyManager<String> proxyManager(CacheManager cacheManager) {
        return new JCacheProxyManager<>(cacheManager.getCache("cache"));
    }



     */

}