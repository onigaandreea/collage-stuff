package com.example.myfirstapp

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import android.net.NetworkCapabilities
import android.util.Log
import androidx.core.content.ContextCompat.getSystemService
import androidx.datastore.preferences.preferencesDataStore
import androidx.room.Room
import com.example.myfirstapp.auth.data.remote.AuthDataSource
import com.example.myfirstapp.core.TAG
import com.example.myfirstapp.auth.data.AuthRepository
import com.example.myfirstapp.core.data.UserPreferencesRepository
import com.example.myfirstapp.core.data.remote.Api
import com.example.myfirstapp.todo.data.ItemRepository
import com.example.myfirstapp.todo.data.SeriesDatabase
import com.example.myfirstapp.todo.data.remote.ItemService
import com.example.myfirstapp.todo.data.remote.ItemWsClient

val Context.userPreferencesDataStore by preferencesDataStore(
    name = "user_preferences"
)

class AppContainer(val context: Context) {
    private val networkRequest = NetworkRequest.Builder()
        .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
        .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
        .build()

    var networkChanged:(Boolean)->Unit = { _ -> }

    fun setNetworkChangedCallback(networkChanged:(Boolean)->Unit){
        this.networkChanged = networkChanged
        val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                    this.networkChanged(true)
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                    this.networkChanged(true)
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                    this.networkChanged(true)
                }
            }
        }
        else {
            this.networkChanged(false)
        }
    }

    val connectivityManager = getSystemService(context, ConnectivityManager::class.java) as ConnectivityManager

    val networkCallback = object : ConnectivityManager.NetworkCallback() {
        // network is available for use
        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            Log.d("NetworkCallback","Network on")
            networkChanged.invoke(true)
        }

        // Network capabilities have changed for the network
        override fun onCapabilitiesChanged(
            network: Network,
            networkCapabilities: NetworkCapabilities
        ) {
            super.onCapabilitiesChanged(network, networkCapabilities)
            val unmetered = networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_NOT_METERED)
        }

        // lost network connection
        override fun onLost(network: Network) {
            super.onLost(network)
            Log.d("NetworkCallback","Network off")
            networkChanged.invoke(false)
        }
    }

    init {
        Log.d(TAG, "init")

        connectivityManager.requestNetwork(networkRequest, networkCallback)
    }

    private val authDataSource: AuthDataSource = AuthDataSource()

    val itemService: ItemService = Api.retrofit.create(ItemService::class.java)
    val itemWsClient: ItemWsClient = ItemWsClient(Api.okHttpClient)

    val database = Room
        .databaseBuilder(context, SeriesDatabase::class.java, "series-db")
        .allowMainThreadQueries()
        .build()


    val itemRepository: ItemRepository by lazy {
        ItemRepository(itemService, itemWsClient, database, context)
    }

    val authRepository: AuthRepository by lazy {
        AuthRepository(authDataSource)
    }

    val userPreferencesRepository: UserPreferencesRepository by lazy {
        UserPreferencesRepository(context.userPreferencesDataStore)
    }
}