package cloud.jakemitchell.galacticmerchants.network.data

data class User(
    val username: String,
    val credits: Int,
    val shipCount: Int,
    val structureCount: Int,
    val joinedAt: String,
)
