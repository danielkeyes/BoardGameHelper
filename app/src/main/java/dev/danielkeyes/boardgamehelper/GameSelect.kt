package dev.danielkeyes.boardgamehelper

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dev.danielkeyes.boardgamehelper.composables.MyScaffold
import dev.danielkeyes.boardgamehelper.composables.ROUTE
import dev.danielkeyes.boardgamehelper.ui.theme.BoardGameHelperTheme


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GameSelect(navHost: NavHostController) {

    // TODO would like to update text with graphic placeholders
    val data = listOf(
        GameOption("Dreamwell", ROUTE.DREAMWELL.toString()),
        GameOption("Life Counter", ROUTE.LIFECOUNTER.toString()),
        GameOption("Score Card", ROUTE.SCORING.toString()),
        GameOption("Dice/Coin", ROUTE.DICECOIN.toString()),
        GameOption("Placeholder", ROUTE.UNIMPLEMENTED.toString()),
        GameOption("Placeholder", ROUTE.UNIMPLEMENTED.toString()),
        GameOption("Placeholder", ROUTE.UNIMPLEMENTED.toString()),
        GameOption("Placeholder", ROUTE.UNIMPLEMENTED.toString()),
    )

    MyScaffold(title = "Board Game Helper") {
        LazyVerticalGrid(
            cells = GridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(data) { item ->
                Card(
                    modifier = Modifier
                        .padding(4.dp)
                        .clickable {
                            navHost.navigate(item.route)
                        },
                    backgroundColor = MaterialTheme.colorScheme.primaryContainer
                ) {
                    Text(
                        text = item.name,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(24.dp),
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }
        }
    }
}

data class GameOption(
    val name: String,
    val route: String
)

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun PreviewGameSelect() {
    BoardGameHelperTheme() {
        GameSelect(rememberNavController())
    }
}