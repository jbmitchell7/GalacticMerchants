package cloud.jakemitchell.galacticmerchants.network.data

data class User(
    val username: String,
    val credits: Int,
    val ships: List<Ships>,
    val loans: List<Loans>
)
