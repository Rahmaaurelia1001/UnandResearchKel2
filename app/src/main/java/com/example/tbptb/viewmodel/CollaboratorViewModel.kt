package com.example.tbptb.viewmodel

import android.content.Context
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import com.example.tbptb.network.ApiClient
import com.example.tbptb.data.CollaboratorResponse
import com.example.tbptb.data.RequestCollaborator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class CollaboratorViewModel : ViewModel() {

    // Fungsi untuk mengirim request collaborator
    fun requestCollaborator(
        projectId: String,
        collaboratorEmail: List<String>,
        token : String,
        onResult: (Boolean, String) -> Unit // Callback untuk menerima hasil
    ) {
        val request = RequestCollaborator(projectId, collaboratorEmail)


        // Mengirim request ke API
        ApiClient.apiService.requestCollaborator("Bearer $token", request).enqueue(object : Callback<CollaboratorResponse> {
            override fun onResponse(call: Call<CollaboratorResponse>, response: Response<CollaboratorResponse>) {
                if (response.isSuccessful && response.body()?.success == true) {
                    onResult(true, response.body()?.message ?: "Request successful")
                } else {
                    onResult(false, response.body()?.message ?: "Failed to send request")
                }
            }

            override fun onFailure(call: Call<CollaboratorResponse>, t: Throwable) {
                onResult(false, t.message ?: "Unknown error")
            }
        })
    }

    fun addCollaborator(email: String, onResult: Any) {

    }
}

private fun Any.enqueue(callback: Callback<CollaboratorResponse>) {

}
