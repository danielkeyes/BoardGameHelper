package dev.danielkeyes.gameplaying.dreamwell

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun Dreamwell(playerCount: Int = 2, players: List<String> = listOf("Player 1", "Player 2")) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text("Unimplemented")
        Text("$playerCount")
        Text("$players")
    }
}