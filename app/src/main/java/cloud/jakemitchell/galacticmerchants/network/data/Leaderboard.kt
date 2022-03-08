package cloud.jakemitchell.galacticmerchants.network.data

data class Leaderboard(
    val netWorth: List<IndividualNetWorth>,
    val userNetWorth: IndividualNetWorth
)

data class IndividualNetWorth(
    val username: String,
    val netWorth: Int,
    val rank: Int
)
