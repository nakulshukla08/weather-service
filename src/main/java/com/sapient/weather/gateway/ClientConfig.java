package com.sapient.weather.gateway;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import com.sapient.weather.config.WeatherConfiguration;

public class ClientConfig {

	private WebClient webClient;

	public ClientConfig(WeatherConfiguration config) {
		this.webClient = WebClient.builder().baseUrl(config.getWeatherApiUrl())
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
	}

	public WebClient getWebClient() {
		return webClient;
	}
	
}
