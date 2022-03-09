package cloud.jakemitchell.galacticmerchants.ui.dashboard

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
import cloud.jakemitchell.galacticmerchants.MainActivity
import cloud.jakemitchell.galacticmerchants.databinding.FragmentDashboardBinding
import cloud.jakemitchell.galacticmerchants.login.LoginActivity

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this)[DashboardViewModel::class.java]

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textDashboard
        dashboardViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        val pref = activity?.getSharedPreferences("AUTHDATA", Context.MODE_PRIVATE)
        val intent = Intent(activity, LoginActivity::class.java)

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