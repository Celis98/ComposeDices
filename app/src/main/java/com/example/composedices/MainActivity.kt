package com.example.composedices

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composedices.ui.composables.DicesScreen
import com.example.composedices.ui.composables.HistoryScreen
import com.example.composedices.ui.theme.ComposeDicesTheme
import com.example.composedices.ui.viewmodel.DiceViewModel

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<DiceViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val dicesState = viewModel.uiState.collectAsState()
//            var showHistory by remember {
//                mutableStateOf(false)
//            }
//            ComposeDicesTheme {
//                if (showHistory) {
//                    HistoryScreen(
//                        history = viewModel.history
//                    ) {
//                        showHistory = false
//                    }
//                } else {
//                    DicesScreen(
//                        dicesUiState = dicesState,
//                        onClickHistory = {
//                            showHistory = true
//                        }
//                    ) {
//                        viewModel.rollDices()
//                    }
//                }
//            }
            val navController = rememberNavController()
            ComposeDicesTheme {
                NavHost(
                    navController = navController,
                    startDestination = "dices"
                ) {
                    composable("dices") {
                        DicesScreen(
                            dicesUiState = dicesState,
                            onClickHistory = {
                                navController.navigate("history/${dicesState.value.numberOfRolls}")
                            }
                        ) {
                            viewModel.rollDices()
                        }
                    }
                    composable("history/{rolls}") { backStackEntry ->
                        val numberOfRolls = backStackEntry.arguments?.getString("rolls")
                        HistoryScreen(
                            history = viewModel.history,
                            numberOfRolls = numberOfRolls?.toInt() ?: 0
                        ) {
                            navController.popBackStack()
                        }
                    }
                }
            }
        }
    }
}