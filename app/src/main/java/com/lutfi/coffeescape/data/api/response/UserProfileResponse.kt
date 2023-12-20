package com.lutfi.coffeescape.data.api.response

import com.google.gson.annotations.SerializedName

data class UserProfileResponse(

	@field:SerializedName("data")
	val data: DataUser,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: String
)

data class DataUser(

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("favoriteCoffee")
	val favoriteCoffee: List<String>? = null,

	@field:SerializedName("email")
	val email: String
)
