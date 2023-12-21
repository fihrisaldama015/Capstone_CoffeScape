package com.lutfi.coffeescape.data.api.response

import com.google.gson.annotations.SerializedName

data class AddRatingResponse(

	@field:SerializedName("data")
	val data: DataAddRating? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class DataAddRating(

	@field:SerializedName("rating")
	val rating: String? = null,

	@field:SerializedName("comment")
	val comment: String? = null,

	@field:SerializedName("id")
	val id: String? = null
)
