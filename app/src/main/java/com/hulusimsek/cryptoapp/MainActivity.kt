package com.hulusimsek.cryptoapp

import CryptoDetailScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.hulusimsek.cryptoapp.ui.theme.CryptoAppTheme
import com.hulusimsek.cryptoapp.view.CryptoListScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CryptoAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "crypto_list_screen") {
                        composable("crypto_list_screen") {
                            CryptoListScreen(navController, modifier = Modifier.padding(innerPadding))
                        }

                        composable("crypto_detail_screen/{cryptoId}/{cryptoPrice}", arguments = listOf(
                            navArgument("cryptoId") {type = NavType.StringType},
                            navArgument("cryptoPrice") {type = NavType.StringType}

                        )) {
                            val cryptoId = remember{
                                it.arguments?.getString("cryptoId")
                            }
                            val cryptoPrice = remember{
                                it.arguments?.getString("cryptoPrice")
                            }
                            CryptoDetailScreen(id = cryptoId ?: "", price = cryptoPrice ?: "", navController = navController, modifier = Modifier.padding(innerPadding) )

                        }


                    }
                }
            }
        }
    }

}
