package cloud.jakemitchell.galacticmerchants.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val API_URL = "https://api.spacetraders.io"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(API_URL)
    .build()

interface SpaceTradersApiService {
    @GET("/game/status")
    suspend fun getGameStatus(): String
}

object SpaceTradersApi {
    val retrofitService : SpaceTradersApiService by lazy {
        retrofit.create(SpaceTradersApiService::class.java) }
}