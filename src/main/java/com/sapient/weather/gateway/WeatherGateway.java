package com.sapient.weather.gateway;

import com.sapient.weather.api.CurrentWeatherDataApi;
import com.sapient.weather.model.FormInput;
import com.sapient.weather.model.Model200;

public class WeatherGateway {

	CurrentWeatherDataApi api;

	public WeatherGateway(CurrentWeatherDataApi api) {
		this.api = api;

	}

	public Model200 getWeatherDetails(FormInput input) {

		return api.currentWeatherData(input.getCity(), input.getApiKey(), null, Integer.toString(input.getCnt()), input.getUnits(),
				input.getLang());
	}

}
