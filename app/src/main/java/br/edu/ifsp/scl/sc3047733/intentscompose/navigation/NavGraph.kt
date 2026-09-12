package br.edu.ifsp.scl.sc3047733.intentscompose.navigation

import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import br.edu.ifsp.scl.sc3047733.intentscompose.ui.screens.addword.AddWordScreen
import br.edu.ifsp.scl.sc3047733.intentscompose.ui.screens.home.HomeScreen

@Composable
fun NavGraph(navController: NavHostController = rememberNavController()) {

    var currentText by rememberSaveable { mutableStateOf("") }

    val backStackEntry by navController.currentBackStackEntryAsState()

    LaunchedEffect(backStackEntry) {
        val savedStateHandle = backStackEntry?.savedStateHandle
        val novaPalavra = savedStateHandle?.get<String>(Routes.RESULT_KEY)

        if (novaPalavra != null) {
            currentText = if (currentText.isEmpty()) {
                novaPalavra
            } else {
                "$currentText $novaPalavra"
            }
            savedStateHandle.remove<String>(Routes.RESULT_KEY)
        }
    }

    NavHost(navController = navController, startDestination = Routes.Home.route) {

        composable(Routes.Home.route) {
            HomeScreen(
                currentText = currentText,
                onAddWordClick = {
                    navController.navigate(Routes.AddWord.createRoute(currentText))
                },
                onResetClick = {
                    currentText = ""
                }
            )
        }

        composable(Routes.AddWord.route) { entry ->
            val currentTextArg = entry.arguments?.getString("currentText").orEmpty()

            AddWordScreen(
                currentText = currentTextArg,
                onConcatenarClick = { novaPalavra ->
                    navController.previousBackStackEntry
                        ?.savedStateHandle
                        ?.set(Routes.RESULT_KEY, novaPalavra)
                    navController.popBackStack()
                }
            )
        }
    }
}