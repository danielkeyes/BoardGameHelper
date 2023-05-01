package dev.danielkeyes.boardgamehelper.gameutils

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.danielkeyes.boardgamehelper.ui.theme.BoardGameHelperTheme

@Composable
fun Winner(
    winner: String,
    winnerScore: Int? = null,
    loser: String? = null,
    loserScore: Int? = null
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Winner:", fontSize = 24.sp)
        Text(text = "$winner", fontSize = 48.sp, lineHeight = 48.sp)
        Spacer(modifier = Modifier.height(16.dp))

        loser?.let {
            Text(text = "Runner up: $loser", fontSize = 18.sp)
        }

        if (winnerScore != null && loserScore != null) {
            val ptDifference = winnerScore - loserScore

            Spacer(modifier = Modifier.height(16.dp))
            Column(
                modifier = Modifier
                    .wrapContentSize()
                    .border(width = 2.dp, color = MaterialTheme.colorScheme.onSurface)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "By The Numbers", fontSize = 16.sp,
                    style = TextStyle(
                        textDecoration = TextDecoration.Underline
                    )
                )
                Text(text = "Final score: $winnerScore to $loserScore")
                Text(
                    text = "$winner won by $ptDifference pts ${percentDiff(ptDifference, winnerScore)}"
                )
                Text(
                    text = "$loser lost by -$ptDifference pts ${percentDiff(ptDifference, loserScore)}"
                )
            }
        }
    }
}

private fun percentDiff(
    ptDifference: Int,
    score: Int,
    prefix: Char? = '(',
    postfix: Char? = ')'
): String {
    return if (ptDifference != 0 && score != 0 && ptDifference != score) {
        "$prefix" +
        "${(ptDifference.toFloat() / score.toFloat() * 100).toInt()}%" +
        "$postfix"
    } else {
        ""
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewWinner() {
    BoardGameHelperTheme() {
        Winner(winner = "Daniel", winnerScore = 125, loser = "Jess", loserScore = 50)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTwoWinners() {
    BoardGameHelperTheme() {
        Winner(winner = "Daniel ... & Jess")
    }
}