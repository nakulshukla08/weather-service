package com.sapient.weather.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.sapient.weather.exception.ServiceException;
import com.sapient.weather.model.FormInput;
import com.sapient.weather.model.WeatherResponse;
import com.sapient.weather.service.WeatherService;
import com.sapient.weather.util.Constants;

@RestController
public class WeatherController {

	private static Logger logger = LoggerFactory.getLogger(WeatherController.class);

	private WeatherService service;

	public WeatherController(WeatherService service) {
		this.service = service;
	}

	@RequestMapping(value = "/weather", method = RequestMethod.GET)
	public String CityForm(Model model) {

		model.addAttribute("city", new FormInput());
		return "form";
	}

	@RequestMapping(value = "/weather", method = RequestMethod.POST)
	public String getWeather(Model model, @ModelAttribute FormInput input)
			throws JsonParseException, JsonMappingException, IOException {
		try {
			WeatherResponse weather = service.getWeatherDetails(input);
			model.addAttribute("weatherData", weather.getWeatherDetailsList());
		} catch (ServiceException | RuntimeException e) {
			logger.error("Exception caught in controller while invoking service layer");
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
		}

		return Constants.WEATHER_SUCCESS_PAGE;
	}

	@RequestMapping(value = "/weather/v2", method = RequestMethod.POST)
	public WeatherResponse getWeatherV2(Model model, @ModelAttribute FormInput input)
			throws JsonParseException, JsonMappingException, IOException {
		WeatherResponse weather = null;
		try {
			weather = service.getWeatherDetails(input);
			model.addAttribute("weatherData", weather.getWeatherDetailsList());
		} catch (ServiceException | RuntimeException e) {
			logger.error("Exception caught in controller while invoking service layer");
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
		}

		return weather;
	}

}
