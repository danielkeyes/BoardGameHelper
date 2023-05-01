package dev.danielkeyes.boardgamehelper.composables

import dev.danielkeyes.boardgamehelper.GameSelect

import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.danielkeyes.boardgamehelper.dreamwell.Dreamwell
import dev.danielkeyes.boardgamehelper.gameutils.*

enum class ROUTE{
    GAMESELECT,
    DREAMWELL,
    LIFECOUNTER,
    ONEPLAYERLIFECOUNTER,
    TWOPLAYERLIFECOUNTER,
    SCORING,
    SCORINGWINNER,
    DICECOIN,
    UNIMPLEMENTED,
}


@Composable
fun MyNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = ROUTE.GAMESELECT.toString()) {
        // Game Select
        composable(ROUTE.GAMESELECT.toString()){
            GameSelect(navController)
        }

        // DreamWell
        composable(ROUTE.DREAMWELL.toString()) {
            Dreamwell()
        }

        // Life Counter
        composable(ROUTE.LIFECOUNTER.toString()){
            PlayerCountSelect(navHost = navController)
        }
        composable(ROUTE.ONEPLAYERLIFECOUNTER.toString()){
            SinglePlayerLifeCounter()
        }
        composable(ROUTE.TWOPLAYERLIFECOUNTER.toString()){
            TwoPlayerLifeCounter()
        }

        // Scoring
        composable(ROUTE.SCORING.toString()){
            Scoring(navHost = navController)
        }
        composable(
            "${ROUTE.SCORINGWINNER}" +
                    "?winner={winner}" +
                    "&winnerScore={winnerScore}" +
                    "&loser={loser}" +
                    "&loserScore={loserScore}",
            arguments = listOf(
                navArgument("winner") {
                    type = NavType.StringType
                },
                navArgument("winnerScore") {
                    nullable = true
                    type = NavType.StringType
                },
                navArgument("loser") {
                    nullable = true
                    type = NavType.StringType
                },
                navArgument("loserScore") {
                    nullable = true
                    type = NavType.StringType
                },
            )
        ){ entry ->

            val winner = entry.arguments?.getString("winner")?: "Winner"
            val winnerScore = entry.arguments?.getString("winnerScore")
            val loser = entry.arguments?.getString("loser")
            val loserScore = entry.arguments?.getString("loserScore")

            Winner(
                winner = winner,
                winnerScore = winnerScore?.toInt(),
                loser = loser,
                loserScore = loserScore?.toInt(),
            )
        }

        composable(ROUTE.DICECOIN.toString()){
            DiceCoin()
        }

        composable(ROUTE.UNIMPLEMENTED.toString()){
            Unimplemented()
        }
    }
}
