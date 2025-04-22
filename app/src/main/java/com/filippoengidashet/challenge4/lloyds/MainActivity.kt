package com.filippoengidashet.challenge4.lloyds

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import com.filippoengidashet.challenge4.lloyds.common.isNightMode
import com.filippoengidashet.challenge4.lloyds.domain.model.StorageDao
import com.filippoengidashet.challenge4.lloyds.domain.model.ThemeSettingType
import com.filippoengidashet.challenge4.lloyds.ui.HomeScreen
import com.filippoengidashet.challenge4.lloyds.ui.theme.LloydsTechChallengeTheme
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        installSplashScreen()

        setContent {

            val mainUiModel = hiltViewModel<MainUiModel>()
            val themeSetting = mainUiModel.themeSetting.collectAsStateWithLifecycle()

            val isNightMode = when (themeSetting.value) {
                ThemeSettingType.LIGHT -> false
                ThemeSettingType.DARK -> true
//                ThemeSettingType.SYSTEM -> isSystemInDarkTheme()
                ThemeSettingType.SYSTEM -> resources.configuration.isNightMode()
            }

            LloydsTechChallengeTheme(darkTheme = isNightMode) {
                HomeScreen()
            }
        }
    }
}

@HiltViewModel
class MainUiModel @Inject constructor(
    private val storageDao: StorageDao,
) : ViewModel() {

    val themeSetting = storageDao.getSavedThemeSetting()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = ThemeSettingType.SYSTEM
        )
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LloydsTechChallengeTheme {
        HomeScreen()
    }
}
