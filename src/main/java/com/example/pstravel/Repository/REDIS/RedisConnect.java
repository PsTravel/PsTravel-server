package com.example.pstravel.Repository.REDIS;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisFuture;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.async.RedisAsyncCommands;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration
public class RedisConnect {

    private static final String REDIS_HOST = "localhost";
    private static final int REDIS_PORT = 6379;

    private static RedisClient redisClient;
    private static StatefulRedisConnection<String, String> connection;

    public static synchronized RedisAsyncCommands<String, String> getRedisAsyncCommands() {
        if (redisClient == null) {
            redisClient = RedisClient.create("redis://" + REDIS_HOST + ":" + REDIS_PORT);
        }
        if (connection == null || !connection.isOpen()) {
            connection = redisClient.connect();
        }
        return connection.async();
    }

    private RedisFuture<String> getLocation(String key) throws Exception {
        try {
            RedisFuture<String> stringRedisFuture = connection.async().get(key);
            return stringRedisFuture;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    private void setLocation(String key, String location) throws Exception {
        try {
            connection.async().set(key, location);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    private void hsetLocation(String key, HashMap<String, String> map) throws Exception {
        try {
            connection.async().hset(key, map);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }


}
