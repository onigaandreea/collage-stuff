package com.example.myfirstapp.todo.ui.item

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myfirstapp.core.Result
import com.example.myfirstapp.core.TAG
import com.example.myfirstapp.todo.ui.ImagePicker
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemAddScreen(itemId: String?, onClose: () -> Unit) {
    val itemViewModel = viewModel<ItemViewModel>(factory = ItemViewModel.Factory(itemId))
    val itemUiState = itemViewModel.uiState

    var title by rememberSaveable { mutableStateOf("") }
    var episodes by rememberSaveable { mutableStateOf("") }
    var date by rememberSaveable { mutableStateOf("") }
    var available by rememberSaveable { mutableStateOf(true) }
    var imageUrl by rememberSaveable { mutableStateOf("")}

    Log.d("ItemAddScreen", "recompose, text = $title")

    LaunchedEffect(itemUiState.submitResult) {
        Log.d("ItemScreen", "Submit = ${itemUiState.submitResult}")
        if (itemUiState.submitResult is Result.Success) {
            Log.d("ItemScreen", "Closing screen")
            onClose()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Add Series") },
                actions = {
                    Button(onClick = {
                        Log.d("ItemScreen", "save item text = $title")
                        itemViewModel.saveItem(title, episodes.toInt(), date, available, imageUrl)
                    }) { Text("Save") }
                }
            )
        }
    ) {
        val scrollState = rememberScrollState()
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .verticalScroll(state = scrollState)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                if (itemUiState.loadResult is Result.Loading) {
                    CircularProgressIndicator()
                    return@Column
                }

                if (itemUiState.submitResult is Result.Loading) {
                    LinearProgressIndicator()
                }

                if (itemUiState.loadResult is Result.Error) {
                    Text(text = "Failed to load item - ${(itemUiState.loadResult as Result.Error).exception?.message}")
                }

                TextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Title") },
                    modifier = Modifier.fillMaxWidth(),
                )
                TextField(
                    value = episodes,
                    onValueChange = { episodes = it },
                    label = { Text("Episodes") },
                    modifier = Modifier.fillMaxWidth(),
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.weight(1.3f)
                    ) {
                        RadioButton(
                            selected = available == true,
                            onClick = { available = true }
                        )
                        Text("Available")
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.weight(1f)
                    ) {
                        RadioButton(
                            selected = available == false,
                            onClick = { available = false }
                        )
                        Text("Not available")
                    }
                }

                Row {
                    val calendar = Calendar.getInstance()
                    calendar.set(2023, 12, 8)

                    val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.ROOT)

                    val datePickerState = rememberDatePickerState(initialSelectedDateMillis = calendar.timeInMillis)

                    DatePicker(
                        state = datePickerState,
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                    )

                    Log.d(TAG, "formatting date....")
                    date = formatter.format(datePickerState.selectedDateMillis)
                }

                ImagePicker(imageUrl, {imageUrl=it})
            }

            if (itemUiState.submitResult is Result.Error) {
                Text(
                    text = "Failed to submit item - ${(itemUiState.submitResult as Result.Error).exception?.message}",
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }
    }
}
