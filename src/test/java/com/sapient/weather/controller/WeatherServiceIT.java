package com.sapient.weather.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.support.BindingAwareModelMap;

import com.sapient.weather.config.AutoConfiguration;
import com.sapient.weather.model.FormInput;
import com.sapient.weather.util.Constants;

@SpringBootTest(classes = {AutoConfiguration.class, WeatherController.class})
class WeatherServiceIT {


	@Autowired
	WeatherController controller;


	@Test
	public void testThatDefaultCallIsSuccesful() throws Exception {

		FormInput input = preparePositiveInput();
		String response = controller.getWeather(new BindingAwareModelMap(), input);
		assertThat(response).isEqualTo(Constants.WEATHER_SUCCESS_PAGE);
	}

	private FormInput preparePositiveInput() {
		FormInput input = new FormInput();
		input.setApiKey("9703311f41bed24971c9e0f0a1d5c4f9");
		input.setCity("london");
		input.setCnt(24);
		input.setUnits("metric");
		input.setLang("eng");
		return input;
	}

}
