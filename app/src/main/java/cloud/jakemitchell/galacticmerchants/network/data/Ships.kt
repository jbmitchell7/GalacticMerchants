package cloud.jakemitchell.galacticmerchants.network.data

import com.squareup.moshi.Json

data class Ships(
    val cargo: List<Cargo>,
    @Json(name = "class") val shipClass: String,
    val flightPlanId: String,
    val id: String,
    val location: String,
    val manufacturer: String,
    val maxCargo: Int,
    val plating: Int,
    val spaceAvailable: Int,
    val speed: Int,
    val type: String,
    val weapons: Int,
    val x: Int,
    val y: Int
)
