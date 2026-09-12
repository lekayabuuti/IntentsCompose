package br.edu.ifsp.scl.sc3047733.intentscompose.navigation

import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.edu.ifsp.scl.sc3047733.intentscompose.ui.screens.addword.AddWordScreen
import br.edu.ifsp.scl.sc3047733.intentscompose.ui.screens.home.HomeScreen

@Composable
fun NavGraph(navController: NavHostController = rememberNavController()) {

    var currentText by rememberSaveable { mutableStateOf("") }

    NavHost(navController = navController, startDestination = Routes.Home.route) {

        composable(Routes.Home.route) { entry ->
            val novaPalavra by entry.savedStateHandle
                .getStateFlow(Routes.RESULT_KEY, "")
                .collectAsState()

            LaunchedEffect(novaPalavra) {
                if (novaPalavra.isNotEmpty()) {
                    currentText = if (currentText.isEmpty()) {
                        novaPalavra
                    } else {
                        "$currentText $novaPalavra"
                    }
                    entry.savedStateHandle[Routes.RESULT_KEY] = ""
                }
            }

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