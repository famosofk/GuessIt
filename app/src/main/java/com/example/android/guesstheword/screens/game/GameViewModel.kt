package com.example.android.guesstheword.screens.game

import android.app.Application
import android.os.CountDownTimer
import android.text.format.DateUtils
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.NavHostFragment

class GameViewModel() : ViewModel() {

    companion object {
        // These represent different important times
        // This is when the game is over
        const val DONE = 0L
        // This is the number of milliseconds in a second
        const val ONE_SECOND = 1000L
        // This is the total time of the game
        const val COUNTDOWN_TIME = 60000L
    }

    private val timer:CountDownTimer

    private val _time = MutableLiveData<String>()
     val time : LiveData<String>
    get() = _time

    private val _word = MutableLiveData<String>()
    private val _score = MutableLiveData<Int>(0)
    val score : LiveData<Int>
    get() = _score
    val word:LiveData<String>
    get() = _word
    private val _finished = MutableLiveData<Boolean>(false)
    val finished : LiveData<Boolean>
    get() = _finished

    init{

        timer = object : CountDownTimer(COUNTDOWN_TIME, ONE_SECOND) {

            override fun onTick(millisUntilFinished: Long) {
               _time.value = DateUtils.formatElapsedTime(millisUntilFinished / ONE_SECOND)
            }

            override fun onFinish() {
                _finished.value = true
            }
        }


        timer.start()

        resetList()
        nextWord()
    }



    private lateinit var wordList: MutableList<String>


    override fun onCleared() {
        super.onCleared()
        timer.cancel()
    }

     fun resetList() {
        wordList = mutableListOf(
                "queen",
                "hospital",
                "basketball",
                "cat",
                "change",
                "snail",
                "soup",
                "calendar",
                "sad",
                "desk",
                "guitar",
                "home",
                "railway",
                "zebra",
                "jelly",
                "car",
                "crow",
                "trade",
                "bag",
                "roll",
                "bubble"
        )
        wordList.shuffle()
    }

     fun nextWord() {
        if (wordList.isEmpty()) {
            resetList()

        }
            _word.value = wordList.removeAt(0)

    }

     fun onSkip() {
        _score.value = (_score.value)?.minus(1)
        nextWord()
    }

     fun onCorrect() {
        _score.value = (_score.value)?.plus(1)
        nextWord()
    }

    fun onGameFinishedComplete(){
        _finished.value = false
    }
}