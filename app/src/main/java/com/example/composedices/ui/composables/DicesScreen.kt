package com.example.composedices.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.composedices.R
import com.example.composedices.ui.theme.ComposeDicesTheme
import com.example.composedices.ui.viewmodel.DiceUIState

@Composable
fun DicesScreen(
    dicesUiState: State<DiceUIState>,
    onClickHistory: () -> Unit,
    rollDices: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopScreenDices(
            diceOne = dicesUiState.value.diceOne,
            diceTwo = dicesUiState.value.diceTwo
        )
        MiddleScreenDices(
            dicesUiState = dicesUiState,
            onClickHistory = onClickHistory
        ) {
            rollDices()
        }
    }
}

@Composable
fun MiddleScreenDices(
    dicesUiState: State<DiceUIState>,
    onClickHistory: () -> Unit,
    onClickRollDices: () -> Unit
) {
    ConstraintLayout(
        modifier = Modifier.fillMaxWidth().fillMaxHeight()
    ) {
        val (retries, dicesAreEqual, rollDices, history) = createRefs()
        Text(
            text = "Intentos: ${dicesUiState.value.numberOfRolls}",
            fontSize = 32.sp,
            modifier = Modifier
                .constrainAs(retries) {
                    top.linkTo(parent.top, 32.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )
        Image(
            painter = painterResource(
                if (dicesUiState.value.dicesAreEqual == true) {
                    R.drawable.check
                } else {
                    R.drawable.close
                }
            ),
            contentDescription = "",
            modifier = Modifier
                .constrainAs(dicesAreEqual) {
                    top.linkTo(retries.bottom, 8.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .size(128.dp),
            colorFilter = ColorFilter.tint(
                when (dicesUiState.value.dicesAreEqual) {
                    true -> Color.Green
                    false -> Color.Red
                    else -> Color.Transparent
                }
            )
        )
        Button(
            onClick = {
                onClickRollDices()
            },
            modifier = Modifier.constrainAs(rollDices) {
                top.linkTo(dicesAreEqual.bottom, 8.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        ) {
            Text(text = "Roll Dices")
        }
        Button(
            onClick = {
                onClickHistory()
            },
            modifier = Modifier.constrainAs(history) {
                bottom.linkTo(parent.bottom, 8.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        ) {
            Text(text = "History")
        }
    }
}

@Composable
fun TopScreenDices(
    diceOne: Int = R.drawable.dice_one,
    diceTwo: Int = R.drawable.dice_one
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = "Lanza tus dados",
            fontSize = 32.sp
        )
        Spacer(modifier = Modifier.height(32.dp))
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            val dicesModifier = Modifier.size(128.dp).weight(1f)
            Image(
                modifier = dicesModifier,
                painter = painterResource(diceOne),
                contentDescription = ""
            )
            Image(
                modifier = dicesModifier,
                painter = painterResource(diceTwo),
                contentDescription = ""
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDicesScreen() {
    ComposeDicesTheme {
//        DicesScreen(dicesState)
    }
}