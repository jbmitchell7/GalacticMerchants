package cloud.jakemitchell.galacticmerchants.network.data

data class User(
    val user: UserData
)

data class UserData(
    val username: String,
    val shipCount: Int,
    val structureCount: Int,
    val joinedAt: String,
    val credits: Int,
)
