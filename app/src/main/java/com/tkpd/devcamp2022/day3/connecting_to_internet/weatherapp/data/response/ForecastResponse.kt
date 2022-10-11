package com.tkpd.devcamp2022.day3.connecting_to_internet.weatherapp.data.response

import com.google.gson.annotations.SerializedName

data class ForecastResponse(

	@SerializedName("city")
	val city: City = City(),

	@SerializedName("cnt")
	val cnt: Int = 0,

	@SerializedName("cod")
	val cod: String = "",

	@SerializedName("message")
	val message: Int = 0,

	@SerializedName("list")
	val list: List<ListItem> = emptyList()
) {
	data class City(

		@SerializedName("country")
		val country: String = "",

		@SerializedName("coord")
		val coord: Coord = Coord(),

		@SerializedName("sunrise")
		val sunrise: Int = 0,

		@SerializedName("timezone")
		val timezone: Int = 0,

		@SerializedName("sunset")
		val sunset: Int = 0,

		@SerializedName("name")
		val name: String = "",

		@SerializedName("id")
		val id: Int = 0,

		@SerializedName("population")
		val population: Int = 0
	) {
		data class Coord(

			@SerializedName("lon")
			val lon: Double = 0.0,

			@SerializedName("lat")
			val lat: Double = 0.0
		)
	}

	data class ListItem(

		@SerializedName("dt")
		val dt: Int = 0,

		@SerializedName("pop")
		val pop: Double = 0.0,

		@SerializedName("rain")
		val rain: Rain? = null,

		@SerializedName("visibility")
		val visibility: Int = 0,

		@SerializedName("dt_txt")
		val dtTxt: String = "",

		@SerializedName("weather")
		val weather: List<WeatherItem> = emptyList(),

		@SerializedName("main")
		val main: Main = Main(),

		@SerializedName("clouds")
		val clouds: Clouds = Clouds(),

		@SerializedName("sys")
		val sys: Sys = Sys(),

		@SerializedName("wind")
		val wind: Wind = Wind()
	) {
		data class Main(

			@SerializedName("temp")
			val temp: Double = 0.0,

			@SerializedName("temp_min")
			val tempMin: Double = 0.0,

			@SerializedName("grnd_level")
			val grndLevel: Int = 0,

			@SerializedName("temp_kf")
			val tempKf: Double = 0.0,

			@SerializedName("humidity")
			val humidity: Int = 0,

			@SerializedName("pressure")
			val pressure: Int = 0,

			@SerializedName("sea_level")
			val seaLevel: Int = 0,

			@SerializedName("feels_like")
			val feelsLike: Double = 0.0,

			@SerializedName("temp_max")
			val tempMax: Double = 0.0
		)

		data class Clouds(

			@SerializedName("all")
			val all: Int = 0
		)

		data class Sys(

			@SerializedName("pod")
			val pod: String = ""
		)

		data class Wind(

			@SerializedName("deg")
			val deg: Int = 0,

			@SerializedName("speed")
			val speed: Double = 0.0,

			@SerializedName("gust")
			val gust: Double = 0.0
		)

		data class WeatherItem(

			@SerializedName("icon")
			val icon: String = "",

			@SerializedName("description")
			val description: String = "",

			@SerializedName("main")
			val main: String = "",

			@SerializedName("id")
			val id: Int = 0
		)

		data class Rain(

			@SerializedName("3h")
			val jsonMember3h: Double = 0.0
		)
	}
}
