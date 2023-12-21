package com.lutfi.coffeescape.data.api.response

import com.google.gson.annotations.SerializedName

data class CoffeeResponse(

	@field:SerializedName("data")
	val data: List<DataCoffee>,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class DataCoffee(

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("thumbnail")
	val imageUrl: String? = null,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("FlavorProfiles")
	val flavorProfiles: String? = null,

	@field:SerializedName("RoastLevel")
	val roastLevel: String? = null,

	@field:SerializedName("ServingStyle")
	val servingStyle: String? = null,

	@field:SerializedName("RecommendedBeans")
	val recommendedBeans: String? = null,

	@field:SerializedName("rating")
	val rating: Double? = null,

	@field:SerializedName("moodType")
	val moodType: String? = null,

	@field:SerializedName("BrewingMethod")
	val brewingMethod: String? = null
)
