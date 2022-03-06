package cloud.jakemitchell.galacticmerchants.ui.login

import android.content.Context
import android.content.Intent
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

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
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

//        val textView: TextView = binding.textLogin
//        loginViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
        val statusObserver = Observer<GameStatus> { gameStatus ->
            val loginBtn: Button = binding.login
            val tokenText: EditText = binding.accessToken
            val userText: EditText = binding.crewName
//            val pref = getPreferences(Context.MODE_PRIVATE)
//            val edit = pref.edit()
            loginBtn.setOnClickListener {
                //will remove eventually
                loginBtn.text = gameStatus.status
//                edit.apply()
            }
        }
        loginViewModel.gameStatus.observe(this, statusObserver)
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}