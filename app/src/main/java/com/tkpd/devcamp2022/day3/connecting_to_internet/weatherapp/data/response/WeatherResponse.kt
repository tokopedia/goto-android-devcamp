package com.tkpd.devcamp2022.day3.connecting_to_internet.weatherapp.data.response

import com.google.gson.annotations.SerializedName

data class WeatherResponse(

	@SerializedName("visibility")
	val visibility: Int = 0,

	@SerializedName("timezone")
	val timezone: Int = 0,

	@SerializedName("main")
	val main: Main = Main(),

	@SerializedName("clouds")
	val clouds: Clouds = Clouds(),

	@SerializedName("sys")
	val sys: Sys = Sys(),

	@SerializedName("dt")
	val dt: Int = 0,

	@SerializedName("coord")
	val coord: Coord = Coord(),

	@SerializedName("weather")
	val weather: List<WeatherItem> = emptyList(),

	@SerializedName("name")
	val name: String = "",

	@SerializedName("cod")
	val cod: Int = 0,

	@SerializedName("id")
	val id: Int = 0,

	@SerializedName("base")
	val base: String = "",

	@SerializedName("wind")
	val wind: Wind = Wind()
) {
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

	data class Wind(

		@SerializedName("deg")
		val deg: Int = 0,

		@SerializedName("speed")
		val speed: Double = 0.0,

		@SerializedName("gust")
		val gust: Double = 0.0
	)

	data class Coord(

		@SerializedName("lon")
		val lon: Double = 0.0,

		@SerializedName("lat")
		val lat: Double = 0.0
	)

	data class Sys(

		@SerializedName("country")
		val country: String = "",

		@SerializedName("sunrise")
		val sunrise: Int = 0,

		@SerializedName("sunset")
		val sunset: Int = 0,

		@SerializedName("id")
		val id: Int = 0,

		@SerializedName("type")
		val type: Int = 0
	)

	data class Clouds(

		@SerializedName("all")
		val all: Int = 0
	)

	data class Main(

		@SerializedName("temp")
		val temp: Double = 0.0,

		@SerializedName("temp_min")
		val tempMin: Double = 0.0,

		@SerializedName("grnd_level")
		val grndLevel: Int = 0,

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
}
