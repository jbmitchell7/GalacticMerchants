package cloud.jakemitchell.galacticmerchants.network.data

data class Structure(
    val completed: Boolean,
    val id: String,
    val materials: List<Material>,
    val name: String,
    val stability: Double
)

data class Material(
    val good: String,
    val quantity: Int,
    val targetQuantity: Int
)