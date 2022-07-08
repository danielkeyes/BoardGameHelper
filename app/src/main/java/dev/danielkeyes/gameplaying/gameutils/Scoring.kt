package dev.danielkeyes.gameplaying.gameutils

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dev.danielkeyes.gameplaying.composables.ROUTE
import dev.danielkeyes.gameplaying.composables.rememberMutableStateListOf
import dev.danielkeyes.gameplaying.ui.theme.GamePlayingTheme

// TODO player name clickable

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Scoring(navHost: NavHostController) {
    var activePlayer = rememberSaveable { mutableStateOf(1) }
    val player1Score = rememberMutableStateListOf<Int>()
    val player2Score = rememberMutableStateListOf<Int>()

    Column() {
        // Scoring buttons
        val scoringValue = listOf<Int>(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        LazyVerticalGrid(
            modifier = Modifier.fillMaxWidth(),
            cells =
            if (LocalConfiguration.current.orientation == Configuration.ORIENTATION_PORTRAIT)
            {
                GridCells.Fixed(4)
            }
            else  {
                GridCells.Fixed(10)
            },
            contentPadding = PaddingValues(8.dp)
        ) {
            items(items = scoringValue) {
                Button(
                    onClick = {
                        if (activePlayer.value == 1) {
                            player1Score.add(it)
                        } else if (activePlayer.value == 2) {
                            player2Score.add(it)
                        }
                    }, modifier = Modifier.padding(4.dp)
                ) {
                    Text(text = "+$it")
                }
            }
        }

        // Player Scores
        Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.weight(1f)) {
            SinglePlayerScoring(
                name = "Player 1",
                scoreHistory = player1Score.toList(),
                isActive = activePlayer.value == 1,
                modifier = Modifier.weight(1f),
                onClick = { activePlayer.value = 1 }
            )
            Spacer(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(2.dp)
                    .background(androidx.compose.material.MaterialTheme.colors.onBackground)
            )
            SinglePlayerScoring(
                name = "Player 2",
                scoreHistory = player2Score.toList(),
                isActive = activePlayer.value == 2,
                modifier = Modifier.weight(1f),
                onClick = { activePlayer.value = 2 }
            )
        }

        // Reset and Done Button
        Row {

            Button(
                onClick = {
                    player1Score.clear()
                    player2Score.clear()
                },
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Text(text = "Reset", fontSize = 16.sp)
            }

            Button(
                onClick = {
                    if (player1Score.sum() > player2Score.sum()) {
                        navigateWinnerScoring(
                            navHost,
                            "Player 1",
                            player1Score.sum(),
                            "Player 2",
                            player2Score.sum()
                        )
                    } else {
                        navigateWinnerScoring(
                            navHost,
                            "Player 2",
                            player2Score.sum(),
                            "Player 1",
                            player1Score.sum()
                        )
                    }
                },
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Text(text = "Done", fontSize = 16.sp)
            }
        }
    }
}

private fun navigateWinnerScoring(
    navHost: NavHostController,
    winner: String,
    winnerScore: Int,
    loser: String,
    loserScore: Int
) {
    navHost.navigate(
        "${ROUTE.SCORINGWINNER.toString()}" +
                "winner=${winner}" +
                "winnerScore=${winnerScore}" +
                "loser=${loser}" +
                "loserScore=${loserScore}"
    )
}


@Composable
fun SinglePlayerScoring(
    name: String,
    scoreHistory: List<Int>,
    isActive: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .clickable { onClick() }
            .border(
                width = 4.dp, color =
                if (isActive) {
                    MaterialTheme.colorScheme.onBackground
                } else {
                    MaterialTheme.colorScheme.surface
                }
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = name, modifier = Modifier.padding(16.dp))

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp)
                .padding(horizontal = 16.dp)
                .background(color = MaterialTheme.colorScheme.onBackground)
                .padding(vertical = 16.dp)
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                val stringBuilder = StringBuilder()
                scoreHistory.forEach {
                    stringBuilder.append("+${it.toString()} ")
                }
                Text(text = stringBuilder.toString(), modifier = Modifier.padding(16.dp))
            }

            Text(
                modifier = Modifier
                    .padding(8.dp)
                    .background(MaterialTheme.colorScheme.surface),
                text = scoreHistory.sum().toString(),
                fontSize = 48.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewScoring() {
    GamePlayingTheme {
        Scoring(rememberNavController())
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSinglePlayerScore() {
    GamePlayingTheme() {
        SinglePlayerScoring(
            name = "Player 1",
            scoreHistory = listOf(1, 3, 1, 5, 7, 1),
            isActive = true
        )
    }
}