package com.example.myfirstapp.todo.ui.item

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.myfirstapp.MyFirstApplication
import com.example.myfirstapp.core.Result
import com.example.myfirstapp.core.TAG
import com.example.myfirstapp.todo.data.Series
import com.example.myfirstapp.todo.data.ItemRepository
import kotlinx.coroutines.launch

data class ItemUiState(
    val itemId: String? = null,
    val series: Series = Series(),
    var loadResult: Result<Series>? = null,
    var submitResult: Result<Series>? = null,
)

class ItemViewModel(private val itemId: String?, private val itemRepository: ItemRepository) :
    ViewModel() {

    var uiState: ItemUiState by mutableStateOf(ItemUiState(loadResult = Result.Loading))
        private set

    init {
        Log.d(TAG, "init")
        if (itemId != null) {
            loadItem()
        } else {
            uiState = uiState.copy(loadResult = Result.Success(Series()))
        }
    }

    fun loadItem() {
        viewModelScope.launch {
            itemRepository.seriesStream.collect { result ->
                if (!(uiState.loadResult is Result.Loading)) {
                    return@collect
                }
                if (result is Result.Success) {
                    val items = result.data
                    val series = items.find { it._id == itemId } ?: Series()
                    uiState = uiState.copy(loadResult = Result.Success(series), series = series)
                } else if (result is Result.Error) {
                    uiState =
                        uiState.copy(loadResult = Result.Error(result.exception))
                }
            }
        }
    }

    fun saveItem(title: String, episodes: Int, date: String, available: Boolean, imageUrl: String){
        viewModelScope.launch {
            Log.d(TAG, "save new series!!!");
            try{
                uiState = uiState.copy(submitResult = Result.Loading)
                val item = uiState.series.copy(title=title, episodes = episodes, date = date, available = available, imageUrl = imageUrl)
                val savedSeries: Series;
                savedSeries = itemRepository.save(item)
                Log.d(TAG, "save series succeeded!!!!");
                uiState = uiState.copy(submitResult = Result.Success(savedSeries))
            }catch (e: Exception){
                Log.d(TAG, "saveOrUpdateItem failed");
                uiState = uiState.copy(submitResult = Result.Error(e))
            }
        }
    }

    fun UpdateItem(title: String, episodes: Int, available: Boolean) {
        viewModelScope.launch {
            Log.d(TAG, "update Series!!!");
            try {
                uiState = uiState.copy(submitResult = Result.Loading)
                val item = uiState.series.copy(title=title, episodes = episodes, available = available)
                val savedSeries: Series;
                savedSeries = itemRepository.update(item)
                Log.d(TAG, "UpdateItem succeeded");
                uiState = uiState.copy(submitResult = Result.Success(savedSeries))
            } catch (e: Exception) {
                Log.d(TAG, "saveOrUpdateItem failed");
                uiState = uiState.copy(submitResult = Result.Error(e))
            }
        }
    }

    companion object {
        fun Factory(itemId: String?): ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val app =
                    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MyFirstApplication)
                ItemViewModel(itemId, app.container.itemRepository)
            }
        }
    }
}
