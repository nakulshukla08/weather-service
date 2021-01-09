package com.sapient.weather.model;

import java.util.List;

public class WeatherResponse {

	private List<WeatherDetails> weatherDetailsList;

	public List<WeatherDetails> getWeatherDetailsList() {
		return weatherDetailsList;
	}

	public void setWeatherDetailsList(List<WeatherDetails> weatherDetailsList) {
		this.weatherDetailsList = weatherDetailsList;
	}

}
