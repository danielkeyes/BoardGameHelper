package dev.danielkeyes.gameplaying.composables

import dev.danielkeyes.gameplaying.GameSelect

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.danielkeyes.gameplaying.games.dreamwell.DreamwellSetup
import dev.danielkeyes.gameplaying.games.dreamwell.PlayDreamWell
import dev.danielkeyes.gameplaying.games.dreamwell.vm.DreamWellViewModel
import dev.danielkeyes.gameplaying.games.utils.LifeCounter
import dev.danielkeyes.gameplaying.games.utils.PlayerCountSelect
import dev.danielkeyes.gameplaying.games.utils.TwoPlayerLifeCounter

enum class ROUTE{
    GAMESELECT,
    DREAMWELL,
    DREAMWELLSETUP,
    LIFECOUNTER,
    ONEPLAYERLIFECOUNTER,
    TWOPLAYERLIFECOUNTER,
}


@Composable
fun MyNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "gameSelect") {
        // Game Select
        composable(ROUTE.GAMESELECT.toString()){
            GameSelect(navController)
        }
        
        // DreamWell
        composable(ROUTE.DREAMWELLSETUP.toString()) {
            val vm = viewModel<DreamWellViewModel>()

            DreamwellSetup(
                navHost = navController,
                playerNames = vm.players.observeAsState(listOf()).value,
                updatePlayerCount = {vm.updatePlayerCount(it)},
                updatePlayerName = {player, name -> vm.updatePlayerName(player, name)}
            )
        }

        composable(ROUTE.DREAMWELL.toString()) {
            val vm = viewModel<DreamWellViewModel>()
            PlayDreamWell(
                playerCount = vm.playerCount.observeAsState(0).value,
                players = vm.players.observeAsState(listOf()).value,
                resetTurn = vm.resetTurn(),
                useAction = vm.useAction(),
            )
        }

        // Life Counter
        composable(ROUTE.LIFECOUNTER.toString()){
            PlayerCountSelect(navHost = navController) {}
        }
        composable(ROUTE.ONEPLAYERLIFECOUNTER.toString()){
            LifeCounter()
        }
        composable(ROUTE.TWOPLAYERLIFECOUNTER.toString()){
            TwoPlayerLifeCounter()
        }
    }
}
