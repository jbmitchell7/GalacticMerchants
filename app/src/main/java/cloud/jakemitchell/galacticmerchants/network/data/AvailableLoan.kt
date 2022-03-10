package cloud.jakemitchell.galacticmerchants.network.data

import cloud.jakemitchell.galacticmerchants.R
import cloud.jakemitchell.galacticmerchants.ui.loans.LoansViewModel

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
