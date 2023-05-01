package dev.danielkeyes.boardgamehelper.dreamwell

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import dev.danielkeyes.boardgamehelper.composables.MyScaffold
import dev.danielkeyes.boardgamehelper.composables.SplitRotatableLayout
import dev.danielkeyes.boardgamehelper.ui.theme.BoardGameHelperTheme

@Composable
fun Dreamwell(playerCount: Int = 2, players: List<String> = listOf("Player 1", "Player 2")) {
    var actionsLeft by rememberSaveable() {
        mutableStateOf(3)
    }

    var currentPlayer by rememberSaveable() {
        mutableStateOf(1)
    }

    val useAction = {
        if (actionsLeft == 1) { // if was last action, flip players and reset actions to 3
            actionsLeft = 3
            currentPlayer = if (currentPlayer == 1) 2 else 1
        } else { // else just decrement actions
            actionsLeft = actionsLeft.minus(1)
        }
    }

    MyScaffold(title = "Dreamwell") {
        SplitRotatableLayout(
            content1 = {
                PlayerActions(
                    player = "Player 1",
                    actionsLeft = actionsLeft,
                    isCurrentPlayer = currentPlayer == 1,
                    onClick = { useAction() }
                )
            },
            content2 = {
                PlayerActions(
                    player = "Player 2",
                    actionsLeft = actionsLeft,
                    isCurrentPlayer = currentPlayer == 2,
                    onClick = { useAction() }
                )
            }
        )
    }
}

@Composable
private fun PlayerActions(player: String, actionsLeft: Int, isCurrentPlayer: Boolean, onClick: () -> Unit, modifier: Modifier = Modifier) {
    val myModifier = if(isCurrentPlayer){
        modifier
            .clickable {
                onClick()
            }
            .alpha(1.0f)
    } else {
        modifier.alpha(.3f)
    }

    Column(
        modifier = myModifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = player, fontSize = 48.sp)
        Text(text = "Actions left")
        Text(text = "${if (isCurrentPlayer) actionsLeft else 0}", fontSize = 64.sp)
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, device = Devices.AUTOMOTIVE_1024p, widthDp = 1920, heightDp = 1080)
@Composable
fun PreviewDreamwell() {
    BoardGameHelperTheme {
        Dreamwell()
    }
}