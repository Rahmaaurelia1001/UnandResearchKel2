package com.example.tbptb

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskScreen(navController: NavController) {
    var taskName by remember { mutableStateOf("") }
    var taskDescription by remember { mutableStateOf("") }
    var taskDeadlineDate by remember { mutableStateOf<Date?>(null) }
    var taskDeadlineTime by remember { mutableStateOf("00:00") }
    var taskAssignee by remember { mutableStateOf("") }
    var showDatePicker by remember { mutableStateOf(false) }
    var showTimePicker by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(false) }

    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Task") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFFF5F5F5))
                .padding(16.dp)
        ) {
            // Form tambah task
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White, RoundedCornerShape(16.dp))
                    .padding(24.dp)
            ) {
                Text(
                    text = "Tambahkan Task",
                    style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold, fontSize = 24.sp)
                )

                TaskInputField(label = "Judul", value = taskName, onValueChange = { taskName = it })
                TaskInputField(label = "Deskripsi", value = taskDescription, onValueChange = { taskDescription = it })
                TaskInputField(label = "Penanggung Jawab", value = taskAssignee, onValueChange = { taskAssignee = it })

                Button(
                    onClick = { showDatePicker = true },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF469C8F))
                ) {
                    Text("Pilih Tanggal", color = Color.White)
                }

                Button(
                    onClick = {
                        isLoading = true
                        val task = Task(
                            name = taskName,
                            description = taskDescription,
                            deadline = taskDeadlineDate?.let { SimpleDateFormat("yyyy-MM-dd").format(it) } ?: "",
                            assignee = taskAssignee
                        )

                        coroutineScope.launch {
                            try {
                                val response = ApiClient.apiService.addTask(task)
                                if (response.isSuccessful) {
                                    Toast.makeText(navController.context, "Task berhasil disimpan", Toast.LENGTH_SHORT).show()
                                    navController.popBackStack()
                                } else {
                                    Toast.makeText(navController.context, "Error: ${response.message()}", Toast.LENGTH_SHORT).show()
                                }
                            } catch (e: Exception) {
                                Toast.makeText(navController.context, "Kesalahan: ${e.message}", Toast.LENGTH_SHORT).show()
                            } finally {
                                isLoading = false
                            }
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF469C8F)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Simpan Task", color = Color.White)
                }
            }
        }
    }
}

@Composable
fun TaskInputField(label: String, value: String, onValueChange: (String) -> Unit) {
    Column {
        Text(label, style = MaterialTheme.typography.bodyMedium)
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFEFEFEF), RoundedCornerShape(8.dp))
                .padding(8.dp)
        )
    }
}

data class Task(
    val name: String,
    val description: String,
    val deadline: String,
    val assignee: String
)
