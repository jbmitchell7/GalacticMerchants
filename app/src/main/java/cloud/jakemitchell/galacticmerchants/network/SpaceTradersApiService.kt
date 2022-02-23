package cloud.jakemitchell.galacticmerchants.network

import retrofit2.http.GET


interface SpaceTradersApiService {
    @GET("/game/status")
    suspend fun getGameStatus(): String
}