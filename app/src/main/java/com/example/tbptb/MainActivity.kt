@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.tbptb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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



@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
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
        // Bagian putih dengan sudut membulat di bawah
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.80f) // Mengisi 80% dari tinggi layar
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp) // Kotak putih berada di bawah dengan sudut atas membulat
                )
                .align(Alignment.BottomCenter) // Letakkan Box di bagian bawah layar
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Login",
                    style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(40.dp))

                // Input Email
                TextField(
                    value = email.value,
                    onValueChange = { email.value = it },
                    label = { Text("Email") },
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

                Spacer(modifier = Modifier.height(120.dp))

                // Tombol "Login"
                Button(
                    onClick = {
                        // Validasi dan logika untuk login
                        if (email.value.isNotEmpty() && password.value.isNotEmpty()) {
                            // Arahkan ke halaman dashboard setelah login berhasil
                            navController.navigate("dashboard")
                        } else {
                            // Tampilkan pesan error
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp), // Tinggi tombol sesuai desain
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF469C8F))
                ) {
                    Text("Login", color = Color.White)
                }

                // Jika perlu, tambahkan tautan ke layar signup
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Don't have an account? Sign up",
                    modifier = Modifier.clickable {
                        navController.navigate("signup")
                    },
                    style = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray)
                )
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
                            navController.navigate("dashboard")
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
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "Welcome to your Dashboard!",
            style = MaterialTheme.typography.headlineMedium,
            color = Color(0xFF469C8F)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Contoh Card untuk informasi penting
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFE0F2F1))
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Your Recent Progress", style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(8.dp))
                Text("You have completed 3 tasks today!")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Contoh menu item
        Text(
            text = "Tasks",
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Black
        )
        Text(
            text = "Notes",
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Black
        )
        Text(
            text = "Deadlines",
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Black
        )
    }
}

// Preview untuk DashboardScreen
@Preview(showBackground = true)
@Composable
fun DashboardScreenPreview() {
    TBPTBTheme {
        DashboardScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun MainContentPreview() {
    TBPTBTheme {
        MainContent(navController = rememberNavController())
    }
}
