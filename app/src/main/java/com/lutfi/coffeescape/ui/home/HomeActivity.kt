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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
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
import com.lutfi.coffeescape.common.UiState
import com.lutfi.coffeescape.data.api.response.DataUser
import com.lutfi.coffeescape.navigation.Screen
import com.lutfi.coffeescape.ui.ViewModelFactory
import com.lutfi.coffeescape.ui.component.BottomBar
import com.lutfi.coffeescape.ui.component.MyTopBar
import com.lutfi.coffeescape.ui.component.MyTopBar2
import com.lutfi.coffeescape.ui.component.Search
import com.lutfi.coffeescape.ui.component.Search2
import com.lutfi.coffeescape.ui.home.screen.detail.DetailCoffeeScreen
import com.lutfi.coffeescape.ui.home.screen.detail.DetailCoffeeViewModel
import com.lutfi.coffeescape.ui.home.screen.favorite.FavoriteScreen
import com.lutfi.coffeescape.ui.home.screen.favorite.FavoriteViewModel
import com.lutfi.coffeescape.ui.home.screen.home.HomeScreen
import com.lutfi.coffeescape.ui.home.screen.home.HomeScreenViewModel
import com.lutfi.coffeescape.ui.home.screen.mood.DetailMoodScreen
import com.lutfi.coffeescape.ui.home.screen.mood.DetailMoodViewModel
import com.lutfi.coffeescape.ui.home.screen.profile.ProfileScreen
import com.lutfi.coffeescape.ui.home.screen.profile.ProfileViewModel
import com.lutfi.coffeescape.ui.home.screen.search.SearchResultScreen
import com.lutfi.coffeescape.ui.home.screen.search.SearchResultViewModel
import com.lutfi.coffeescape.ui.landingpage.WelcomeActivity
import com.lutfi.jetcoffee.ui.theme.CoffeeScapeTheme

class HomeActivity : AppCompatActivity() {

    private val viewModel by viewModels<HomeViewModel> {
        ViewModelFactory.getInstance(this)
    }

    private val detailViewModel by viewModels<DetailCoffeeViewModel> {
        ViewModelFactory.getInstance(this)
    }

    private val favoriteViewModel by viewModels<FavoriteViewModel> {
        ViewModelFactory.getInstance(this)
    }

    private val homeScreenViewModel by viewModels<HomeScreenViewModel> {
        ViewModelFactory.getInstance(this)
    }

    private val profileViewModel by viewModels<ProfileViewModel> {
        ViewModelFactory.getInstance(this)
    }

    private val detailMoodViewModel by viewModels<DetailMoodViewModel> {
        ViewModelFactory.getInstance(this)
    }

    private val searchViewModel by viewModels<SearchResultViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        viewModel.getSession().observe(this) { user ->
            if (!user.isLogin) {
                startActivity(Intent(this, WelcomeActivity::class.java))
                finish()
            } else {
                viewModel.getUserProfile(user.id)
                viewModel.userProfile.observe(this) { dataUser ->
                    setContent {
                        CoffeeScapeTheme {
                            Surface {
                                CoffeeScapeApp(
                                    logout = { logout() },
                                    userData = dataUser,
                                    detailViewModel = detailViewModel,
                                    favoriteViewModel = favoriteViewModel,
                                    homeScreenViewModel = homeScreenViewModel,
                                    profileViewModel = profileViewModel,
                                    detailMoodViewModel = detailMoodViewModel,
                                    searchResultViewModel = searchViewModel,
                                )
                            }
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
    detailViewModel: DetailCoffeeViewModel,
    favoriteViewModel: FavoriteViewModel,
    homeScreenViewModel: HomeScreenViewModel,
    profileViewModel: ProfileViewModel,
    detailMoodViewModel: DetailMoodViewModel,
    searchResultViewModel: SearchResultViewModel,
    logout: () -> Unit,
    userData: DataUser,
) {
   val navBackStackEntry by navController.currentBackStackEntryAsState()
   val currentRoute = navBackStackEntry?.destination?.route
   
   Scaffold(
       topBar = {
                when (currentRoute) {
                    Screen.Favorite.route -> {
                        MyTopBar(text = stringResource(R.string.your_favorite_coffee))
                    }
                    Screen.Profile.route -> {
                        MyTopBar(text = stringResource(R.string.profile))
                    }
                    Screen.DetailCoffee.route -> {
                        MyTopBar2(
                            navController = navController,
                            text = stringResource(R.string.detail_coffee)
                        )
                    }
                    Screen.MoodCoffee.route -> {
                        MyTopBar2(
                            navController = navController,
                            text = stringResource(R.string.coffee_for_your_mood),
                        )
                    }
                }
       },
       bottomBar = {
           if (currentRoute != Screen.DetailCoffee.route && currentRoute != Screen.MoodCoffee.route && currentRoute != Screen.SearchResult.route) {
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
                   viewModel = homeScreenViewModel,
                   userId = userData.id,
                   navigateToDetail = { coffeeId ->
                       navController.navigate(Screen.DetailCoffee.createRoute(coffeeId))
                   },
                   navigateToDetailMood = { mood, icon ->
                       navController.navigate(Screen.MoodCoffee.createRoute(mood, icon))
                   },
                   navigateToSearchResult = { query ->
                       navController.navigate(Screen.SearchResult.withArg("query" to query))
                   }
               )
           }
           composable(Screen.Favorite.route) {
               FavoriteScreen(
                   navigateToDetail = { coffeeId ->
                       navController.navigate(Screen.DetailCoffee.createRoute(coffeeId))
                   },
                   userId = userData.id,
                   viewModel = favoriteViewModel,
               )

           }
           composable(Screen.Profile.route) {
               ProfileScreen(
                   name = userData.name,
                   email = userData.email,
                   logout = {
                       logout()
                   },
                   viewModel = profileViewModel,
                   navigateToDetail = { coffeeId ->
                       navController.navigate(Screen.DetailCoffee.createRoute(coffeeId))
                   },
               )
           }
           composable(
               route = Screen.DetailCoffee.route,
               arguments = listOf(navArgument("coffeeId") { type = NavType.StringType })
           ) {
               val id = it.arguments?.getString("coffeeId") ?: ""
               DetailCoffeeScreen(
                   userId = userData.id,
                   coffeeId = id,
                   viewModel = detailViewModel,
               )
           }
           composable(
               route = Screen.MoodCoffee.route,
               arguments = listOf(
                   navArgument("mood") { type = NavType.StringType },
                   navArgument("icon") { type = NavType.IntType })
           ) {
               val moodType = it.arguments?.getString("mood") ?: ""
               val icon = it.arguments?.getInt("icon") ?: 0
               DetailMoodScreen(
                   userName = userData.name,
                   context = LocalContext.current,
                   moodType = moodType,
                   icon = icon,
                   detailMoodViewModel = detailMoodViewModel,
                   navigateToDetail = { coffeeId ->
                       navController.navigate(Screen.DetailCoffee.createRoute(coffeeId))
                   },
               )
           }
           composable(Screen.SearchResult.route) { navBackStackEntry ->
               val query = navBackStackEntry.arguments?.getString("query") ?: ""
               SearchResultScreen(
                   query = query,
                   viewModel = searchResultViewModel,
                   navigateToDetail = { coffeeId ->
                       navController.navigate(Screen.DetailCoffee.createRoute(coffeeId))
                   },
                   navigationBack = navController
               )
           }
       }
   } 
}

