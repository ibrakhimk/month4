package com.example.month4.ui.home

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.month4.App
import com.example.month4.R
import com.example.month4.databinding.FragmentHomeBinding
import com.example.month4.ui.home.adapter.TaskAdapter
import com.example.month4.model.Task

@Suppress("NAME_SHADOWING")
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!
    private lateinit var adapter: TaskAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = TaskAdapter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.taskFragment)
            setData()
        }
        val tasks = App.db.taskDao().getAll()
        adapter.addTask(tasks)
        binding.recyclerView.adapter = adapter
        adapter.onLongClick = {
            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle("Delete?")
            builder.setMessage("Delete sure")
            builder.setPositiveButton("Delete") { _, _ ->
                App.db.taskDao().delete(tasks)
                Toast.makeText(requireContext(), "Удален объект $it", Toast.LENGTH_SHORT).show()
                adapter.remove(it)
                binding.recyclerView.adapter = adapter
            }
            builder.setNegativeButton("cancel") { dialog, _ -> dialog.cancel() }
            builder.show()
        }


    }

    private fun setData() {
        val listOfTask = App.db.taskDao()?.getAll()
        adapter.addTask(listOfTask as List<Task>)
    }


    companion object {
        const val RESULT_REQUEST_KEY = "requestKey"
        const val TASK_KEY = "task_key"
    }
}