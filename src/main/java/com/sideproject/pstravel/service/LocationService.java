package com.sideproject.pstravel.service;

import com.sideproject.pstravel.Dto.LocationDto;
import com.sideproject.pstravel.Repository.REDIS.RedisConnect;
import io.lettuce.core.api.async.RedisAsyncCommands;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.HashMap;

@Service
public class LocationService {

    public void saveLocation(LocationDto locationDto) throws IllegalAccessException {

        HashMap<String, String> hashMap = new HashMap<>();
        Class<?> clazz = locationDto.getClass();

        // 모든 필드를 순회하며 HashMap에 저장
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            String fieldName = field.getName();
            String fieldValue = (String) field.get(locationDto);
            hashMap.put(fieldName, fieldValue);
        }

        RedisAsyncCommands<String, String> redisAsyncCommands = RedisConnect.getRedisAsyncCommands();
        redisAsyncCommands.hset(locationDto.getUserId(), hashMap);
    }
}
