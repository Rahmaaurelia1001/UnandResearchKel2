package com.example.tbptb.network

import com.example.tbptb.data.AuthRequest
import com.example.tbptb.data.AuthResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("api/login") // Endpoint untuk login
    fun login(@Body request: AuthRequest): Call<AuthResponse>
}
