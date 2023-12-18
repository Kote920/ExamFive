package com.example.examfive.courses

import com.squareup.moshi.Json

data class Course(
    val id: String,
    @Json(name = "icon_type")
    val iconType: String,
    val duration: String,
    val title: String,
    val question: String,
    @Json(name = "main_color")
    val mainColor: String
)

data class ActiveCourse(
    val id: String? = null,
    @Json(name = "booking_time")
    val bookingTime: String? = null,
    val progress: String? = null,
    val title: String? = null,
    @Json(name = "main_color")
    val mainColor: String? = null,
    @Json(name = "background_color_present")
    val backgroundColorPresent: String? = null,
    @Json(name = "play_button_color_present")
    val playButtonColorPresent: String? = null,
    val image: String? = null,
    val type:String? = null
)

data class CoursesResponse(
    @Json(name = "new_courses")
    val newCourses: List<Course>? = null,
    @Json(name = "active_courses")
    val activeCourses: List<ActiveCourse>? = null
)