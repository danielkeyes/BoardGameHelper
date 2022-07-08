package dev.danielkeyes.gameplaying.composables

import dev.danielkeyes.gameplaying.GameSelect

import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.danielkeyes.gameplaying.dreamwell.Dreamwell
import dev.danielkeyes.gameplaying.gameutils.Scoring
import dev.danielkeyes.gameplaying.gameutils.DiceCoin
import dev.danielkeyes.gameplaying.gameutils.LifeCounter
import dev.danielkeyes.gameplaying.gameutils.PlayerCountSelect
import dev.danielkeyes.gameplaying.gameutils.TwoPlayerLifeCounter
import dev.danielkeyes.gameplaying.gameutils.Winner

enum class ROUTE{
    GAMESELECT,
    DREAMWELL,
//    DREAMWELLSETUP,
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

//        // DreamWell
//        composable(ROUTE.DREAMWELLSETUP.toString()) {
//            DreamwellSetup(
//                navHost = navController,
//            )
//        }

//        composable(
//            "${Screens.Start.route}/{tId}",
//            arguments = listOf(navArgument("tId") {
//                type = NavType.StringType
//            })
//        ) {
//
//            StartScreen(viewModel, navController, it.arguments?.getString("tId") ?: "")
//        }
        //createExercise/{exerciseId}/{workoutId}?setNumber={setNumber}&repNumber={repNumber}

        // playerCount required and players optional
//        composable(
//            "${ROUTE.DREAMWELL}/{playerCount}/players={players}",
////            "${ROUTE.DREAMWELL}/{playerCount}",
//            arguments = listOf(
//                navArgument("playerCount") {
//                    type = NavType.IntType
//                    defaultValue = 2
//                },
//                navArgument("players") {
//                    type = NavType.StringArrayType
//                    defaultValue = arrayOf("Player 1", "Player 2", "Player 3", "Player 4")
//                }
//            )
//        ) { entry ->
//            PlayDreamWell(
//                playerCount = entry.arguments?.getInt("playerCount")?: 2,
//                players = entry.arguments?.getStringArray("players")?: arrayOf<String>()
//            )
//        }

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

        composable(ROUTE.DICECOIN.toString()){
            DiceCoin()
        }

        composable(ROUTE.UNIMPLEMENTED.toString()){
            Unimplemented()
        }
    }
}
