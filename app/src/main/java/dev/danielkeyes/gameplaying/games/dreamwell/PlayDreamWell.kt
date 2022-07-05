package dev.danielkeyes.gameplaying.games.dreamwell

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun PlayDreamWell(playerCount: Int, players: List<String>, resetTurn: Unit, useAction: Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text("Unimplemented")
        Text("$playerCount")
        Text("$players")
    }
}