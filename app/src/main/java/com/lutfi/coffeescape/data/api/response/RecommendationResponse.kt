package com.lutfi.coffeescape.data.api.response

import com.google.gson.annotations.SerializedName

data class RecommendationResponse(

	@field:SerializedName("data")
	val data: DataRecommend,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class DataRecommend(

	@field:SerializedName("prediction")
	val prediction: List<Int?>
)
