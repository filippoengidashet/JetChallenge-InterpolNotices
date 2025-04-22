package com.filippoengidashet.challenge4.lloyds.ui.screens.list

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.filippoengidashet.challenge4.lloyds.common.Utils
import com.filippoengidashet.challenge4.lloyds.common.orElse
import com.filippoengidashet.challenge4.lloyds.domain.model.InterpolNotice
import com.filippoengidashet.challenge4.lloyds.ui.components.ErrorMessageComponent
import com.filippoengidashet.challenge4.lloyds.ui.components.HomeListItem
import com.filippoengidashet.challenge4.lloyds.ui.components.ScrollToTopButton
import com.filippoengidashet.challenge4.lloyds.ui.components.isScrollingUp
import com.filippoengidashet.challenge4.lloyds.ui.popups.search.SearchDialog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun InterpolNoticeListScreen(
    scope: CoroutineScope,
    showSearchPopup: Boolean,
    onDismiss: () -> Unit,
    snackbarHostState: SnackbarHostState,
    viewModel: InterpolNoticeListUiModel = hiltViewModel(),
    onClick: (InterpolNotice) -> Unit,
) {

    Utils.log("HomeScreen recomposed")

    //Handle countries data loading
    DisposableEffect(Unit) {

        viewModel.handleIntent(InterpolNoticeListUiIntent.LoadCountriesAction)

        onDispose {
            //Maybe useful to cleanup references after leaving the composable screen
            //Can be replaced with LaunchedEffect if not required
        }
    }

    val countryListUiState = viewModel.countryListState.collectAsStateWithLifecycle().value

    val errorState = countryListUiState.error

    if (errorState != null) {

        Utils.log("HomeScreen error")

        LaunchedEffect(errorState.id) {
            val result = snackbarHostState.showSnackbar(
                errorState.message.orElse("Something went wrong"),
                actionLabel = "Retry",
                duration = SnackbarDuration.Indefinite
            )
            if (result == SnackbarResult.ActionPerformed) {
                viewModel.handleIntent(InterpolNoticeListUiIntent.ReLoadCountriesAction)
            }
            snackbarHostState.currentSnackbarData?.dismiss()
        }
    }

    val searchQuery = viewModel.searchQuery.collectAsStateWithLifecycle()

    //Handle search popup
    if (showSearchPopup) {
        SearchDialog(
            scope = scope,
            query = searchQuery.value
        ) { query ->
            query?.let {
                viewModel.handleIntent(
                    InterpolNoticeListUiIntent.UpdateSearchQuery(it)
                )
            }
            onDismiss.invoke()
        }
    }

    //Handle notices list
    val lazyListState = rememberLazyListState()
    val lazyPagingItems = viewModel.pagingData.collectAsLazyPagingItems()
    val loadState = lazyPagingItems.loadState

    val appendState = loadState.append
    val prependState = loadState.prepend
    val refreshState = loadState.refresh

    Surface {
        Box(modifier = Modifier.fillMaxSize()) {

            Utils.log("HomeScreen users-lazy-column")

            LazyColumn(
                userScrollEnabled = true,
                modifier = Modifier.padding(8.dp),
                state = lazyListState,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                val itemCount = lazyPagingItems.itemCount

                items(
                    count = itemCount,
                    key = lazyPagingItems.itemKey { it.entity_id }
                ) { index ->
                    val position = index + 1
                    lazyPagingItems[index]?.let { item ->
                        Utils.log("HomeScreen item $index")

                        val countries = viewModel.getCountries(item.nationalities)

                        HomeListItem(
                            position,
                            item,
                            countries,
                            onClick = { onClick.invoke(item) }
                        )
                    }
                }

                if (itemCount == 0 && refreshState is LoadState.NotLoading) {
                    item {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(text = "No data found with query: ${searchQuery.value}")
                        }
                    }
                }

                // Show error message if there's an error in the initial load
                if (refreshState is LoadState.Error) {
                    val refreshErrorMessage = refreshState.error.localizedMessage
                    item {
                        ErrorMessageComponent("Error loading items: $refreshErrorMessage") {
                            lazyPagingItems.retry()
                        }
                    }
                }
                // Show error message if there's an error in the append load
                if (appendState is LoadState.Error) {
                    val appendErrorMessage = appendState.error.localizedMessage
                    item {
                        ErrorMessageComponent("Error loading items: $appendErrorMessage") {
                            lazyPagingItems.retry()
                        }
                    }
                }
            }

            if (prependState is LoadState.Loading
                || refreshState is LoadState.Loading
                || appendState is LoadState.Loading
            ) {
                Utils.log("HomeScreen isLoading")
                CircularProgressIndicator(
                    color = Color.Green,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            AnimatedVisibility(
                visible = lazyListState.isScrollingUp(),
                enter = fadeIn(),
                exit = fadeOut(),
                modifier = Modifier.align(Alignment.BottomCenter)
            ) {
                ScrollToTopButton(
                    onClick = {
                        scope.launch {
                            lazyListState.scrollToItem(0)
                        }
                    })
            }
        }
    }
}
