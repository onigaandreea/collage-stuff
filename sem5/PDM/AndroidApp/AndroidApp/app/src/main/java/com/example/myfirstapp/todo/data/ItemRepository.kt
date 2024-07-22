package com.example.myfirstapp.todo.data

import android.app.ActivityManager
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import com.example.myfirstapp.core.Result
import com.example.myfirstapp.core.TAG
import com.example.myfirstapp.core.data.remote.Api
import com.example.myfirstapp.todo.data.remote.ItemEvent
import com.example.myfirstapp.todo.data.remote.ItemService
import com.example.myfirstapp.todo.data.remote.ItemWsClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.withContext

class ItemRepository(private val itemService: ItemService, private val itemWsClient: ItemWsClient,
                     private val database: SeriesDatabase, private val context: Context
) {
    private var seriess: List<Series> = listOf();


    private var itemsFlow: MutableSharedFlow<Result<List<Series>>> = MutableSharedFlow(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    val seriesStream: Flow<Result<List<Series>>> = itemsFlow

    init {
        Log.d(TAG, "init")
    }

    suspend fun refresh() {
        Log.d(TAG, "refresh started")
        try {
            seriess = itemService.find(authorization = getBearerToken())
            database.seriesDao().clear()
            for(series in seriess) {
                series.requiresUpdate = false
                series.requiresCreate = false
                database.seriesDao().insert(series)
            }
            Log.d(TAG, "refresh succeeded")
            itemsFlow.emit(Result.Success(seriess))
        } catch (e: Exception) {
            Log.d(TAG, "refresh failed", e)
            seriess = database.seriesDao().getAll()
            itemsFlow.emit(Result.Success(seriess))
            //itemsFlow.emit(Result.Error(e))
        }
    }

    suspend fun openWsClient() {
        Log.d(TAG, "openWsClient")
        withContext(Dispatchers.IO) {
            getItemEvents().collect {
                Log.d(TAG, "Item event collected $it")
                if (it is Result.Success) {
                    val itemEvent = it.data;
                    when (itemEvent.event) {
                        "created" -> handleItemCreated(itemEvent.payload.updatedSeries)
                        "updated" -> handleItemUpdated(itemEvent.payload.updatedSeries)
                        "deleted" -> handleItemDeleted(itemEvent.payload.updatedSeries)
                    }
                }
            }
        }
    }

    suspend fun closeWsClient() {
        Log.d(TAG, "closeWsClient")
        withContext(Dispatchers.IO) {
            itemWsClient.closeSocket()
        }
    }

    suspend fun getItemEvents(): Flow<Result<ItemEvent>> = callbackFlow {
        Log.d(TAG, "getItemEvents started")
        itemWsClient.openSocket(
            onEvent = {
                Log.d(TAG, "onEvent $it")
                if (it != null) {
                    Log.d(TAG, "onEvent trySend $it")
                    trySend(Result.Success(it))
                }
            },
            onClosed = { close() },
            onFailure = { close() });
        awaitClose { itemWsClient.closeSocket() }
    }

    suspend fun update(series: Series): Series {
        try {
            series.requiresUpdate=false
            Log.d(TAG, "update $series...")
            val updatedItem = itemService.update(authorization = getBearerToken(), series._id, series)
            Log.d(TAG, "update $series succeeded")
            handleItemUpdated(updatedItem)
            return updatedItem
        }
        catch (ex:Exception){
            Log.d(TAG, "failed update $series")
            series.requiresUpdate=true
            handleItemUpdated(series)

            Handler(Looper.getMainLooper()).post {
                Toast.makeText(context, "Server unreachable. Saved locally", Toast.LENGTH_LONG)
                    .show()
            }
            return series
        }
    }

    suspend fun save(series: Series): Series {
        try {
            Log.d(TAG, "save $series...")
            series.requiresCreate=false
            val createdItem = itemService.create(authorization = getBearerToken(), series)
            Log.d(TAG, "save $series succeeded")
            Log.d(TAG, "handle created $createdItem")
            handleItemCreated(createdItem)
            return createdItem
        }
        catch (ex:Exception){
            val createdItem = Series(
                title = series.title,
                episodes = series.episodes,
                date = series.date,
                available = series.available,
                imageUrl = series.imageUrl,
                requiresCreate = true,
                requiresUpdate = false
            )
            Log.d(TAG, "failed create on the server $series")
            handleItemCreated(createdItem)

            Handler(Looper.getMainLooper()).post {
                Toast.makeText(context, "Server unreachable. Saved locally", Toast.LENGTH_LONG)
                    .show()
            }
            return createdItem
        }
    }

    private suspend fun handleItemDeleted(series: Series) {
        Log.d(TAG, "handleItemDeleted - todo $series")
    }

    private suspend fun handleItemUpdated(series: Series) {
        Log.d(TAG, "handleItemUpdated...: $series")
        seriess = seriess.map { if (it._id == series._id) series else it }
        database.seriesDao().update(series)
        itemsFlow.emit(Result.Success(seriess))
    }

    private suspend fun handleItemCreated(series: Series) {
        Log.d(TAG, "handleItemCreated...: $series")
        if(!seriess.contains(series)) {
            seriess = seriess.plus(series)
            database.seriesDao().insert(series)
        }
        itemsFlow.emit(Result.Success(seriess))
    }

    fun quite_remove(series:Series){
        seriess = seriess.minus(series)
        database.seriesDao().deleteById(series._id)
    }

    fun setToken(token: String) {
        itemWsClient.authorize(token)
    }

    private fun getBearerToken() = "Bearer ${Api.tokenInterceptor.token}"
}