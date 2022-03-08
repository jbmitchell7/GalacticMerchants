package cloud.jakemitchell.galacticmerchants.network.data

import com.squareup.moshi.Json

data class ShipListing(
    @Json(name = "class") val shipClass: String,
    val manufacturer: String,
    val maxCargo: Int,
    val plating: Int,
    val purchaseLocations: List<ListingLocation>,
    val speed: Int,
    val type: String,
    val weapons: Int
)

data class ListingLocation(
    val location: String,
    val price: Int,
    val system: String
)
