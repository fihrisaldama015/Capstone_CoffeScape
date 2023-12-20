package com.lutfi.coffeescape.data.api.retrofit

import com.lutfi.coffeescape.data.api.response.CoffeeResponse
import com.lutfi.coffeescape.data.api.response.DataCoffee
import com.lutfi.coffeescape.data.api.response.DeleteFavoriteRequest
import com.lutfi.coffeescape.data.api.response.DetailCoffeeResponse
import com.lutfi.coffeescape.data.api.response.LoginResponse
import com.lutfi.coffeescape.data.api.response.RegisterResponse
import com.lutfi.coffeescape.data.api.response.UserProfileResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.HTTP
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @FormUrlEncoded
    @POST("register")
    suspend fun register(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): RegisterResponse

    @FormUrlEncoded
    @POST("login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ) : LoginResponse

    @GET("users/{id}")
    suspend fun getUserProfile(
        @Path("id") id: String
    ): UserProfileResponse

    @GET("users/{id}/favorite")
    suspend fun getFavoriteCoffee(
        @Path("id") id: String
    ): CoffeeResponse

    @FormUrlEncoded
    @POST("users/{id}/favorite")
    suspend fun addFavoriteCoffee(
        @Path("id") id: String,
        @Field("coffeeId") coffeeId: String
    ): UserProfileResponse

    @HTTP(method = "DELETE", path = "users/{id}/favorite", hasBody = true)
    suspend fun deleteFavoriteCoffee(
        @Path("id") id: String,
        @Body requestBody: DeleteFavoriteRequest
    ): UserProfileResponse

    @GET("arabica/{id}")
    suspend fun getCoffeeById(
        @Path("id") id: String
    ): DetailCoffeeResponse
}