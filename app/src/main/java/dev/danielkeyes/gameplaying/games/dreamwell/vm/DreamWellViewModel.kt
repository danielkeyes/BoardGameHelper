package dev.danielkeyes.gameplaying.games.dreamwell.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DreamWellViewModel: ViewModel() {
    //TODO update to flow
    // Doing LiveData now because it it what I know

    private var _playerCount: MutableLiveData<Int> = MutableLiveData<Int>(2)
    val playerCount: LiveData<Int>
        get() = _playerCount

    private val defaultPlayerNames = listOf(
        "Player 1",
        "Player 2",
        "Player 3",
        "Player 4",
    )
    private var _players: MutableLiveData<List<String>> = MutableLiveData(defaultPlayerNames)
    val players: LiveData<List<String>>
        get() = _players

    private var _currentPlayer: MutableLiveData<Int> = MutableLiveData(1)
    val currentPlayer: LiveData<Int>
        get() = _currentPlayer

    private val _actionsLeft: MutableLiveData<Int> = MutableLiveData(3)
    val actionsLeft: LiveData<Int>
        get() = _actionsLeft

    fun resetTurn(){
        _actionsLeft.value = 3
    }

    fun useAction(){
        // if last action, move to next player and set actions left to 3
        if(actionsLeft.value == 1) {
            _actionsLeft.value = 3
            _currentPlayer.value = _currentPlayer.value?.plus(1 )?.rem(_playerCount.value!!)
        } else {
            _actionsLeft.value == _actionsLeft.value?.minus(1)
        }
    }

    fun updatePlayerCount(playerCount: Int){
        _playerCount.value = playerCount
    }

    fun updatePlayerName(player: Int, name: String){
        // work around for live data using array
         var players = _players.value?.toMutableList()
        players?.set(player-1, name)
        _players.value = players
    }

//
//    private val _playerNames = MutableStateFlow(listPlayerNames)
//    val playerNames = _playerNames.asStateFlow()
}