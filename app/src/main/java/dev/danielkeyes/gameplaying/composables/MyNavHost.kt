package dev.danielkeyes.gameplaying.composables

import dev.danielkeyes.gameplaying.GameSelect

import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.danielkeyes.gameplaying.scoring.Scoring
import dev.danielkeyes.gameplaying.games.dreamwell.DreamwellSetup
import dev.danielkeyes.gameplaying.games.dreamwell.PlayDreamWell
import dev.danielkeyes.gameplaying.games.utils.LifeCounter
import dev.danielkeyes.gameplaying.games.utils.PlayerCountSelect
import dev.danielkeyes.gameplaying.games.utils.TwoPlayerLifeCounter
import dev.danielkeyes.gameplaying.scoring.Winner

enum class ROUTE{
    GAMESELECT,
    DREAMWELL,
    DREAMWELLSETUP,
    LIFECOUNTER,
    ONEPLAYERLIFECOUNTER,
    TWOPLAYERLIFECOUNTER,
    SCORING,
    SCORINGWINNER,
}


@Composable
fun MyNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = ROUTE.GAMESELECT.toString()) {
        // Game Select
        composable(ROUTE.GAMESELECT.toString()){
            GameSelect(navController)
        }
        
        // DreamWell
        composable(ROUTE.DREAMWELLSETUP.toString()) {
            DreamwellSetup(
                navHost = navController,
            )
        }

        composable(
            ROUTE.DREAMWELL.toString(),
            arguments = listOf(
                navArgument("PlayerCount") {
                    type = NavType.IntType
                    defaultValue = 2
                },
                navArgument("Players") {
                    type = NavType.StringArrayType
                    defaultValue = arrayOf("Player 1", "Player 2", "Player 3", "Player 4")
                }
            )
        ) { entry ->
            PlayDreamWell(
                playerCount = entry.arguments?.getInt("PlayerCount")?: 2,
                players = entry.arguments?.getStringArray("Players")?: arrayOf<String>()
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

        // Scoring
        composable(ROUTE.SCORING.toString()){
            Scoring(navHost = navController)
        }
        composable(ROUTE.SCORINGWINNER.toString(),
            arguments = listOf(
                navArgument("winner") {
                    type = NavType.IntType
                    defaultValue = "Winner"
                },
                navArgument("winnerScore") {
                    type = NavType.StringArrayType
                    defaultValue = 0
                },
                navArgument("loser") {
                    type = NavType.StringArrayType
                    defaultValue = "Loser"
                },
                navArgument("loserScore") {
                    type = NavType.StringArrayType
                    defaultValue = 0
                },
            )
        ){ entry ->
            Winner(
                winner = entry.arguments?.getString("winner")?: "Winner",
                winnerScore = entry.arguments?.getInt("winnerScore")?: 0,
                loser = entry.arguments?.getString("loser")?: "Loser",
                loserScore = entry.arguments?.getInt("loserScore")?: 0,
            )
        }
    }
}
