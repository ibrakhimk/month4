package com.example.month4.ui.image

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.example.month4.databinding.FragmentImageBinding
import com.example.month4.data.local.Pref
import com.example.month4.utils.loadImage
import com.google.firebase.auth.FirebaseAuth


class ImageFragment : Fragment() {

    private lateinit var binding: FragmentImageBinding
    private lateinit var pref: Pref
    private lateinit var auth: FirebaseAuth
    private val launcher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK && it.data != null) {
                val uri: Uri? = it.data?.data
                pref.setImage(uri.toString())
                binding.imgView.loadImage(uri.toString())
            }
        }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentImageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pref = Pref(requireContext())
        saveName()
        binding.imgView.loadImage(pref.getImage())
        saveImage()
        exitGoogle()
    }

    private fun exitGoogle() {
        binding.btnExit.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            activity?.finish()
        }
    }

    private fun saveImage() {
        binding.imgView.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            launcher.launch(intent)
        }
    }

    private fun saveName() {
        binding.myEditText.setText(pref.getUser())

        binding.myEditText.addTextChangedListener {
            pref.setUser(binding.myEditText.text.toString())
        }
    }
}
