package com.example.tbptb

import android.util.Patterns
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.ui.graphics.Color


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CollaboratorScreen(navController: NavController) {
    val emailInput = remember { mutableStateOf("") }
    val emailList = remember { mutableStateListOf<String>() }
    var emailError by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Collaborator") },
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
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Title
            Text(
                text = "Add Collaborator Email",
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold)
            )

            // Email Input Field
            OutlinedTextField(
                value = emailInput.value,
                onValueChange = { emailInput.value = it },
                label = { Text("Enter Email") },
                isError = emailError,
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0xFF469C8F),
                    cursorColor = Color(0xFF469C8F),
                    errorBorderColor = Color.Red
                )
            )

            // Error Message
            if (emailError) {
                Text(
                    text = "Please enter a valid email",
                    color = Color.Red,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            // Add Collaborator Button
            Button(
                onClick = {
                    val email = emailInput.value
                    if (email.isNotBlank() && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                        emailList.add(email)
                        emailInput.value = "" // Reset email input field
                        emailError = false
                    } else {
                        emailError = true // Show error if invalid email
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF469C8F))
            ) {
                Text("Add Collaborator", color = Color.White)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Collaborator List Title
            Text(
                text = "Collaborator List",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
            )

            // Displaying the List of Collaborators (Emails)
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(emailList) { email ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                    ) {
                        Text(
                            text = email,
                            modifier = Modifier.padding(8.dp),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }
    }
}
