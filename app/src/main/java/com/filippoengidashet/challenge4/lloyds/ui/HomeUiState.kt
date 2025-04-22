package com.filippoengidashet.challenge4.lloyds.ui

import android.content.Context
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope

@Composable
fun rememberHomeUiState(
    context: Context = LocalContext.current,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController(),
    snackbarHostState: SnackbarHostState = SnackbarHostState(),
) = remember(context, coroutineScope, navController, snackbarHostState) {
    HomeUiState(
        context = context,
        scope = coroutineScope,
        navController = navController,
        snackbarHostState = snackbarHostState,
    )
}

class HomeUiState(
    val context: Context,
    val scope: CoroutineScope,
    val navController: NavHostController,
    val snackbarHostState: SnackbarHostState,
) {

    fun canGoBack() = navController.previousBackStackEntry != null
    fun goBack() = navController.popBackStack()
}

sealed class Screen(val route: String) {

    object InterpolNoticeList : Screen(route = "list")

    object InterpolNoticeDetail : Screen(route = "detail/{id}/{name}") {

        fun createNavRoute(id: String, name: String) = "detail/$id/$name"
    }
}
