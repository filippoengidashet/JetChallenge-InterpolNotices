package com.filippoengidashet.challenge4.lloyds.ui.popups.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.filippoengidashet.challenge4.lloyds.domain.model.ThemeSettingType

@Composable
fun SettingsDialog(
    uiModel: SettingsUiModel = hiltViewModel(), onDismiss: () -> Unit,
) {

    val themeSetting = uiModel.themeSetting.collectAsStateWithLifecycle().value

    AlertDialog(
        properties = DialogProperties(usePlatformDefaultWidth = true),
        onDismissRequest = { onDismiss() },
        title = {
            Text(
                text = "App Settings",
                style = MaterialTheme.typography.titleLarge,
            )
        },
        text = {
            HorizontalDivider()
            Column(Modifier.verticalScroll(rememberScrollState())) {

                Text(
                    text = "Change Theme",
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.titleMedium
                )

                Column(Modifier.selectableGroup()) {
                    PreferenceItemComponent(
                        text = "System Theme",
                        selected = themeSetting == ThemeSettingType.SYSTEM,
                        onClick = {
                            uiModel.handleIntent(
                                SettingsUiIntent.UpdateTheme(ThemeSettingType.SYSTEM)
                            )
                        }
                    )

                    PreferenceItemComponent(
                        text = "Light Theme",
                        selected = themeSetting == ThemeSettingType.LIGHT,
                        onClick = {
                            uiModel.handleIntent(
                                SettingsUiIntent.UpdateTheme(ThemeSettingType.LIGHT)
                            )
                        }
                    )

                    PreferenceItemComponent(
                        text = "Dark Theme",
                        selected = themeSetting == ThemeSettingType.DARK,
                        onClick = {
                            uiModel.handleIntent(
                                SettingsUiIntent.UpdateTheme(ThemeSettingType.DARK)
                            )
                        }
                    )
                }

                HorizontalDivider(Modifier.padding(top = 8.dp))
            }
        },
        confirmButton = {
            Button(
                modifier = Modifier.padding(horizontal = 8.dp),
                onClick = onDismiss
            ) {
                Text(
                    text = "Dismiss",
                    style = MaterialTheme.typography.labelLarge,
                )
            }
        },
    )
}

@Composable
fun PreferenceItemComponent(
    text: String,
    selected: Boolean,
    onClick: () -> Unit,
) {
    Row(
        Modifier
            .fillMaxWidth()
            .selectable(
                selected = selected,
                role = Role.RadioButton,
                onClick = onClick,
            )
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        RadioButton(selected = selected, onClick = null)
        Spacer(Modifier.width(8.dp))
        Text(text)
    }
}
