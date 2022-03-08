package cloud.jakemitchell.galacticmerchants.network.data

data class User(
    val username: String,
    val credits: Int,
    val ships: List<Ship>,
    val loans: List<Loan>
)
