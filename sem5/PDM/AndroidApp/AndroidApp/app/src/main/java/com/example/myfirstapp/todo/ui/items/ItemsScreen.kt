package com.example.myfirstapp.todo.ui.items

import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.res.stringResource
import com.example.myfirstapp.R
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.myfirstapp.MyFirstApplication
import com.example.myfirstapp.core.Result
import com.example.myfirstapp.todo.data.Series
import com.example.myfirstapp.todo.ui.ConnectionState
import com.example.myfirstapp.todo.ui.PendingWorker
import com.example.myfirstapp.todo.ui.connectivityState
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalMaterial3Api::class, ExperimentalCoroutinesApi::class)
@Composable
fun ItemsScreen(onItemClick: (id: String?) -> Unit, onAddItem: () -> Unit, onLogout: () -> Unit){
    Log.d("ItemsScreen", "Recompose")
    val itemsViewModel = viewModel<ItemsViewModel>(factory = ItemsViewModel.Factory)
    val itemsUiState by itemsViewModel.uiState.collectAsStateWithLifecycle()

    val app = LocalContext.current.applicationContext as MyFirstApplication;
    val workManager = WorkManager.getInstance(app)
    var isOffline by rememberSaveable { mutableStateOf(true) }
    val networkConnectivity by connectivityState()


    LaunchedEffect(networkConnectivity) {
        if(isOffline) {
            if (networkConnectivity == ConnectionState.Available) {
                val request = OneTimeWorkRequestBuilder<PendingWorker>().build()
                workManager.enqueue(request)
            }
        }
        isOffline = networkConnectivity == ConnectionState.Unavailable
    }

    Scaffold (
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.items)) },
                actions = {
                    Text(if (networkConnectivity== ConnectionState.Available) "Online " else "Offline ")
                    Text("•",
                        color= if (networkConnectivity== ConnectionState.Available) Color.Green else Color.Red,
                        modifier=Modifier.padding(end=20.dp))
                    Button(onClick = onLogout) {
                        Text("Logout")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    Log.d("ItemsScreen", "add")
                    onAddItem()
                },
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {
                    Icon(Icons.Rounded.Add, "Add")
                }
            }
        }
    ){
        when (itemsUiState) {
            is Result.Success ->
                ItemList(
                    seriesList = (itemsUiState as Result.Success<List<Series>>).data,
                    onItemClick = onItemClick,
                    modifier = Modifier.padding(it)
                )

            is Result.Loading -> CircularProgressIndicator(modifier = Modifier.padding(it))
            is Result.Error -> Text(
                text = "Failed to load items - ${(itemsUiState as Result.Error).exception?.message}",
                modifier = Modifier.padding(it)
            )
        }
    }
}

@Preview
@Composable
fun PreviewItemsScreen() {
    ItemsScreen(onItemClick = {}, onAddItem = {}, onLogout = {})
}