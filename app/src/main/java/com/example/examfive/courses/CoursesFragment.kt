package com.example.examfive.courses

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.examfive.BaseFragment
import com.example.examfive.R
import com.example.examfive.databinding.FragmentCoursesBinding
import com.example.examfive.databinding.ItemRecyclerScrollContainerBinding
import com.example.homeworkseventeen.resource.Resource
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class CoursesFragment : BaseFragment<FragmentCoursesBinding>(FragmentCoursesBinding::inflate) {
    private val viewModel: CoursesViewModel by viewModels()
    private lateinit var adapter: ActiveCoursesRecyclerAdapter

    override fun setUp() {
        initItemRecycler()
        viewModel.getCourses()

    }

    override fun listeners() {


    }
    private fun initItemRecycler() {
        adapter = ActiveCoursesRecyclerAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

    }

    override fun bindObserves() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.coursesFlow.collect {
                    when (it) {
                        is Resource.Loading -> {
                            Toast.makeText(requireContext(), "loading", Toast.LENGTH_SHORT).show()
                        }

                        is Resource.Success -> {
//                           binding.bla.text =  it.responseData.toString()
                            Toast.makeText(requireContext(), "log in success", Toast.LENGTH_SHORT)
                                .show()
                            val listToSubmit = it.responseData.activeCourses!!.toMutableList()
                            listToSubmit.add(0, ActiveCourse(type = "scroll"))
                            adapter.setList(it.responseData.newCourses!!.toMutableList())
                            adapter.submitList(listToSubmit)



                        }

                        is Resource.Failed -> {
//                                binding.bla.text = it.message
                            Toast.makeText(requireContext(), "no  success", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }

                }
            }
        }
    }

}