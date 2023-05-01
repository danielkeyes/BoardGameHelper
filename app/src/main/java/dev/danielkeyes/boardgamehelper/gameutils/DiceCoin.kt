package dev.danielkeyes.boardgamehelper.gameutils

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.danielkeyes.boardgamehelper.RandomizerUseCase
import dev.danielkeyes.boardgamehelper.composables.MyScaffold
import dev.danielkeyes.boardgamehelper.ui.theme.BoardGameHelperTheme

// TODO - add multiple dice
// TODO - add animation when clicking button
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DiceCoin() {
    // All possible dice sides
    val diceOptions = listOf(100, 20, 12, 10, 8, 6, 4)

    MyScaffold(title = "Dice/Coin") {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {

            var resultTitle by rememberSaveable { mutableStateOf("") }
            var result by rememberSaveable { mutableStateOf("Choose an option") }

            // Dice roll options
            Text(text = "Roll a Die")
            // TODO rework reusable lazyVertical Grid for reuse
//        LazyVerticalGridOrientatedButtons(
//        items = diceOptions,
//        onclick = { it -> /* What happens when we roll */}
//        )
            LazyVerticalGrid(
                modifier = Modifier.fillMaxWidth(),
                cells =
                if (LocalConfiguration.current.orientation == Configuration.ORIENTATION_PORTRAIT) {
                    GridCells.Fixed(3)
                } else {
                    GridCells.Fixed(10)
                },
                contentPadding = PaddingValues(8.dp)
            ) {
                items(items = diceOptions) {
                    Button(
                        modifier = Modifier.padding(8.dp),
                        onClick = {
                            resultTitle = "Rolled a D$it"
                            result = RandomizerUseCase.roll(it).toString()
                        }
                    ) {
                        Text(text = "d$it")
                    }
                }
            }

            // Coin flip options
            Text(text = "Flip a coin")
            Button(
                modifier = Modifier.padding(8.dp),
                onClick = {
                    resultTitle = "Coin flip results"
                    result = RandomizerUseCase.flipACoin().toString()
                }
            ) {
                Text(text = "Flip!")
            }

            // Result
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "$resultTitle",
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = "$result",
                    style = MaterialTheme.typography.headlineLarge
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDiceCoin() {
    BoardGameHelperTheme {
        DiceCoin()
    }
}