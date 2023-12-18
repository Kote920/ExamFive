package com.example.examfive.courses

import android.util.Log.d
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homeworkseventeen.resource.Resource
import com.example.homeworksixteen.network.Network
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CoursesViewModel : ViewModel() {


    private val _coursesFlow = MutableSharedFlow<Resource<CoursesResponse>>()

    val coursesFlow: SharedFlow<Resource<CoursesResponse>> = _coursesFlow.asSharedFlow()


    fun getCourses() {
        viewModelScope.launch {
            try {
                _coursesFlow.emit(Resource.Loading())

                val response = Network.courseService().getCourses()
                if (response.isSuccessful) {
                    d("tester","bla")
                    val courses = response.body()!!
                    _coursesFlow.emit(Resource.Success(courses))
                    d("tester","bla")

                } else {

                    _coursesFlow.emit(Resource.Failed("Request Failed"))

                }
            }catch (e: Exception){
                    _coursesFlow.emit(Resource.Failed(e.message?: "Unknown error"))
            }
        }

    }


}