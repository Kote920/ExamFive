package com.example.examfive.courses

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.examfive.databinding.ItemRecyclerScrollContainerBinding
import com.example.examfive.databinding.ItemRecyclerviewBinding
import com.example.examfive.databinding.ItemRecyclerviewScrollBinding

class NewCoursesRecyclerAdapter() :

    ListAdapter<Course, RecyclerView.ViewHolder>(object :
        DiffUtil.ItemCallback<Course>() {
        override fun areItemsTheSame(oldItem: Course, newItem: Course): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Course, newItem: Course): Boolean {
            return oldItem == newItem
        }

    }) {


    companion object {
        const val NEW_TYPE = 1


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CourseViewHolder(
            ItemRecyclerviewScrollBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is CourseViewHolder) {
            holder.bind()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return NEW_TYPE
    }

    inner class CourseViewHolder(private val binding: ItemRecyclerviewScrollBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind() {
            val item = currentList[adapterPosition]
            with(binding) {
                binding.tvTitle.text = item.title


            }


        }


    }

}