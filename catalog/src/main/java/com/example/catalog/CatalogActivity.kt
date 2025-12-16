package com.example.catalog

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.designsystem.AppTheme // from your design-system module
import com.example.catalog.screens.ComponentListScreen
import com.example.catalog.screens.HomeShowcaseScreen
import com.example.catalog.screens.ProfileShowcaseScreen
import com.example.feature.routineElements.ElementListRoute
import com.example.feature.simpleAddition.AdditionRoute
import dagger.hilt.android.AndroidEntryPoint


/** purpose of this file:
 * Sets up a Compose environment, applies your Compose design-system theme, and wires simple
 * in-app navigation between showcase screens.
 * It launches the catalog activity, nothing else!
 */

@AndroidEntryPoint
class CatalogActivity : ComponentActivity() {

    // onCreate: starts the lifecycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // attaches a (later) defined UI hierarchy to the activity
        setContent {
            // App theme wraps around the content, ensuring unity in style
            AppTheme {
                // Surface provides the Background using MaterialThemes color scheme
                // MaterialTheme is defined in Theme.kt
                Surface(color = MaterialTheme.colorScheme.background) {
                    // Renders the catalogs navigation graph
                    CatalogNav()
                }
            }
        }
    }
}

@Composable
private fun CatalogNav() {
    // navController keeps track of the "Stack" of destinations to remember
    val navController = rememberNavController()
    // NavHost defines start and destination of the stack. startDestination = list
    // list will be the composable later...
    NavHost(navController, startDestination = "list") {
        // composable(list) {ComponentListScreen(...)} defines the starting location

        composable("home") // this is the destination "home" set to HomeShowcaseScreen
        { HomeShowcaseScreen() }
        composable("profile") // this is the destination "profile" set to ProfileShowcaseScreen
        { ProfileShowcaseScreen() }
        composable("routines")

        { AdditionRoute( navController = navController) }
        composable("list") // as defined previously, this is our starting point!
        { ComponentListScreen(
            onHomeClick = { navController.navigate("home") },
            onProfileClick = { navController.navigate("profile") },
            onAdditionClick = { navController.navigate( "addition" )}
        )
        }
    }
}