package com.example.composedices.ui.viewmodel

import androidx.annotation.DrawableRes
import com.example.composedices.R

data class DiceUIState(
    @DrawableRes
    val diceOne: Int = R.drawable.dice_one,
    @DrawableRes
    val diceTwo: Int = R.drawable.dice_one,
    val numberOfRolls: Int = 0,
    val dicesAreEqual: Boolean? = null
)
