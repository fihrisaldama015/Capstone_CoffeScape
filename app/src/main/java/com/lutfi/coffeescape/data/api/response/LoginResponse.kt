package com.lutfi.coffeescape.data.api.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(

	@field:SerializedName("data")
	val data: DataLogin,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: String
)

data class DataLogin(

	@field:SerializedName("userId")
	val userId: String,

	@field:SerializedName("token")
	val token: String
)
