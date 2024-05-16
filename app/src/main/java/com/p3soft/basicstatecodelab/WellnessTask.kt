package com.p3soft.basicstatecodelab

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

/* Changing this
data class WellnessTask(
    val id: Int,
    val label: String,
    var checked: MutableState<Boolean> = mutableStateOf(false),
)

 */

class WellnessTask(
    val id: Int,
    val label: String,
    initialChecked: Boolean = false,
) {
    var checked by mutableStateOf(initialChecked)
}