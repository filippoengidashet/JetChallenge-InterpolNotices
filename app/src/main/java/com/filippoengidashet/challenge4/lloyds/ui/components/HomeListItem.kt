package com.filippoengidashet.challenge4.lloyds.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.filippoengidashet.challenge4.lloyds.common.Utils
import com.filippoengidashet.challenge4.lloyds.domain.model.CountryCode
import com.filippoengidashet.challenge4.lloyds.domain.model.InterpolNotice
import kotlin.String

@Composable
fun HomeListItem(
    position: Int,
    item: InterpolNotice,
    countries: List<CountryCode>,
    onClick: () -> Unit
) {

    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
    ) {
        Box {

            Row {

                ImageComponent(
//                url = item.images.first().original,
                    url = item.thumbnail_href,
                    modifier = Modifier
                        .size(128.dp)
                        .padding(8.dp)
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = 8.dp,
                            end = 8.dp,
                            bottom = 8.dp
                        ),
                    verticalArrangement = Arrangement.spacedBy(5.dp),
                ) {

                    Text(
                        text = item.name,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "Forename: " + Utils.capitalizeFirstLetter(item.forename),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "Age: " + item.age + " years old",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "Nationality: " + countries.joinToString { it.name },
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "ID: " + item.entity_id,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }

            Text(
                text = position.toString(),
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .clip(
                        RoundedCornerShape(
                            topEnd = 8.dp
                        )
                    )
                    .background(
                        MaterialTheme.colorScheme.surface
                    )
                    .padding(5.dp)
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun HomeListItemPreview() {
    HomeListItem(
        position = 1,
        item = InterpolNotice(
            name = "John Doe",
            forename = "Alexis Carl",
            entity_id = "12345",
            date_of_birth = "2023/10/05",
            age = 25,
            nationalities = emptyList(),
            self_href = "",
            images_href = "",
            thumbnail_href = "",
            gender = "M",
            weight = "1.87",
            height = "88",
            hair_colors = emptyList(),
            eyes_colors = emptyList(),
            place_of_birth = "",
            country_of_birth_id = "",
            distinguishing_marks = "",
            arrest_warrants = emptyList(),
            languages_spoken_ids = emptyList(),
        ),
        countries = emptyList(),
        onClick = {
            //handle onClick
        }
    )
}
