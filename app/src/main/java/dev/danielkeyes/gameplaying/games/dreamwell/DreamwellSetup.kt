package dev.danielkeyes.gameplaying.games.dreamwell

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.RadioButton
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dev.danielkeyes.gameplaying.R
import dev.danielkeyes.gameplaying.ui.theme.GamePlayingTheme

@Composable
fun DreamwellSetup(
    navHost: NavHostController,
    playerNames: List<String>,
    updatePlayerCount: (Int) -> Unit,
    updatePlayerName: (Int, String) -> Unit,
) {
    Column {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // TODO do I want to keep this?
            Image(
                painter = painterResource(id = R.drawable.dreamwellboardgame),
                contentDescription = null
            )

            // Total Players
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Players", style = MaterialTheme.typography.headlineMedium)
            val playerCountOptions = listOf(2, 3, 4)
            var playerCount by remember { mutableStateOf(playerCountOptions.first()) }
            Row(
                modifier = Modifier.wrapContentSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                playerCountOptions.forEach {
                    RadioButton(selected = playerCount == it, onClick = { playerCount = it })
                    Text(
                        text = it.toString(),
                        modifier = Modifier
                            .clickable(onClick = {
                                playerCount = it
                                updatePlayerCount(it)
                            }
                            )
                            .padding(start = 4.dp)
                    )
                    Spacer(modifier = Modifier.size(4.dp))
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            // TODO update this to be based off of viewmodel value
            // getPlayerName()

            // Player Names
            // Player 1

            for (i in 1..playerCount) {
                OutlinedTextField(
                    value = playerNames[i - 1],
                    onValueChange = {
                        updatePlayerName(i, it)
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp),
            onClick = { navHost.navigate("playDreamWell") }) {
            Text(text = "Start Game")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDreamwellSetup() {
    GamePlayingTheme() {
        DreamwellSetup(
            navHost = rememberNavController(),
            listOf("Daniel", "Jess", "Dean"),
            {},
            { _, _ -> })
    }
}

