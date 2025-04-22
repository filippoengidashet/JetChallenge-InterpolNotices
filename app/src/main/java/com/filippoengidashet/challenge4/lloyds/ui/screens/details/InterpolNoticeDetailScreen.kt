package com.filippoengidashet.challenge4.lloyds.ui.screens.details

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.filippoengidashet.challenge4.lloyds.common.Utils
import com.filippoengidashet.challenge4.lloyds.common.orElse
import com.filippoengidashet.challenge4.lloyds.data.model.GenderType
import com.filippoengidashet.challenge4.lloyds.ui.components.BulletItem
import com.filippoengidashet.challenge4.lloyds.ui.components.DialogBox
import com.filippoengidashet.challenge4.lloyds.ui.components.ImagePager
import com.filippoengidashet.challenge4.lloyds.ui.components.DetailSectionComponent
import com.filippoengidashet.challenge4.lloyds.ui.components.PagerIndicator
import com.filippoengidashet.challenge4.lloyds.ui.components.ZoomableBox

@Composable
fun InterpolNoticeDetailScreen(
    id: String,
    snackbarHostState: SnackbarHostState,
    viewModel: InterpolNoticeDetailUiModel = hiltViewModel(),
) {

    Utils.log("DetailScreen recomposed")

    DisposableEffect(Unit) {

        viewModel.handleIntent(InterpolNoticeDetailUiIntent.LoadAction(id))

        onDispose {
            //Maybe useful to cleanup references after leaving the composable screen
            //Can be replaced with LaunchedEffect if not required
        }
    }

    val state = viewModel.uiState.collectAsStateWithLifecycle().value
    val noticeImages = state.images?.map { it.url } ?: emptyList()

    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {

        DialogBox(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            onDismiss = { showDialog = false },
        ) {
            val pagerState = rememberPagerState { noticeImages.size }

            ZoomableBox {

                ImagePager(
                    modifier = Modifier.fillMaxSize(),
                    pagerState = pagerState,
                    images = noticeImages
                )
            }

            PagerIndicator(
                pageCount = noticeImages.size,
                currentPageIndex = pagerState.currentPage,
                modifier = Modifier.align(Alignment.BottomCenter)
            )

            IconButton(
                onClick = {
                    showDialog = false
                }, modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(12.dp)
                    .clip(CircleShape)
                    .background(Color(0x42000000))
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close",
                    tint = Color.White
                )
            }
        }
    }

    val errorState = state.error

    if (errorState != null) {

        Utils.log("DetailScreen error")

        LaunchedEffect(errorState.id) {
            val result = snackbarHostState.showSnackbar(
                errorState.message.orElse("Something went wrong"),
                actionLabel = "Retry",
                duration = SnackbarDuration.Indefinite
            )
            if (result == SnackbarResult.ActionPerformed) {
                viewModel.handleIntent(InterpolNoticeDetailUiIntent.ReLoadAction(id))
            }
            snackbarHostState.currentSnackbarData?.dismiss()
        }
    }

    Surface {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {

            Column(
                modifier = Modifier.verticalScroll(rememberScrollState())
            ) {

                Box(
                    modifier = Modifier.clip(RoundedCornerShape(6.dp))
                ) {
                    val pagerState = rememberPagerState { noticeImages.size }

                    ImagePager(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(250.dp)
                            .clickable {
                                showDialog = true
                            },
                        pagerState = pagerState,
                        images = noticeImages
                    )

                    PagerIndicator(
                        pageCount = noticeImages.size,
                        currentPageIndex = pagerState.currentPage,
                        modifier = Modifier.align(Alignment.BottomCenter)
                    )
                }

                if (state.notice != null) {

                    val name = state.notice.name
                    val forename = state.notice.forename
                    val gender = state.notice.gender
                    val dateOfBirth = state.notice.date_of_birth
                    val distinguishingMarks = state.notice.distinguishing_marks
                    val placeOfBirth = state.notice.place_of_birth
                    val nationalities = state.notice.nationalities

                    Spacer(Modifier.height(8.dp))
                    DetailSectionComponent(
                        title = "Identity particulars"
                    ) {

                        if (name.isNotEmpty()) {
                            BulletItem("Family name: $name")
                        }

                        if (forename.isNotEmpty()) {
                            BulletItem("Forename: $forename")
                        }

                        if (gender.isNotEmpty()) {
                            BulletItem("Gender: " + GenderType.from(gender))
                        }

                        if (dateOfBirth.isNotEmpty()) {
                            BulletItem("Date of birth: $dateOfBirth (${state.notice.age} years old)")
                        }

                        if (distinguishingMarks.isNotEmpty()) {
                            BulletItem("Distinguishing marks and characteristics: $distinguishingMarks")
                        }

                        if (placeOfBirth.isNotEmpty()) {
                            BulletItem("Place of birth: $placeOfBirth")
                        }

                        if (nationalities.isNotEmpty()) {
                            BulletItem("Nationality: ${nationalities.joinToString(",")}")
                        }
                    }

                    val height = state.notice.height
                    val weight = state.notice.weight
                    val hairColors = state.notice.hair_colors
                    val eyeColors = state.notice.eyes_colors

                    if (height.isNotEmpty()
                        || weight.isNotEmpty()
                        || hairColors.isNotEmpty()
                        || eyeColors.isNotEmpty()
                    ) {

                        Spacer(Modifier.height(8.dp))
                        DetailSectionComponent(
                            title = "Physical description",
                        ) {

                            if (height.isNotEmpty()) {
                                BulletItem("Height: $height metres")
                            }

                            if (weight.isNotEmpty()) {
                                BulletItem("Weight: $weight kilograms")
                            }

                            if (hairColors.isNotEmpty()) {
                                BulletItem("Colour of hair: ${hairColors.joinToString(",")}")
                            }

                            if (eyeColors.isNotEmpty()) {
                                BulletItem("Colour of eyes: ${eyeColors.joinToString(",")}")
                            }
                        }
                    }

                    val languagesSpokenIds = state.notice.languages_spoken_ids

                    if (languagesSpokenIds.isNotEmpty()) {
                        Spacer(Modifier.height(8.dp))
                        DetailSectionComponent(
                            title = "Details",
                        ) {

                            if (languagesSpokenIds.isNotEmpty()) {
                                BulletItem("Languages spoken: ${languagesSpokenIds.joinToString(",")}")
                            }
                        }
                    }

                    val arrestWarrants = state.notice.arrest_warrants

                    if (arrestWarrants.isNotEmpty()) {
                        Spacer(Modifier.height(8.dp))
                        DetailSectionComponent(
                            title = "Charges",
                            subTitle = "Published as provided by requesting entity"
                        ) {
                            for (warrant in arrestWarrants) {
                                BulletItem(warrant)
                            }
                        }
                    }
                }
            }

            if (state.isLoading) {
                Utils.log("DetailScreen isLoading")
                CircularProgressIndicator(
                    color = Color.Green,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}
