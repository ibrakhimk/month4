package com.example.month4.ui.home.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView.*
import com.example.month4.R
import com.example.month4.databinding.ItemTaskBinding
import com.example.month4.ui.home.HomeFragment
import com.example.month4.model.Task

class TaskAdapter(val listener: HomeFragment) : Adapter<TaskAdapter.TaskViewHolder>() {
    private val data = arrayListOf<Task>()
    private var color = true

    var onClick: ((Task) -> Unit)? = null
    var onLongClick: ((Int) -> Unit)? = null

    fun addTask(task: List<Task>) {
        data.clear()
        data.addAll(task)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(
            ItemTaskBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int,) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.count()
    }

    fun deleteItem(pos: Int): Task {
        return data[pos]
    }

    inner class TaskViewHolder(private val binding: ItemTaskBinding) : ViewHolder(binding.root) {
        @SuppressLint("ResourceAsColor")
        fun bind(task: Task) {
            with(binding) {
                title.text = task.title
                description.text = task.description
                itemView.setOnClickListener {
                    onClick?.invoke(task)
                }
                itemView.setOnLongClickListener {
                    onLongClick?.invoke(adapterPosition)
                    return@setOnLongClickListener true
                }
            }
            if (color && position % 2 == 0) {
                binding.llMain.setBackgroundColor(Color.BLACK)
                binding.description.setTextColor(Color.WHITE)
                binding.title.setTextColor(Color.WHITE)
            } else {
                binding.llMain.setBackgroundColor(Color.WHITE)
                binding.description.setTextColor(Color.BLACK)
                binding.title.setTextColor(Color.BLACK)
            }
//            if (color && position % 2 == 0){
//                binding.llMain.setBackgroundColor(R.color.black)
//                binding.title.setTextColor(R.color.white)
//                binding.description.setTextColor(R.color.white)
//            } else {
//                binding.llMain.setBackgroundColor(R.color.white)
//                binding.title.setTextColor(R.color.black)
//                binding.description.setTextColor(R.color.black)
//            }

        }
    }

    fun remove(pos: Int) {
        data.removeAt(pos)
        notifyItemRemoved(pos)
    }
}
//if (position % 2 == 0) {
//    binding.llMain.setBackgroundColor(
//        ContextCompat.getColor(
//            binding.llMain.context, R.color.grey
//        )
//    )
//} else {
//    binding.llMain.setBackgroundColor(
//        ContextCompat.getColor(
//            binding.llMain.context,
//            R.color.white
//        )
//    )
//}