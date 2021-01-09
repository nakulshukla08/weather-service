package com.sapient.weather.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties
public class WeatherConfiguration {
	
	private String weatherApiUrl;

	public String getWeatherApiUrl() {
		return weatherApiUrl;
	}

	public void setWeatherApiUrl(String weatherApiUrl) {
		this.weatherApiUrl = weatherApiUrl;
	}
	

}
