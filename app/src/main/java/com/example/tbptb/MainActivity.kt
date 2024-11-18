@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.tbptb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tbptb.ui.theme.TBPTBTheme
import kotlinx.coroutines.delay
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.clickable
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material3.Icon
import androidx.compose.material.icons.filled.AccountCircle


@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TBPTBTheme {
                val navController = rememberNavController()

                // Set splash screen sebagai layar awal
                NavHost(navController = navController, startDestination = "splash") {
                    composable("splash") { SplashScreen(navController) }
                    composable("main") { MainContent(navController) }
                    composable("login") { LoginScreen(navController) } // Tambahkan navController
                    composable("signup") { SignUpScreen(navController) } // Tambahkan navController
                    composable("dashboard") { DashboardScreen() } // Tidak perlu navController jika tidak ada navigasi lanjutan
                }
            }
        }
    }
}

@Composable
fun SplashScreen(navController: NavController) {
    LaunchedEffect(Unit) {
        delay(2000) // Splash screen selama 2 detik
        navController.navigate("main") {
            popUpTo("splash") { inclusive = true }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF469C8F)),
        contentAlignment = Alignment.Center
    ) {
        Image(painter = painterResource(id = R.drawable.logo_ur_app), contentDescription = "Logo")
    }
}

@Composable
fun MainContent(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF469C8F))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Gambar Buku
            Image(
                painter = painterResource(id = R.drawable.buku),
                contentDescription = "Book Image",
                modifier = Modifier.size(200.dp) // Menentukan ukuran gambar
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Teks Deskriptif
            Text(
                text = "Organize your scientific research \nwith Unand Research APP!",
                style = MaterialTheme.typography.titleMedium,
                color = Color.White,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Register your progress with accuracy.\n" +
                        "Track and share deadline, notes, and tasks with your \n" +
                        "colleagues and advisors.",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Button Login
            Button(
                onClick = { navController.navigate("login") },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White)
            ) {
                Text("Login", color = Color.Black)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Button Sign Up
            TextButton(onClick = { navController.navigate("signup") }) {
                Text("Belum punya akun? Daftar", color = Color.White)
            }
        }
    }
}


@Composable
fun LoginScreen(navController: NavController) {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF469C8F)) // Warna hijau untuk latar belakang penuh
    ) {
        // Gambar di bagian atas
        Image(
            painter = painterResource(id = R.drawable.logo_ur_app),
            contentDescription = "Logo",
            modifier = Modifier
                .size(120.dp)
                .align(Alignment.TopCenter)
                .padding(top = 32.dp)
        )

        // Bagian putih di bawah
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.75f) // Isi 75% dari tinggi layar
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
                )
                .align(Alignment.BottomCenter)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Tulisan Login
                Text(
                    text = "Login",
                    style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // Input Email
                TextField(
                    value = email.value,
                    onValueChange = { email.value = it },
                    label = { Text("Email") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Transparent),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Gray,
                        unfocusedIndicatorColor = Color.LightGray
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Input Password
                TextField(
                    value = password.value,
                    onValueChange = { password.value = it },
                    label = { Text("Password") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Transparent),
                    visualTransformation = PasswordVisualTransformation(),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Gray,
                        unfocusedIndicatorColor = Color.LightGray
                    )
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Tombol Login
                Button(
                    onClick = {
                        navController.navigate("dashboard") // Arahkan ke dashboard
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp), // Tinggi tombol sesuai desain
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF469C8F))
                ) {
                    Text("Login", color = Color.White)
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Tombol "Belum punya akun?"
                TextButton(
                    onClick = { navController.navigate("signup") } // Arahkan ke signup
                ) {
                    Text("Belum punya akun? Daftar", color = Color(0xFF469C8F))
                }
            }
        }
    }
}


@Composable
fun SignUpScreen(navController: NavController) {
    val name = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val confirmPassword = remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF469C8F)) // Warna hijau untuk latar belakang penuh
    ) {
        // Bagian putih dengan sudut membulat di bawah
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.80f) // Mengisi 85% dari tinggi layar
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp)
                )
                .align(Alignment.TopCenter)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Sign Up",
                    style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(40.dp))

                // Input Name
                TextField(
                    value = name.value,
                    onValueChange = { name.value = it },
                    label = { Text("Name") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Transparent), // Menghapus latar belakang
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent, // Latar belakang transparan
                        focusedIndicatorColor = Color.Gray, // Warna garis saat fokus
                        unfocusedIndicatorColor = Color.LightGray // Warna garis saat tidak fokus
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Input Email
                TextField(
                    value = email.value,
                    onValueChange = { email.value = it },
                    label = { Text("Email") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Transparent),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Gray,
                        unfocusedIndicatorColor = Color.LightGray
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Input Password
                TextField(
                    value = password.value,
                    onValueChange = { password.value = it },
                    label = { Text("Password") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Transparent),
                    visualTransformation = PasswordVisualTransformation(),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Gray,
                        unfocusedIndicatorColor = Color.LightGray
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Input Confirm Password
                TextField(
                    value = confirmPassword.value,
                    onValueChange = { confirmPassword.value = it },
                    label = { Text("Confirm Password") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Transparent),
                    visualTransformation = PasswordVisualTransformation(),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Gray,
                        unfocusedIndicatorColor = Color.LightGray
                    )
                )

                Spacer(modifier = Modifier.height(120.dp))

                // Tombol "Create Account"
                Button(
                    onClick = {
                        if (password.value == confirmPassword.value) {

                        } else {
                            // Tampilkan pesan error
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp), // Tinggi tombol sesuai desain
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF469C8F))
                ) {
                    Text("Create Account", color = Color.White)
                }
            }
        }
    }
}

