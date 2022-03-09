package cloud.jakemitchell.galacticmerchants.network.data

data class LoginData(
    val token: String,
    val user: LoginUserResponse
)

data class LoginUserResponse(
    val username: String,
    val credits: Int,
    val ships: List<OwnedShip>,
    val loans: List<ActiveLoan>
)
