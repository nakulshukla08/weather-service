package com.sapient.weather.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class WeatherDetails {

	public WeatherDetails(BigDecimal highTemp, BigDecimal lowTemp, String date) {
		this.highTemp = highTemp;
		this.lowTemp = lowTemp;
		this.date = LocalDate.parse(date);
	}

	private LocalDate date;

	private BigDecimal highTemp;

	private BigDecimal lowTemp;

	private String warning;

	private boolean heatwarning;

	private boolean rainWarning;

	public boolean isRainWarning() {
		return rainWarning;
	}

	public void setRainWarning(boolean rainWarning) {
		this.rainWarning = rainWarning;
	}

	private String weatherMain;

	public String getWeatherMain() {
		return weatherMain;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public void setWeatherMain(String weatherMain) {
		this.weatherMain = weatherMain;
	}

	public BigDecimal getHighTemp() {
		return highTemp;
	}

	public void setHighTemp(BigDecimal highTemp) {
		if (highTemp != null) {
			this.highTemp = highTemp;

			if (!heatwarning && highTemp.compareTo(new BigDecimal(40)) == 1) {
				String heatWarning = this.warning != null ? this.warning + "|Carry Sunscreen|" : "|Carry Sunscreen|";
				this.warning = heatWarning;
				heatwarning = true;
			}
		}

	}

	public BigDecimal getLowTemp() {
		return lowTemp;
	}

	public void setLowTemp(BigDecimal lowTemp) {
		this.lowTemp = lowTemp;
	}

	public String getWarning() {
		return warning;
	}

	public void setWarning(String warning) {
		this.warning = warning;
	}

}
