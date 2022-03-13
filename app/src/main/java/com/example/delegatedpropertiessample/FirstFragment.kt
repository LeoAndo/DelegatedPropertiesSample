package com.example.delegatedpropertiessample

import android.os.Bundle
import android.os.SystemClock
import androidx.fragment.app.Fragment
import android.view.View
import com.example.delegatedpropertiessample.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment(R.layout.fragment_first) {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val userSettings by lazy { UserSettings(requireContext()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFirstBinding.bind(view)
        updateDisplay()
        binding.buttonEdit.setOnClickListener {
            val ret = SystemClock.elapsedRealtime()
            userSettings.userName = "userName update: $ret"
            userSettings.iconUrl = "iconUrl update: $ret"
            updateDisplay()
        }
    }

    private fun updateDisplay() {
        binding.textviewUserName.text = userSettings.userName
        binding.textviewIconUrl.text = userSettings.iconUrl
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}