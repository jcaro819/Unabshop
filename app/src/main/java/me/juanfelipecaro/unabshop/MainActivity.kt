package me.juanfelipecaro.unabshop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import me.juanfelipecaro.unabshop.ui.theme.UnabShopTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val navController = rememberNavController()
            var startDestination = "Login"

            val auth = Firebase.auth
            val currentUser = auth.currentUser

            if (currentUser != null) {
                startDestination = "home"
            } else {
                startDestination = "Login"
            }

            NavHost(navController, startDestination) {
                composable(route = "Login") {
                    LoginScreen(onClickRegister = {
                        navController.navigate("register")
                    }, onSuccessfulLogin = {
                        navController.navigate("home") {
                            popUpTo("Login") { inclusive = true }
                        }
                    }
                    )
                }
                composable(route = "register") {
                    RegisterScreen(onClickBack = {
                        navController.popBackStack()
                    }, onSuccessfulRegister = {
                        navController.navigate("home") {
                            popUpTo(0)
                        }
                    })
                }
                composable(route = "home") {
                    HomeScreen()
                }
                composable("home") {
                    HomeScreen(onClickLogout = {
                        navController.navigate("Login") {
                            popUpTo(0)
                        }
                    })
                }
            }
        }
    }
}