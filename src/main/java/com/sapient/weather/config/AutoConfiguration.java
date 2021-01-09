package com.sapient.weather.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

import com.sapient.weather.api.CurrentWeatherDataApi;
import com.sapient.weather.gateway.ClientConfig;
import com.sapient.weather.gateway.WeatherGateway;
import com.sapient.weather.service.WeatherService;

@Configuration
public class AutoConfiguration {

	@Bean
	public ClientConfig clientConfig(WeatherConfiguration configuration) {
		return new ClientConfig(configuration);
	}

	@Bean
	public WeatherConfiguration weatherConfiguration() {
		return new WeatherConfiguration();
	}

	@Bean
	public WeatherService weatherService(WeatherGateway weatherGateway) {
		return new WeatherService(weatherGateway);
	}

	@Bean
	public WeatherGateway weatherGateway(CurrentWeatherDataApi api) {
		return new WeatherGateway(api);
	}

	@Bean
	public CurrentWeatherDataApi currentWeatherDataApi() {

		return new CurrentWeatherDataApi();
	}

}
