package com.example.homeworksixteen.service

import com.example.examfive.courses.CoursesResponse
import retrofit2.Response
import retrofit2.http.GET

interface CoursesService {

    @GET("v3/83160a49-fe85-46ba-bcf8-3cf5aa09f92b")
    suspend fun getCourses(): Response<CoursesResponse>

}