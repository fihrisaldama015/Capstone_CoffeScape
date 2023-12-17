package com.lutfi.coffeescape.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.lutfi.coffeescape.R
import com.lutfi.coffeescape.navigation.Screen
import com.lutfi.coffeescape.ui.ViewModelFactory
import com.lutfi.coffeescape.ui.component.BottomBar
import com.lutfi.coffeescape.ui.component.Search
import com.lutfi.coffeescape.ui.home.screen.favorite.FavoriteScreen
import com.lutfi.coffeescape.ui.home.screen.home.HomeScreen
import com.lutfi.coffeescape.ui.home.screen.profile.ProfileScreen
import com.lutfi.coffeescape.ui.landingpage.WelcomeActivity
import com.lutfi.jetcoffee.ui.theme.CoffeeScapeTheme

class HomeActivity : AppCompatActivity() {

    private val viewModel by viewModels<HomeViewModel> {
        ViewModelFactory.getInstance(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.getSession().observe(this) { user ->
            if (!user.isLogin) {
                startActivity(Intent(this, WelcomeActivity::class.java))
                finish()
            } else {
                setContent {
                    CoffeeScapeTheme {
                        Surface {
                            CoffeeScapeApp( logout = { logout() })
                        }
                    }
                }
            }
        }
    }

    private fun logout() {
        viewModel.logout()
    }
}

@Composable
fun CoffeeScapeApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    logout: () -> Unit,
) {
   val navBackStackEntry by navController.currentBackStackEntryAsState()
   val currentRoute = navBackStackEntry?.destination?.route
   
   Scaffold(
       bottomBar = {
           if (currentRoute != Screen.DetailCoffee.route && currentRoute != Screen.MoodCoffee.route) {
               BottomBar(navController)
           }
       },
       modifier = modifier,
   ) { innerPadding ->
       NavHost(
           navController = navController,
           startDestination = Screen.Home.route,
           modifier = Modifier.padding(innerPadding)
       ) {
           composable(Screen.Home.route) {
               HomeScreen(

               )
           }
           composable(Screen.Favorite.route) {
               FavoriteScreen()

           }
           composable(Screen.Profile.route) {
               ProfileScreen(
                   logout = {
                       logout()
                   }
               )
           }
           composable(
               route = Screen.DetailCoffee.route,
               arguments = listOf(navArgument("coffeeId") { type = NavType.LongType })
           ) {
               val id = it.arguments?.getLong("coffeeId") ?: -1L

           }
           composable(
               route = Screen.MoodCoffee.route,
               arguments = listOf(navArgument("mood") { type = NavType.StringType })
           ) {
               val id = it.arguments?.getLong("mood") ?: ""

           }
       }
   } 
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun CoffeeScapePreview() {
    CoffeeScapeTheme {
        CoffeeScapeApp(logout = {})
    }
}

