package cloud.jakemitchell.galacticmerchants.network.data

data class ActiveLoan(
    val id: String,
    val due: String,
    val repaymentAmount: Int,
    val status: String,
    val type: String
)

data class LoanReceipt(
    val credits: Int,
    val loan: ActiveLoan
)
