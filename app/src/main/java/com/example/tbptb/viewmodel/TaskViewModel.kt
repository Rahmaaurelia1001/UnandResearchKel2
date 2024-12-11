package com.example.tbptb.viewmodel

import androidx.lifecycle.ViewModel
import com.example.tbptb.data.Task
import com.example.tbptb.data.TaskResponse
import com.example.tbptb.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TaskViewModel(private val apiService: ApiService) : ViewModel() {

    fun addTaskToServer(task: Task, onResult: (Boolean, String) -> Unit) {
        apiService.addTask(task).enqueue(object : Callback<TaskResponse> {
            override fun onResponse(call: Call<TaskResponse>, response: Response<TaskResponse>) {
                if (response.isSuccessful && response.body()?.success == true) {
                    onResult(true, response.body()?.message ?: "Task added successfully")
                } else {
                    onResult(false, response.body()?.message ?: "Failed to add task")
                }
            }

            override fun onFailure(call: Call<TaskResponse>, t: Throwable) {
                onResult(false, t.message ?: "Error occurred")
            }
        })
    }
}
