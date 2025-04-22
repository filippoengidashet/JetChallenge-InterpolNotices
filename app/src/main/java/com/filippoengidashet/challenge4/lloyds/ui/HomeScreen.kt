package com.filippoengidashet.challenge4.lloyds.ui

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import com.filippoengidashet.challenge4.lloyds.R
import com.filippoengidashet.challenge4.lloyds.common.orElse
import com.filippoengidashet.challenge4.lloyds.ui.components.TopNavBarComponent
import com.filippoengidashet.challenge4.lloyds.ui.popups.settings.SettingsDialog
import com.filippoengidashet.challenge4.lloyds.ui.screens.details.InterpolNoticeDetailScreen
import com.filippoengidashet.challenge4.lloyds.ui.screens.list.InterpolNoticeListScreen

@Composable
fun HomeScreen(uiState: HomeUiState = rememberHomeUiState()) {

    val scope = uiState.scope
    val navController = uiState.navController
    val snackbarHostState = uiState.snackbarHostState

    val currentBackStackEntry = navController.currentBackStackEntryAsState().value

    val isTopLevelRoute =
        currentBackStackEntry?.destination?.route == Screen.InterpolNoticeList.route

    var showSettingsPopup by rememberSaveable { mutableStateOf(false) }
    var showSearchPopup by rememberSaveable { mutableStateOf(false) }

    if (showSettingsPopup) {
        SettingsDialog {
            showSettingsPopup = false
        }
    }

    Scaffold(
        topBar = {
            TopNavBarComponent(
                title = {
                    val title = if (isTopLevelRoute) {
                        "Red Interpol Notices"
                    } else {
                        currentBackStackEntry?.arguments?.getString("name").orElse("Unknown")
                    }
                    Text(
                        text = title,
                        color = Color.White
                    )
                },
                icon = {
                    if (isTopLevelRoute) {
                        IconButton(onClick = {}) {
                            Icon(
                                painter = painterResource(R.drawable.ic_interpol_logo),
                                contentDescription = null,
                                tint = Color.Unspecified
                            )
                        }
                    } else {
                        IconButton(onClick = {

                            if (uiState.canGoBack()) {
                                uiState.goBack()
                            }

                        }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Default.ArrowBack,
                                contentDescription = "ArrowBack",
                                tint = Color.White
                            )
                        }
                    }
                },
                actions = {

                    if (isTopLevelRoute) {
                        IconButton(onClick = {
                            showSearchPopup = true
                        }) {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "Search",
                                tint = Color.White
                            )
                        }
                    }

                    IconButton(onClick = {
                        showSettingsPopup = true
                    }) {
                        Icon(
                            painter = painterResource(R.drawable.ic_contrast),
                            contentDescription = "Change Theme",
                            tint = Color.White
                        )
                    }
                }
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.InterpolNoticeList.route,
            enterTransition = { fadeIn(animationSpec = tween(500)) },
            exitTransition = { ExitTransition.None },
            popEnterTransition = { EnterTransition.None },
            popExitTransition = { fadeOut(animationSpec = tween(500)) },
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            composable(route = Screen.InterpolNoticeList.route) {
                InterpolNoticeListScreen(
                    scope = scope,
                    showSearchPopup = showSearchPopup,
                    snackbarHostState = snackbarHostState,
                    onDismiss = {
                        showSearchPopup = false
                    }
                ) { item ->
                    val navRoute = Screen.InterpolNoticeDetail.createNavRoute(
                        id = item.entity_id.replace("/", "-"),
                        name = item.name
                    )
                    navController.navigate(navRoute)
                }
            }
            composable(
                route = Screen.InterpolNoticeDetail.route,
                arguments = listOf(
                    navArgument("id") { type = NavType.StringType },
                    navArgument("name") { type = NavType.StringType },
                )
            ) { backStackEntry ->

                val arguments = backStackEntry.arguments

                val id = arguments?.getString("id").orElse("")

                InterpolNoticeDetailScreen(id, snackbarHostState)
            }
        }
    }
}
