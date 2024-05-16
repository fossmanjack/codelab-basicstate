package com.p3soft.basicstatecodelab

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun WaterCounter(modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(16.dp)) {
        var count by rememberSaveable { mutableStateOf(0) }
        if(count > 0) {

            // This text is "present" if the button has been pressed at least once
            // Elsewise it is "absent"
            Text("You've had $count glasses.")
        }
        Button(
            onClick = {
                count++
            },
            Modifier.padding(top = 8.dp),
            enabled = count < 10,
        ) {
            Text("Add one")
        }
    }

}

@Composable
fun StatelessCounter(
    count: Int,
    onIncrement: () -> Unit,        // accept a callback function to update state
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.padding(16.dp)) {
        if(count > 0) {
            Text("You've had ${count} glasses.")
        }
        Button(
            onClick = onIncrement,
            Modifier.padding(top = 8.dp),
            enabled = count < 10,
        ) {
            Text("Add one")
        }
    }
}

@Composable
fun StatefulCounter(modifier: Modifier = Modifier) {
    var count by rememberSaveable { mutableStateOf(0) } // State lives here
    StatelessCounter(       // The component that actually displays
        count,              // the current count
        { count++ },        // the (anonymous) callback function
        modifier
    )
}

/* NOTES

- Your stateless composables can now be reused:

@Composable
fun StatefulCounter() {
    var waterCount by remember { mutableStateOf(0) }

    var juiceCount by remember { mutableStateOf(0) }

    StatelessCounter(waterCount, { waterCount++ })
    StatelessCounter(juiceCount, { juiceCount++ })
}

If juiceCount is modified then StatefulCounter is recomposed.  During recomposition, Compose identifies
which functions read juiceCount and triggers recomposition of *only* those functions.

- Your stateful Composable can now provide the same state to multiple Composables

@Composable
fun StatefulCounter() {
   var count by remember { mutableStateOf(0) }

   StatelessCounter(count, { count++ })
   AnotherStatelessMethod(count, { count *= 2 })
}

If the count is updated by either StatelessCounter or AnotherStatelessMethod, everything is recomposed,
which is as expected.


 */