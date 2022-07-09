package dev.danielkeyes.gameplaying.composables

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun <T> LazyVerticalGridOrientatedButtons(items: List<T>, portraitCount: Int = 4, landscapeCount: Int = 10, onclick: (T) -> Unit) {
    LazyVerticalGrid(
        modifier = Modifier.fillMaxWidth(),
        cells =
        if (LocalConfiguration.current.orientation == Configuration.ORIENTATION_PORTRAIT) {
            GridCells.Fixed(4)
        } else {
            GridCells.Fixed(10)
        },
        contentPadding = PaddingValues(8.dp)
    ) {
        items(items = items) {
            Button(
                modifier = Modifier.padding(8.dp),
                onClick = {
                onclick(it)
            }
            ) {
                Text(text = it.toString())
            }
        }
    }
}

@Composable
fun Unimplemented() {
    MyScaffold(title = "Coming Soon") {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = "Unimplemented",
                fontSize = 48.sp,
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyScaffold(
    title: String, content: @Composable () -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(MaterialTheme.colorScheme.primary)
            ) {
                Text(
                    text = title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    ) {
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(it)){
            content()
        }
    }
}