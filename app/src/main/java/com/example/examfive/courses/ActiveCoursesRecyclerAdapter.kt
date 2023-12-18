package com.example.examfive.courses

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.examfive.databinding.ItemRecyclerScrollContainerBinding
import com.example.examfive.databinding.ItemRecyclerviewBinding

class ActiveCoursesRecyclerAdapter() :
    ListAdapter<ActiveCourse, RecyclerView.ViewHolder>(object :
        DiffUtil.ItemCallback<ActiveCourse>() {
        override fun areItemsTheSame(oldItem: ActiveCourse, newItem: ActiveCourse): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ActiveCourse, newItem: ActiveCourse): Boolean {
            return oldItem == newItem
        }

    }) {
    private lateinit var newCourseList: MutableList<Course>

    fun setList(input: MutableList<Course>){
        newCourseList = input

    }


    companion object {
        const val ACTIVE_TYPE = 1
        const val NEW_TYPE = 2


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == ACTIVE_TYPE) {
            return ActiveCourseViewHolder(
                ItemRecyclerviewBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                )
            )
        } else {
            return NewCourseViewHolder(
                ItemRecyclerScrollContainerBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ActiveCourseViewHolder) {
            holder.bind()
        }
        else if(holder is NewCourseViewHolder){
            holder.bind(newCourseList)
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (currentList[position].type == "scroll") {
            return NEW_TYPE
        } else {
            return ACTIVE_TYPE
        }
    }

    inner class ActiveCourseViewHolder(private val binding: ItemRecyclerviewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind() {
            val item = currentList[adapterPosition]
            with(binding) {
                binding.tvTitle.text = item.title
                binding.tvBookingTime.text = item.bookingTime
                binding.root.setBackgroundColor(Color.parseColor("#" + item.mainColor))
                imageManagement(item.image, binding.ivImage)


            }


        }


    }

    inner class NewCourseViewHolder(private val binding: ItemRecyclerScrollContainerBinding) :
        RecyclerView.ViewHolder(binding.root) {

           fun  bind(newCourses: List<Course>){
               val adapter = NewCoursesRecyclerAdapter()
               binding.recyclerViewNewCourses.layoutManager = GridLayoutManager(itemView.context, 3)
               binding.recyclerViewNewCourses.adapter = adapter
               adapter.submitList(newCourses)


            }

    }


}

fun imageManagement(imageUrl: String?, imageView: AppCompatImageView) {
    imageUrl?.let {
        Glide.with(imageView)
            .load(imageUrl)
            .fitCenter()
            .into(imageView)
    }


}
