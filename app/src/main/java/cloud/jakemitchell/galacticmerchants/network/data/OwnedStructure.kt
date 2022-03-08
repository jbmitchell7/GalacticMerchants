package cloud.jakemitchell.galacticmerchants.network.data

data class OwnedStructure(
    val active: Boolean,
    val consumes: List<String>,
    val id: String,
    val Inventory: List<InventoryGood>,
    val location: String,
    //need to call ownedby.username
    val ownedBy: String,
    val produces: List<String>,
    val status: String,
    val type: String
)

data class InventoryGood(
    val good: String,
    val quantity: Int
)
