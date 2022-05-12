package cloud.jakemitchell.galacticmerchants.network.data

data class AvailableLoan(
    val amount: Int,
    val collateralRequired: Boolean,
    val rate: Int,
    val termInDays: Int,
    val type: String
)

data class AvailableLoans(
    val loans: List<AvailableLoan>
)
