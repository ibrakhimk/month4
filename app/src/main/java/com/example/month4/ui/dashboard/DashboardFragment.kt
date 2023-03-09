package com.example.month4.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.month4.databinding.FragmentDashboardBinding
import com.example.month4.model.Quote
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    private lateinit var db: FirebaseFirestore

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        db = FirebaseFirestore.getInstance()
        binding.btnSave.setOnClickListener {
            if (binding.etAuthor.text.toString().isNotEmpty()&& binding.etQuotes.text.toString().isNotEmpty()) {
                save()
            } else if (binding.etAuthor.text.toString().isEmpty()&&binding.etQuotes.text.toString().isNotEmpty()) {
                binding.etAuthor.error = "Ошибка"
                binding.etQuotes.error = "Ошибка"
//            } else if (binding.etQuotes.text.isEmpty()) {
//                binding.etQuotes.error = "Ошибка"
            } else {
                binding.etQuotes.error = "Заполните таблицу"
                binding.etAuthor.error = "Заполните таблицу"
            }
        }
    }

    private fun save() {
        val quote = Quote(
            text = binding.etQuotes.text.toString(),
            author = binding.etAuthor.text.toString()
        )
        db.collection(FirebaseAuth.getInstance().currentUser?.uid.toString()).add(quote)
            .addOnSuccessListener {
                Toast.makeText(context, "Ну ты тигр", Toast.LENGTH_SHORT).show()
                binding.etAuthor.setText("")
                binding.etQuotes.setText("")
            }.addOnFailureListener {
                Log.e("lolo", "save" +it.message)
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}