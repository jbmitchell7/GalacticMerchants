package cloud.jakemitchell.galacticmerchants.network.data

data class Loan(
    val due: String,
    val id: String,
    val repaymentAmount: Int,
    val status: String,
    val type: String
)
