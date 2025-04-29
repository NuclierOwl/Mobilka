package com.example.mobilka_krosovok.ui.Screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mobilka_krosovok.R
import com.example.mobilka_krosovok.ui.theme.MobilkakrosovokTheme
import com.example.mobilka_krosovok.ui.Screens.auth.ForgotPasswordScreen
import com.example.mobilka_krosovok.ui.Screens.auth.RegistrationScreen
import com.example.mobilka_krosovok.ui.Screens.auth.SignInScreen
import com.example.mobilka_krosovok.ui.Screens.home.HomeScreen
import com.example.mobilka_krosovok.ui.Screens.onboarding.FirstScreen
import com.example.mobilka_krosovok.ui.Screens.onboarding.SlideScreen
import com.example.mobilka_krosovok.ui.Screens.products.PopularScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MobilkakrosovokTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = Screen.FirstScreen.route
                ) {
                    composable(Screen.FirstScreen.route) {
                        Screen.FirstScreen(
                            onGetStartedClick = {
                                navController.navigate(Screen.SlideScreen.route) {
                                    popUpTo(Screen.FirstScreen.route) { inclusive = true }
                                }
                            }
                        )
                    }

                    composable(Screen.SlideScreen.route) {
                        SlideScreen(
                            onContinueClick = {
                                navController.navigate(Screen.SignIn.route) {
                                    popUpTo(Screen.SlideScreen.route) { inclusive = true }
                                }
                            }
                        )
                    }

                    composable(Screen.SignIn.route) {
                        SignInScreen(
                            onSignInClick = { email, password ->
                                // Здесь должна быть логика авторизации
                                navController.navigate(Screen.Home.route) {
                                    popUpTo(Screen.SignIn.route) { inclusive = true }
                                }
                            },
                            onForgotPasswordClick = {
                                navController.navigate(Screen.ForgotPass.route)
                            },
                            onRegisterClick = {
                                navController.navigate(Screen.Registration.route)
                            }
                        )
                    }

                    composable(Screen.ForgotPass.route) {
                        ForgotPasswordScreen(
                            onSendClick = { email ->
                                // Логика восстановления пароля
                                navController.popBackStack()
                            },
                            onBackClick = {
                                navController.popBackStack()
                            }
                        )
                    }

                    composable(Screen.Registration.route) {
                        RegistrationScreen(
                            onRegisterClick = { name, email, password ->
                                // Логика регистрации
                                navController.navigate(Screen.Home.route) {
                                    popUpTo(Screen.Registration.route) { inclusive = true }
                                }
                            },
                            onLoginClick = {
                                navController.popBackStack()
                            }
                        )
                    }

                    composable(Screen.Home.route) {
                        HomeScreen(
                            onPopularClick = {
                                navController.navigate(Screen.Popular.route)
                            }
                        )
                    }

                    composable(Screen.Popular.route) {
                        PopularScreen(
                            onBackClick = {
                                navController.popBackStack()
                            }
                        )
                    }
                }
            }
        }
    }
}

sealed class Screen(val route: String) {
    object FirstScreen : Screen("first_screen")
    object SlideScreen : Screen("slide_screen")
    object SignIn : Screen("sign_in")
    object ForgotPass : Screen("forgot_pass")
    object Registration : Screen("registration")
    object Home : Screen("home")
    object Popular : Screen("popular")
}