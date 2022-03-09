package cloud.jakemitchell.galacticmerchants.network

import cloud.jakemitchell.galacticmerchants.network.data.*
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

private const val API_URL =
    "https://api.spacetraders.io"

val interceptor : HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

val client : OkHttpClient = OkHttpClient.Builder().apply {
        addInterceptor(interceptor)
    }.build()

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(API_URL)
    .client(client)
    .build()

interface SpaceTradersApiService {
    @GET("/game/status")
    suspend fun getGameStatus(): GameStatus

    @GET("/game/leaderboard/net-worth")
    suspend fun getLeaderboard(): Leaderboard

    @GET("/my/loans")
    suspend fun getLoans(): List<ActiveLoan>

    @GET("/my/ships")
    suspend fun getMyShips(): List<OwnedShip>

    @GET("my/ships/{shipId}")
    suspend fun getOneShip(): OwnedShip

    @GET("my/account")
    suspend fun getAccount(@Header("Authorization") authorization: String): User

    @POST("/users/{username}/claim")
    suspend fun createUsername(@Path("username") username: String): LoginData
}

object SpaceTradersApi {
    val retrofitService: SpaceTradersApiService by lazy {
        retrofit.create(SpaceTradersApiService::class.java)
    }
}