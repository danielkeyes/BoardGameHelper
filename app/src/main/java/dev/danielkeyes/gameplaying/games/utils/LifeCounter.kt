package dev.danielkeyes.gameplaying.games.utils

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dev.danielkeyes.gameplaying.ui.theme.GamePlayingTheme

@Composable
fun PlayerCountSelect(navHost: NavHostController, setPlayerCount: (Int) -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Players",
            style = MaterialTheme.typography.titleLarge,
            fontSize = 64.sp
        )
        Button(
            modifier = Modifier.padding(16.dp),
            onClick = {
                setPlayerCount(1)
                navHost.navigate("onePlayerLifeCounter")
            }) {
            Text(text = "1 Player")
        }
        Button(
            modifier = Modifier.padding(16.dp),
            onClick = {
                setPlayerCount(2)
                navHost.navigate("twoPlayerLifeCounter")
            }) {
            Text(text = "2 Player")
        }
    }
}

@Composable
fun LifeCounter(
    lifeTotal: Int = 20,
    name: String = "Player 1",
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "- $name -", fontSize = 36.sp, modifier = Modifier.padding(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            HealthButton(text = "-") { /* Decrease here */ }
            Text(
                text = lifeTotal.toString(),
                style = MaterialTheme.typography.titleLarge,
                fontSize = 64.sp
            )
            HealthButton(text = "+") { /* Decrease here */ }
        }
    }
}

@Composable
fun TwoPlayerLifeCounter(
    player1LifeTotal: Int = 20,
    player1Name: String = "Player 1",
    player2LifeTotal: Int = 20,
    player2Name: String = "Player 1",
) {
    // portrait
    if (LocalConfiguration.current.orientation == Configuration.ORIENTATION_PORTRAIT) {
        Column(modifier = Modifier.fillMaxSize()) {
            LifeCounter(
                lifeTotal = player2LifeTotal,
                name = player1Name,
                modifier = Modifier
                    .weight(1f)
                    .rotate(180f)
            )
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .background(MaterialTheme.colorScheme.onBackground))
            LifeCounter(
                lifeTotal = player1LifeTotal,
                name = player2Name,
                modifier = Modifier.weight(1f)
            )
        }
    } else { // landscape
        Row(modifier = Modifier.fillMaxSize()) {
            LifeCounter(
                lifeTotal = player1LifeTotal,
                name = player1Name,
                modifier = Modifier
                    .weight(1f)
            )
            Spacer(modifier = Modifier
                .fillMaxHeight()
                .width(8.dp)
                .background(MaterialTheme.colorScheme.onBackground))
            LifeCounter(
                lifeTotal = player2LifeTotal,
                name = player2Name,
                modifier = Modifier.weight(1f)
            )
        }
    }

//    Column() {
//        LifeCounter(
//            lifeTotal = player2LifeTotal,
//            name = player1Name,
//            modifier = Modifier
//                .weight(1f)
//                .rotate(180f)
//        )
//        Spacer(modifier = Modifier
//            .fillMaxWidth()
//            .height(8.dp)
//            .background(MaterialTheme.colorScheme.onBackground))
//        LifeCounter(
//            lifeTotal = player1LifeTotal,
//            name = player2Name,
//            modifier = Modifier.weight(1f)
//        )
//    }
}

@Composable
fun HealthButton(text: String, updateHealth: () -> Unit) {
    Button(
        modifier = Modifier.padding(8.dp),
        onClick = { updateHealth }) {
        Text(
            text = text,
            fontSize = 18.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSetPlayerCount() {
    GamePlayingTheme {
        PlayerCountSelect(
            rememberNavController()
        ) {}
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLifeCounter() {
    GamePlayingTheme {
        LifeCounter(20, "Player 1")
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, device = Devices.AUTOMOTIVE_1024p, widthDp = 1920, heightDp = 1080)
@Composable
fun PreviewDualLifeCounter() {
    GamePlayingTheme {
        TwoPlayerLifeCounter(20, "Daniel", 20, "Jess")
    }
}