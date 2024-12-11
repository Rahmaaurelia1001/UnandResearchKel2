package com.example.tbptb.data

data class CollaboratorResponse(
    val success: Boolean,    // Status berhasil/gagalnya request
    val message: String,     // Pesan terkait status request
    val collaboratorId: String? = null, // ID collaborator jika berhasil
    val collaboratorName: String? = null, // Nama collaborator jika berhasil
    val email: String? = null // Email collaborator jika diperlukan
)
