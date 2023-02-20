package com.example.month4.ui.onBoard.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.month4.databinding.ItemOnBoardingBinding
import com.example.month4.ui.model.OnBoard
import com.example.month4.ui.utils.loadImage

class OnBoardingAdapter(private val onStartClick:()->Unit) : Adapter<OnBoardingAdapter.OnBoardingViewHolder>() {

    val data = arrayListOf<OnBoard>(
        OnBoard(
            "Успех",
            "Действуйте не спеша, медлено но уверенно",
            "https://img.freepik.com/free-vector/hand-drawn-business-planning-with-task-list_23-2149164275.jpg?w=2000"
        ),
        OnBoard(
            "Знание",
            "Каждый день работайте над тем что-бы повесить своим умения",
            "https://miro.medium.com/max/1400/1*8ygFKYb0Yo6Hc-vnScGA9A.png"
        ),
        OnBoard(
            "Умение",
            "Действуй правельно, учись на ошибках.",
            "http://theieltscoach.com/wp-content/uploads/2014/05/Learn-from-your-IELTS-mistakes.jpg"
        )
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingViewHolder {
        return OnBoardingViewHolder(
            ItemOnBoardingBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: OnBoardingViewHolder, position: Int) {
        holder.bind(data.get(position))
    }

    override fun getItemCount() = data.size

    inner class OnBoardingViewHolder(private val binding: ItemOnBoardingBinding) :
        ViewHolder(binding.root) {
        fun bind(onBoard: OnBoard) {
            binding.btnStart.isVisible = adapterPosition == 2
            binding.btnSkip.isVisible = adapterPosition != 2

            binding.btnStart.setOnClickListener {
                onStartClick()
            }
            binding.btnSkip.setOnClickListener {
                onStartClick()
            }


            binding.title.text = onBoard.title
            binding.description.text = onBoard.desc
            binding.ivBoard.loadImage(onBoard.image.toString())


        }

    }
}