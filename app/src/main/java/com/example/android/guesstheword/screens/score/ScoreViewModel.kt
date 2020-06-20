package com.example.android.guesstheword.screens.score

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ScoreViewModel(finalScore: Int) : ViewModel(){
    init {

    }

    private val _score = MutableLiveData(finalScore)
    val score: LiveData<Int>
    get() = _score

    private val _replay = MutableLiveData<Boolean>(false)
    val replay :LiveData<Boolean>
        get() = _replay


    fun playAgain(){
        _replay.value = true;
    }


}