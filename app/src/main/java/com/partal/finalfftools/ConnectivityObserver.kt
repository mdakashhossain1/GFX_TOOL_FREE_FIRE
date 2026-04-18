package com.partal.finalfftools

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.Network
import android.os.Build
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class ConnectivityObserver(private val context: Context) {

    private val networkStatus = MutableLiveData<Boolean>()

    fun observeNetworkStatus(): LiveData<Boolean> {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            connectivityManager.registerDefaultNetworkCallback(object :
                ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    networkStatus.postValue(true)
                }

                override fun onLost(network: Network) {
                    networkStatus.postValue(false)
                }
            })
        } else {
            val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
            val receiver = object : BroadcastReceiver() {
                override fun onReceive(context: Context, intent: Intent) {
                    val activeNetwork =
                        connectivityManager.activeNetworkInfo
                    networkStatus.postValue(activeNetwork?.isConnectedOrConnecting == true)
                }
            }
            context.registerReceiver(receiver, filter)
        }

        return networkStatus
    }
}
