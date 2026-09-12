package br.edu.ifsp.scl.sc3047733.intentscompose.navigation

import android.net.Uri

sealed class Routes(val route: String) {

    object Home : Routes("home")

    object AddWord : Routes("addWord/{currentText}") {
        fun createRoute(currentText: String): String {
            val encoded = Uri.encode(currentText)
            return "addWord/$encoded"
        }
    }

    companion object {
        const val RESULT_KEY = "newWord"
    }
}