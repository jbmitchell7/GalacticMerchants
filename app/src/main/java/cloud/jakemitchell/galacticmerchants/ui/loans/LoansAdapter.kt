package cloud.jakemitchell.galacticmerchants.ui.loans

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import cloud.jakemitchell.galacticmerchants.databinding.LoanCardBinding
import cloud.jakemitchell.galacticmerchants.network.data.AvailableLoan

class LoansAdapter(private val onSelect: (AvailableLoan) -> Unit) :
    ListAdapter<AvailableLoan, LoansAdapter.LoansViewHolder>(DiffCallback) {

    class LoansViewHolder(
        val binding: LoanCardBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(availableLoan: AvailableLoan) {
            binding.loan = availableLoan
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<AvailableLoan>() {
        override fun areItemsTheSame(oldItem: AvailableLoan, newItem: AvailableLoan): Boolean {
            return oldItem.amount == newItem.amount
        }

        override fun areContentsTheSame(oldItem: AvailableLoan, newItem: AvailableLoan): Boolean {
            return oldItem.amount == newItem.amount
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LoansViewHolder {
        return LoansViewHolder(
            LoanCardBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: LoansViewHolder, position: Int) {
        val loan = getItem(position)
        holder.binding.loanCardAmount.text = "Loan Amount: $${loan.amount}"
        holder.binding.loanCardCollateral.text = "Collateral Required? ${loan.collateralRequired}"
        holder.binding.loanCardRate.text = "Loan Rate: ${loan.rate}"
        holder.binding.loanCardTerm.text = "Term: ${loan.termInDays}"
        holder.binding.loanCardType.text = "Type of Loan: ${loan.type}"
        holder.binding.takeLoanBtn.setOnClickListener{
            onSelect(loan)
        }
    }

}