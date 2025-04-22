package com.ggn.myapplication.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ggn.myapplication.ui.login.LoginPage
import com.ggn.myapplication.ui.login.LoginViewModel
import com.ggn.myapplication.ui.theme.StarterKitTheme
import com.ggn.myapplication.utils.navigator.Screen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StarterKitTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}


@Composable
fun StartLoginJourney() {
    val loginViewModel : LoginViewModel = hiltViewModel()
    val navController = rememberNavController()
    NavHost(navController, startDestination = Screen.Login.route) {
        composable(Screen.Login.route) {
            LoginPage(navController,loginViewModel)
        }
        composable(Screen.Signup.route) {
            LoginPage(navController,loginViewModel)
        }
        composable(Screen.ForgotPassword.route) {
            LoginPage(navController,loginViewModel)
        }
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    StartLoginJourney()
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    StarterKitTheme {
        Greeting("Android")
    }
}