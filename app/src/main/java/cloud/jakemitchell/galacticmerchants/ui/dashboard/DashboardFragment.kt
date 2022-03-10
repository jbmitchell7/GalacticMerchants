package cloud.jakemitchell.galacticmerchants.ui.dashboard

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import cloud.jakemitchell.galacticmerchants.login.LoginActivity
import cloud.jakemitchell.galacticmerchants.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    private val binding get() = _binding!!

    private val pref = activity?.getSharedPreferences("AUTHDATA", Context.MODE_PRIVATE)

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this)[DashboardViewModel::class.java]

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val intent = Intent(activity, LoginActivity::class.java)

        val userName: TextView = binding.textUsername
        val shipCount: TextView = binding.textShipCount
        val structureCount: TextView = binding.textStructureCount
        val credits: TextView = binding.textCredits
        dashboardViewModel.accountInfo.observe(viewLifecycleOwner) {
            userName.text = "Username: ${it.user.username}"
            credits.text = "Credits: ${it.user.credits}"
            shipCount.text = "Number of Ships: ${it.user.shipCount}"
            structureCount.text = "Number of Structures: ${it.user.structureCount}"
        }

        val logoutButton: Button = binding.logoutBtn
        logoutButton.setOnClickListener {
            if (pref != null) {
                with (pref.edit()) {
                    putString("TOKEN", "")
                    putString("USER", "")
                    apply()
                }
                startActivity(intent)
                activity?.finish()
            }
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}