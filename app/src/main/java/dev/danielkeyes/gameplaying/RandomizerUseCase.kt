package dev.danielkeyes.gameplaying

import kotlin.random.Random

class RandomizerUseCase {
    companion object{
        fun roll(sides: Int): Int{
            return Random.nextInt(from = 1, until = sides+1)
        }

        fun flipACoin(): CoinSides{
            return CoinSides.values().get(Random.nextInt(from = 0, until = CoinSides.values().size))
        }
    }
}

enum class CoinSides {
    HEADS,
    TAILS,
}
