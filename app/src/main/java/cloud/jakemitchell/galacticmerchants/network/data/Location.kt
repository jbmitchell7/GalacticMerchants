package cloud.jakemitchell.galacticmerchants.network.data

data class Location(
    val allowsConstruction: Boolean,
    val dockedShips: Int,
    val name: String,
    val symbol: String,
    val type: String,
    val x: Int,
    val y: Int
)
