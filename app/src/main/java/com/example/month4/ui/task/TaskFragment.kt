package com.example.month4.ui.task

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import com.example.month4.App
import com.example.month4.R
import com.example.month4.databinding.FragmentTaskBinding
import com.example.month4.ui.home.HomeFragment
import com.example.month4.model.Task
import kotlin.concurrent.timerTask

class TaskFragment : Fragment() {

    private lateinit var binding: FragmentTaskBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSave.setOnClickListener {
            val task = Task(
                title = binding.etTitle.text.toString(),
                description = binding.etDesk.text.toString())
            App.db.taskDao().insert(task)

        setFragmentResult(HomeFragment.RESULT_REQUEST_KEY, bundleOf(HomeFragment.TASK_KEY to task))
            findNavController().navigateUp()
    }
    }
}