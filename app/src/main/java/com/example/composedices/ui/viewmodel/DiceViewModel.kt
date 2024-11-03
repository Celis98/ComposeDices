package com.example.composedices.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.composedices.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class DiceViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(DiceUIState())
    val uiState: StateFlow<DiceUIState> = _uiState.asStateFlow()

    val history = arrayListOf<String>()

    private var dicesList: List<Int> = listOf(
        R.drawable.dice_one,
        R.drawable.dice_two,
        R.drawable.dice_three,
        R.drawable.dice_four,
        R.drawable.dice_five,
        R.drawable.dice_six
    )

    fun rollDices() {
        val diceOne = obtainDice()
        val diceTwo = obtainDice()
        _uiState.value = _uiState.value.copy(
            diceOne = diceOne,
            diceTwo = diceTwo,
            numberOfRolls = _uiState.value.numberOfRolls + 1,
            dicesAreEqual = diceOne == diceTwo
        )
        history.add("${dicesList.indexOf(diceOne)}-${dicesList.indexOf(diceTwo)}")
    }

    private fun obtainDice() : Int =
        dicesList.random()

}