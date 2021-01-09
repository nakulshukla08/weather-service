package com.sapient.weather.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.sapient.weather.exception.ServiceException;
import com.sapient.weather.gateway.WeatherGateway;
import com.sapient.weather.model.FormInput;
import com.sapient.weather.model.Model200;
import com.sapient.weather.model.Model200List;
import com.sapient.weather.model.WeatherDetails;
import com.sapient.weather.model.WeatherResponse;

public class WeatherService {

	private WeatherGateway gateway;

	public WeatherService(WeatherGateway gateway) {
		this.gateway = gateway;
	}
	public WeatherResponse getWeatherDetails(FormInput input) throws ServiceException {
		WeatherResponse weatherResponse = null;
		if (input != null) {
			Map<String, WeatherDetails> dateTempratureMap = null;
			try {
				Model200 response = gateway.getWeatherDetails(input);

				List<Model200List> list = response.getList();

				dateTempratureMap = aggregateWeatherDetails(list);
			} catch (ParseException parseException) {
				throw new ServiceException(parseException, parseException.getMessage());
			}

			weatherResponse = new WeatherResponse();

			weatherResponse.setWeatherDetailsList(dateTempratureMap.values().stream().collect(Collectors.toList()));

		}

		return weatherResponse;

	}

	private Map<String, WeatherDetails> aggregateWeatherDetails(List<Model200List> list) throws ParseException {
		Map<String, WeatherDetails> dateTempratureMap = new HashMap<>();
		for (Model200List info : list) {
			String dtTxt = info.getDtTxt();
			String trimDate = trimDate(dtTxt);
			WeatherDetails weatherDetails = dateTempratureMap.get(trimDate);
			if (weatherDetails != null) {
				updateHighTemp(info, weatherDetails);
				updateLowTemp(info, weatherDetails);
				updateRainWarning(info, weatherDetails);
			} else {
				BigDecimal temp = info.getMain().getTemp();
				weatherDetails = new WeatherDetails(temp, temp, trimDate);
				dateTempratureMap.put(trimDate, weatherDetails);
			}
		}
		return dateTempratureMap;
	}

	private void updateRainWarning(Model200List info, WeatherDetails weatherDetails) {

		if (!weatherDetails.isRainWarning()
				&& info.getWeather().stream().filter(weather -> weather.getMain().contains("Rain")).count() >= 1) {

			String rainWarning = weatherDetails.getWarning() != null ? weatherDetails.getWarning() + "|Carry Umbrella|"
					: "|Carry Umbrella|";
			weatherDetails.setWarning(rainWarning);
			weatherDetails.setRainWarning(true);
		}

	}

	private void updateHighTemp(Model200List info, WeatherDetails weatherDetails) {
		BigDecimal currentHigh = weatherDetails.getHighTemp();

		BigDecimal tempHigh = info.getMain().getTemp();

		if (tempHigh != null && currentHigh != null) {
			if (tempHigh.compareTo(currentHigh) == 1) {
				weatherDetails.setHighTemp(tempHigh);
			}
		}
	}

	private void updateLowTemp(Model200List info, WeatherDetails weatherDetails) {
		BigDecimal currentLow = weatherDetails.getLowTemp();

		BigDecimal tempLow = info.getMain().getTemp();

		if (tempLow != null && currentLow != null) {
			if (currentLow.compareTo(tempLow) == 1) {
				weatherDetails.setLowTemp(tempLow);
			}
		}
	}

	private static String trimDate(String date) throws ParseException {
		java.util.Date temp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);

		SimpleDateFormat mdyFormat = new SimpleDateFormat("yyyy-MM-dd");

		return mdyFormat.format(temp);
	}

}
