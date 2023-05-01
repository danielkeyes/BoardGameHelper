package dev.danielkeyes.boardgamehelper.dreamwell

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.RadioButton
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dev.danielkeyes.boardgamehelper.R
import dev.danielkeyes.boardgamehelper.composables.ROUTE
import dev.danielkeyes.boardgamehelper.composables.rememberMutableStateListOf
import dev.danielkeyes.boardgamehelper.ui.theme.BoardGameHelperTheme

// TODO future integration, doing 2p design for the moment only
@Composable
fun DreamwellSetup(
    navHost: NavHostController,
) {
    val playerCount = rememberSaveable {
        mutableStateOf(2)
    }
    val playerNames = rememberMutableStateListOf ("Player 1", "Player 2", "Player 3", "Player 4")

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

            Row(
                modifier = Modifier.wrapContentSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                playerCountOptions.forEach {
                    RadioButton(
                        selected = playerCount.value == it,
                        onClick = { playerCount.value = it })
                    Text(
                        text = it.toString(),
                        modifier = Modifier
                            .clickable(onClick = {
                                playerCount.value = it
                            }
                            )
                            .padding(start = 4.dp)
                    )
                    Spacer(modifier = Modifier.size(4.dp))
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            for (i in 1..playerCount.value) {
                OutlinedTextField(
                    label = {
                        Text(
                            text = "Player $i",
                            modifier = Modifier
                                .padding(8.dp)
                                .background(MaterialTheme.colorScheme.background)
                        )
                    },
                    value = playerNames[i - 1], // TODO handle if playerNames doesn't have i-1
                    onValueChange = {
                        playerNames[i - 1]
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp),
            onClick = {
                navHost.navigate(
                    "${ROUTE.DREAMWELL}" +
                    "/${playerCount.value}" +
                    "/players=${arrayOf(playerNames)}"
                )
            }
        ) {
            Text(text = "Start Game")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDreamwellSetup() {
    BoardGameHelperTheme() {
        DreamwellSetup(
            navHost = rememberNavController()
        )
    }
}

