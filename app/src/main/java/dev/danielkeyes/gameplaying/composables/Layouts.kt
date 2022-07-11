package dev.danielkeyes.gameplaying.composables

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp

@Composable
fun SplitRotatableLayout(
    content1: @Composable () -> Unit,
    content2: @Composable () -> Unit,
    flipPortrait: Boolean = true,
    modifier: Modifier = Modifier,
) {
    if (LocalConfiguration.current.orientation == Configuration.ORIENTATION_PORTRAIT) {
        Column(modifier = modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .rotate(if (flipPortrait) 180f else 0f),
            ){
                content2()
            }
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
                    .background(MaterialTheme.colorScheme.onBackground)
            )
            Box(modifier = Modifier.weight(1f)){
                content1()
            }
        }
    } else { // landscape
        Row(modifier = modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .weight(1f)
            ) {
                content1()
            }
            Spacer(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(8.dp)
                    .background(MaterialTheme.colorScheme.onBackground)
            )
            Box(
                modifier = Modifier.weight(1f)
            ){
                content2()
            }
        }
    }
}