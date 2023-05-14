package com.example.pstravel.Controllter;

import com.example.pstravel.Dto.LocationDto;
import com.example.pstravel.service.LocationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/map")
public class LocationController {

    @Autowired
    LocationService locationService;
    /**
     * @description 유저들의 위치 정보를 redis에 저장하는 API
     */
    @PostMapping("/location")
    public void saveLocation(@RequestBody LocationDto locationDto) throws IllegalAccessException {
        locationService.saveLocation(locationDto);
    }
    /**
     * @description 내 주변 유저들의 위치 정보를 보내주는 api
     */
    @GetMapping("/getSurround")
    public List<?> getSurroundLocation(@RequestBody LocationDto locationDto) throws IllegalAccessException {
        locationService.saveLocation(locationDto);

        return null;
    }
}
