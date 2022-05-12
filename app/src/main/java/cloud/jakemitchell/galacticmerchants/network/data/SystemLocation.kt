package cloud.jakemitchell.galacticmerchants.network.data

data class SystemLocation(
    val allowsConstruction: Boolean,
    val name: String,
    val symbol: String,
    val type: String,
    val x: Int,
    val y: Int
)

data class SystemLocations(
    val locations: List<SystemLocation>
)
