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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tbptb.ui.theme.TBPTBTheme
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TBPTBTheme {
                val navController = rememberNavController()

                // Menetapkan layar awal ke "splash"
                NavHost(navController = navController, startDestination = "splash") {
                    composable("splash") { SplashScreen(navController) }
                    composable("main") { MainContent(navController) }
                    composable("login") { LoginScreen() }
                    composable("signup") { SignUpScreen() }
                }
            }
        }
    }
}

@Composable
fun SplashScreen(navController: NavController) {
    LaunchedEffect(Unit) {
        delay(2000) // Splash screen ditampilkan selama 2 detik
        navController.navigate("main") { // Navigasi ke main setelah 2 detik
            popUpTo("splash") { inclusive = true } // Menghapus splash dari back stack agar tidak bisa kembali ke splash
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF469C8F)), // Warna hijau latar belakang
        contentAlignment = Alignment.Center
    ) {
        Image(painter = painterResource(id = R.drawable.logo_ur_app), contentDescription = "Logo")
    }
}

@Composable
fun MainContent(navController: NavController, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize() // Memastikan column mengisi seluruh layar
            .padding(16.dp)
            .background(Color(0xFF469C8F)), // Warna hijau untuk latar belakang
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Gambar buku
        Image(painter = painterResource(id = R.drawable.buku), contentDescription = "Book Image")

        Spacer(modifier = Modifier.height(32.dp)) // Memberi jarak antara gambar dan tombol

        // Tombol Login dengan warna putih dan teks hitam
        Button(
            onClick = { navController.navigate("login") },
            colors = ButtonDefaults.buttonColors(containerColor = Color.White) // Mengubah warna tombol jadi putih
        ) {
            Text("Login", color = Color.Black) // Mengubah warna teks tombol jadi hitam
        }

        Spacer(modifier = Modifier.height(16.dp)) // Memberi jarak antara tombol dan teks

        // Tombol SignUp
        TextButton(onClick = { navController.navigate("signup") }) {
            Text("Don't have an account? Sign Up", color = Color.White) // Mengubah warna teks menjadi putih
        }
    }
}

@Composable
fun LoginScreen() {
    // Konten tampilan Login
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Login Screen")
        // Tambahkan formulir login di sini
    }
}

@Composable
fun SignUpScreen() {
    // Konten tampilan Sign Up
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Sign Up Screen")
        // Tambahkan formulir signup di sini
    }
}

@Preview(showBackground = true)
@Composable
fun MainContentPreview() {
    TBPTBTheme {
        MainContent(navController = rememberNavController())
    }
}
