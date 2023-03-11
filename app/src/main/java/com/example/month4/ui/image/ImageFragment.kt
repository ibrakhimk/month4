package com.example.month4.ui.image

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
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
        // Получаем корневой view фрагмента
        val rootView = requireActivity().window.decorView.rootView

// Устанавливаем флаги, чтобы скрыть системные UI элементы и зафиксировать экран в полноэкранном режиме
        rootView.systemUiVisibility = (View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION)
        activity?.window?.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
//        val rootView = requireActivity().window.decorView.rootView
//        rootView.systemUiVisibility = (View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION)
//        rootView.setOnSystemUiVisibilityChangeListener { visibility ->
//            if (visibility and View.SYSTEM_UI_FLAG_FULLSCREEN == 0) {
//                // Если UI элементы снова видны, то блокируем экран
//                rootView.systemUiVisibility = (View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION)
//            }
//        }
        binding.btnClick.setOnClickListener {
            activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
            rootView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
        }

        saveName()
        binding.imgView.loadImage(pref.getImage())
        saveImage()
        exitGoogle()

    }

    private fun click() {

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
