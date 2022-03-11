package cloud.jakemitchell.galacticmerchants.ui.loans

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import cloud.jakemitchell.galacticmerchants.databinding.FragmentLoansBinding

class LoansFragment : Fragment() {

    private var _binding: FragmentLoansBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val loansViewModel =
            ViewModelProvider(this)[LoansViewModel::class.java]

        _binding = FragmentLoansBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.lifecycleOwner = this
        binding.loansViewModel= loansViewModel
        binding.loanCardList.adapter = LoansAdapter()

        return root
    }
}