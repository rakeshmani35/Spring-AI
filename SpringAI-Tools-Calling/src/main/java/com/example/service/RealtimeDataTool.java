package com.example.service;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/*
use
https://app.swaggerhub.com/apis-docs/WeatherAPI.com/WeatherAPI/1.0.2#/APIs/time-zone
 */

@Component
public class RealtimeDataTool {

    @Autowired
    private RestTemplate restTemplate;

    @Tool(description = "Get current weather for a given city")
    public String getWhetherInformation(@ToolParam(description = "City name like Bangalore , London") String city) {
        System.out.println("TOOL Calling : -- Fetching weather information for city: " + city);
        return restTemplate
                .getForObject("https://api.weatherapi.com/v1/current.json?q=" + city + "&key=cbadb6cabe224ddd864182546261803", String.class);
    }

    @Tool(description = "Get current time, local time, timezone or clock details for any city")
    public String getTimezoneInformation(@ToolParam(description = "City name like Bangalore , London") String city) {
        System.out.println("TOOL Calling : -- Fetching timezone information for city: " + city);
        return restTemplate
                .getForObject("https://api.weatherapi.com/v1/timezone.json?q=" + city + "&key=cbadb6cabe224ddd864182546261803", String.class);
    }
}
