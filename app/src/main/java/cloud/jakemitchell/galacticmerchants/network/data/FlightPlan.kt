package cloud.jakemitchell.galacticmerchants.network.data

data class FlightPlan(
    val arrivesAt: String,
    val createdAt: String,
    val departure: String,
    val destination: String,
    val distance: Int,
    val fuelConsumed: Int,
    val fuelRemaining: Int,
    val id: String,
    val shipId: String,
    val terminatedAt: String,
    val timeRemainingInSeconds: Int
)
