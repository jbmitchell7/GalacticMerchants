package cloud.jakemitchell.galacticmerchants.ui.systems

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import cloud.jakemitchell.galacticmerchants.databinding.LocationCardBinding
import cloud.jakemitchell.galacticmerchants.network.data.SystemLocation

class SystemsAdapter(private val onSelect: (SystemLocation) -> Unit) :
    ListAdapter<SystemLocation, SystemsAdapter.SystemsViewHolder>(DiffCallback){

    class SystemsViewHolder(
        val binding: LocationCardBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(systemLocation: SystemLocation) {
            binding.location = systemLocation
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<SystemLocation>() {
        override fun areItemsTheSame(oldItem: SystemLocation, newItem: SystemLocation): Boolean {
            return oldItem.symbol == newItem.symbol
        }

        override fun areContentsTheSame(oldItem: SystemLocation, newItem: SystemLocation): Boolean {
            return oldItem.symbol == newItem.symbol
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SystemsViewHolder {
        return SystemsViewHolder(
            LocationCardBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: SystemsViewHolder, position: Int) {
        val location = getItem(position)
        holder.binding.locationCardName.text = "Name: ${location.name}"
        holder.binding.locationCardSymbol.text = "Symbol: ${location.symbol}"
        holder.binding.locationCardType.text = "Type: ${location.type}"
        holder.binding.locationCardX.text = "X Coordinate: ${location.x}"
        holder.binding.locationCardY.text = "Y Coordinate: ${location.y}"
    }
}