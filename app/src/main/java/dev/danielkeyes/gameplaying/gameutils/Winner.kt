package dev.danielkeyes.gameplaying.gameutils

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun Winner(winner: String, winnerScore: Int, loser: String, loserScore: Int) {
    Column() {
        Text(text = winner)
        Text(text = winnerScore.toString())
        Text(text = loser)
        Text(text = loserScore.toString())
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewWinner() {
    Winner(winner = "Winner", winnerScore = 100, loser = "Loser", loserScore = 0)
}