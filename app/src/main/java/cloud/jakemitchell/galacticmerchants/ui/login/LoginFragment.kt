package cloud.jakemitchell.galacticmerchants.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import cloud.jakemitchell.galacticmerchants.databinding.FragmentLoginBinding
import cloud.jakemitchell.galacticmerchants.network.data.GameStatus

class LoginFragment: Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val loginViewModel =
            ViewModelProvider(this)[LoginViewModel::class.java]

        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val statusObserver = Observer<GameStatus> { gameStatus ->
            val loginBtn: Button = binding.login
            val tokenText: EditText = binding.accessToken
            val userText: EditText = binding.crewName
            loginBtn.setOnClickListener {
                //will remove eventually
                loginBtn.text = gameStatus.status
            }
        }
        loginViewModel.gameStatus.observe(viewLifecycleOwner, statusObserver)
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}