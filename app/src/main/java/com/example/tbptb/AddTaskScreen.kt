package com.example.tbptb

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
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

    val tasks = remember { mutableStateListOf<Task>() }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Tambahkan Task") },
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
                .background(Color(0xFFF5F5F5))
                .padding(paddingValues)
        ) {
            // Form untuk menambah task
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .background(Color.White, RoundedCornerShape(16.dp))
                    .padding(24.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        text = "Tambahkan Task",
                        style = MaterialTheme.typography.headlineSmall.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp
                        ),
                        color = Color.Black
                    )

                    TaskInputField(
                        label = "Judul",
                        value = taskName,
                        onValueChange = { taskName = it }
                    )

                    TaskInputField(
                        label = "Deskripsi",
                        value = taskDescription,
                        onValueChange = { taskDescription = it },
                        height = 100.dp
                    )

                    TaskInputField(
                        label = "Penanggung Jawab",
                        value = taskAssignee,
                        onValueChange = { taskAssignee = it }
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Deadline: ${taskDeadlineDate?.let { SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(it) } ?: "Pilih Tanggal"}",
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.weight(1f)
                        )
                        Button(
                            onClick = { showDatePicker = true },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF469C8F))
                        ) {
                            Text("Pilih Tanggal", color = Color.White)
                        }
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Waktu: $taskDeadlineTime",
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.weight(1f)
                        )
                        Button(
                            onClick = { showTimePicker = true },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF469C8F))
                        ) {
                            Text("Pilih Waktu", color = Color.White)
                        }
                    }

                    Button(
                        onClick = {
                            if (taskName.isNotBlank() && taskAssignee.isNotBlank()) {
                                tasks.add(
                                    Task(
                                        name = taskName,
                                        description = taskDescription,
                                        deadline = "${taskDeadlineDate?.let { SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(it) }} $taskDeadlineTime",
                                        assignee = taskAssignee
                                    )
                                )
                                taskName = ""
                                taskDescription = ""
                                taskDeadlineDate = null
                                taskDeadlineTime = "00:00"
                                taskAssignee = ""
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF469C8F)),
                        shape = RoundedCornerShape(50)
                    ) {
                        Text("Simpan Task", color = Color.White)
                    }
                }
            }

            // Daftar task yang ditambahkan
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 16.dp),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(tasks.size) { index ->
                    val task = tasks[index]
                    TaskCard(task = task)
                }
            }
        }
    }

    if (showDatePicker) {
        val calendar = Calendar.getInstance()
        DatePickerDialog(
            navController.context,
            { _, year, month, dayOfMonth ->
                taskDeadlineDate = Calendar.getInstance().apply {
                    set(year, month, dayOfMonth)
                }.time
                showDatePicker = false
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    if (showTimePicker) {
        val calendar = Calendar.getInstance()
        TimePickerDialog(
            navController.context,
            { _, hourOfDay, minute ->
                taskDeadlineTime = String.format("%02d:%02d", hourOfDay, minute)
                showTimePicker = false
            },
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),
            true
        ).show()
    }
}

@Composable
fun TaskInputField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    height: Dp = 56.dp,
    isClickable: Boolean = false
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(height)
            .background(Color(0xFFE0F2F1))
            .padding(16.dp)
    ) {
        if (isClickable) {
            Text(text = value, color = Color.Gray, style = MaterialTheme.typography.bodyMedium)
        } else {
            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                textStyle = MaterialTheme.typography.bodyMedium,
                singleLine = true,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

// Model data untuk task
data class Task(
    val name: String,
    val description: String,
    val deadline: String,
    val assignee: String
)

@Composable
fun TaskCard(task: Task) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(8.dp))
            .padding(16.dp)
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("Nama: ${task.name}", style = MaterialTheme.typography.bodyMedium)
            Text("Deskripsi: ${task.description}", style = MaterialTheme.typography.bodyMedium)
            Text("Deadline: ${task.deadline}", style = MaterialTheme.typography.bodyMedium)
            Text("Penanggung Jawab: ${task.assignee}", style = MaterialTheme.typography.bodyMedium)
        }
    }
}
