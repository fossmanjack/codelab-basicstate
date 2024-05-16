package com.p3soft.basicstatecodelab

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.viewmodel.compose.viewModel

// fun getWellnessTasks() = List(30) { i -> WellnessTask(i, "Task no. ${i}") }

@Composable
fun WellnessScreen(
    modifier: Modifier = Modifier,
    wellnessModel: WellnessViewModel = viewModel(),
    ) {
    Column(
        modifier = modifier,
    ) {
        StatefulCounter()

        WellnessTasksList(
            list = wellnessModel.tasks,
            onCloseTask = { task -> wellnessModel.remove(task) },
            onCheckedTask = { task, checked ->
                wellnessModel.changeTaskChecked(task, checked)
            }
        )
    }
}
