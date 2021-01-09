package com.sapient.weather.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.sapient.weather.exception.ServiceException;
import com.sapient.weather.gateway.WeatherGateway;
import com.sapient.weather.model.FormInput;
import com.sapient.weather.model.Model200;
import com.sapient.weather.model.Model200List;
import com.sapient.weather.model.Model200Main;
import com.sapient.weather.model.WeatherResponse;

public class WeatherServiceTest {

	WeatherService sevice;// = new WeatherService(null);

	WeatherGateway gateway;

	FormInput input;

	@BeforeEach
	void setUp() {
		gateway = Mockito.mock(WeatherGateway.class);
		sevice = new WeatherService(gateway);
		input = new FormInput();
	}

	@Test
	public void testServiceForNegativeCase() throws ServiceException {
		WeatherResponse response = sevice.getWeatherDetails(null);
		assertThat(response).isEqualTo(null);
	}

	@Test
	public void testServiceForNegativeCase2() throws ServiceException {

		Mockito.when(gateway.getWeatherDetails(input)).thenReturn(new Model200());
		WeatherResponse response = sevice.getWeatherDetails(null);
		assertThat(response).isEqualTo(null);
	}

	@Test
	public void testServiceForPositiveCase() throws ServiceException {

		Mockito.when(gateway.getWeatherDetails(input)).thenReturn(prepareOutput());
		WeatherResponse response = sevice.getWeatherDetails(null);
		assertThat(response).isEqualTo(null);
	}

	private Model200 prepareOutput() {

		Model200 model200 = new Model200();
		model200.setCod("200");
		List<Model200List> list = new ArrayList<>();
		model200.setList(list);
		Model200List innerListObj = new Model200List();
		list.add(innerListObj);
		innerListObj.setDtTxt("2021-01-10 12:00:00");
		Model200Main main = new Model200Main();
		main.setTemp(new BigDecimal(20));
		innerListObj.setMain(main);
		model200.setList(list);

		return model200;
	}
}
