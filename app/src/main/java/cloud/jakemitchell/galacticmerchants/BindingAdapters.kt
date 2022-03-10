package cloud.jakemitchell.galacticmerchants

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import cloud.jakemitchell.galacticmerchants.network.data.AvailableLoan
import cloud.jakemitchell.galacticmerchants.ui.loans.LoansAdapter

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<AvailableLoan>?) {
    val adapter = recyclerView.adapter as LoansAdapter
    adapter.submitList(data)
}