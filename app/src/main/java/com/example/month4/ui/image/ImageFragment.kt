package com.example.month4.ui.image

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import com.example.month4.databinding.FragmentImageBinding
import com.example.month4.ui.data.local.Pref


class ImageFragment : Fragment() {

    private lateinit var binding: FragmentImageBinding
    private lateinit var pref: Pref


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentImageBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pref = Pref(requireContext())
        saveName()
    }

    private fun saveName() {
        binding.myEditText.setText(pref.getUser())

        binding.myEditText.addTextChangedListener{
            pref.setUser(binding.myEditText.text.toString())
        }
    }
}