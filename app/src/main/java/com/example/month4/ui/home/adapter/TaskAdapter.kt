package com.example.month4.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.*
import com.example.month4.App
import com.example.month4.databinding.ItemTaskBinding
import com.example.month4.ui.home.HomeFragment
import com.example.month4.ui.model.Task
import com.squareup.picasso.Picasso.Listener

class TaskAdapter(val listener: HomeFragment) : Adapter<TaskAdapter.TaskViewHolder>() {
    private val data = arrayListOf<Task>()

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

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.count()
    }
    fun deleteItem(pos:Int):Task{
        return data[pos]
    }

    inner class TaskViewHolder(private val binding: ItemTaskBinding) : ViewHolder(binding.root) {
        fun bind(task: Task) {
            with(binding) {
                title.text = task.title
                description.text = task.description
                itemView.setOnClickListener{
                    onClick?.invoke(task)
                }
                itemView.setOnLongClickListener {
                    onLongClick?.invoke(adapterPosition)
                    return@setOnLongClickListener true
                }


            }
        }

    }
    fun remove(pos: Int){
        data.removeAt(pos)
        notifyItemRemoved(pos)
    }
}