// Implementasi DashboardScreen
@Composable
fun DashboardScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F8FA))
            .padding(16.dp)
    ) {
        // Header Section
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text("Welcome Back!", style = MaterialTheme.typography.bodyMedium)
                Text("Hallo, Rahma!", style = MaterialTheme.typography.headlineSmall)
            }
            IconButton(onClick = { /* Handle notification click */ }) {
                Icon(
                    imageVector = Icons.Default.Notifications,
                    contentDescription = "Notification Icon"
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Calendar Section
        Text("Oktober 2024", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            DateButton("Fri", "11")
            DateButton("Sat", "12")
            DateButton("Sun", "13", isSelected = true)
            DateButton("Mon", "14")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // New Project Button
        Button(
            onClick = { /* Handle new project click */ },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF469C8F))
        ) {
            Text("New Project")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Current Project Section
        Text("Current Project", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            ProjectCard(
                title = "Rancangan Aplikasi Umroh",
                members = listOf("member1.jpg", "member2.jpg"),
                progress = 51
            )
            ProjectCard(
                title = "Sistem Informasi Absensi",
                members = listOf("member1.jpg", "member2.jpg", "member3.jpg"),
                progress = 51
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Your Project Section
        Text("Your Project", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))
        ProjectDetailsCard(
            title = "Perancangan Aplikasi Umroh",
            status = "On Progress",
            objectName = "PT Cahaya Hati Cabang Padang",
            collaborators = listOf("Radatul Mutmainnah", "Regina Nathamiya")
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Bottom Navigation
        NavigationBar {
            NavigationBarItem(
                icon = { Icon(Icons.Default.Group, contentDescription = "Collaborator") },
                label = { Text("Collaborator") },
                selected = false,
                onClick = { /* Handle navigation */ }
            )
            NavigationBarItem(
                icon = { Icon(Icons.Default.Check, contentDescription = "Task") },
                label = { Text("Task") },
                selected = false,
                onClick = { /* Handle navigation */ }
            )
            NavigationBarItem(
                icon = { Icon(Icons.Default.Folder, contentDescription = "Project") },
                label = { Text("Project") },
                selected = true,
                onClick = { /* Handle navigation */ }
            )
        }
    }
}

@Composable
fun DateButton(day: String, date: String, isSelected: Boolean = false) {
    Column(
        modifier = Modifier
            .background(
                if (isSelected) Color(0xFF469C8F) else Color.White,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(day, style = MaterialTheme.typography.bodySmall)
        Text(date, style = MaterialTheme.typography.titleMedium)
    }
}

@Composable
fun ProjectCard(title: String, members: List<String>, progress: Int) {
    Card(
        modifier = Modifier
            .width(150.dp)
            .height(120.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF469C8F))
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(title, style = MaterialTheme.typography.bodyMedium, color = Color.White)
            Spacer(modifier = Modifier.height(8.dp))
            Row {
                members.forEach { member ->
                    // Replace with Image composable
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = "Member",
                        tint = Color.White
                    )

                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            LinearProgressIndicator(
                progress = progress / 100f,
                modifier = Modifier.fillMaxWidth(),
                color = Color.White
            )
            Text("$progress% completed", style = MaterialTheme.typography.bodySmall, color = Color.White)
        }
    }
}

@Composable
fun ProjectDetailsCard(
    title: String,
    status: String,
    objectName: String,
    collaborators: List<String>
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF469C8F))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(title, style = MaterialTheme.typography.bodyMedium, color = Color.White)
            Text("Status: $status", style = MaterialTheme.typography.bodySmall, color = Color.White)
            Text("Objek: $objectName", style = MaterialTheme.typography.bodySmall, color = Color.White)
            Text("Collaborator: ${collaborators.joinToString()}", style = MaterialTheme.typography.bodySmall, color = Color.White)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun MainContentPreview() {
    TBPTBTheme {
        MainContent(navController = rememberNavController())
    }
}
