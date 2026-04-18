package com.partal.finalfftools.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.facebook.ads.NativeAdLayout
import com.partal.finalfftools.Ads.Constant
import com.partal.finalfftools.ConnectivityObserver
import com.partal.finalfftools.R
import com.partal.finalfftools.isInternetAvailable


class HomeActivity : AppCompatActivity() {
    private lateinit var connectivityObserver: ConnectivityObserver
    private var noInternetDialog: AlertDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val headshoot = findViewById<LinearLayout>(R.id.headshoot)
        val evosh4x = findViewById<LinearLayout>(R.id.evoskin)
        val grandpush = findViewById<LinearLayout>(R.id.grandpush)
        val redcustom = findViewById<LinearLayout>(R.id.redcustom)
        val onetap = findViewById<LinearLayout>(R.id.onetap)
        val gloowall = findViewById<LinearLayout>(R.id.gloowall)
        val menu = findViewById<ImageView>(R.id.menu)
        val playnow = findViewById<LinearLayout>(R.id.play_now)
        val discoverplaynow = findViewById<LinearLayout>(R.id.discover_play_now)


        val nativeAdLayout = findViewById<NativeAdLayout>(R.id.nativeadd)
        Constant.metaAds.loadNative(nativeAdLayout)

        headshoot.setOnClickListener {
            val intent = Intent(this, HeadshootActivity::class.java)
            try {
                Constant.metaAds.showInterstitial()
            } catch (e: Exception) {
                Log.e("InterstitialAdError", "Failed to show interstitial ad: ${e.message}")
            }
            startActivity(intent)
        }

        menu.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }

        evosh4x.setOnClickListener {
            val intent = Intent(this, EvoActivity::class.java)
            try {
                Constant.metaAds.showInterstitial()
            } catch (e: Exception) {
                Log.e("InterstitialAdError", "Failed to show interstitial ad: ${e.message}")
            }
            startActivity(intent)
        }
        grandpush.setOnClickListener {
            val intent = Intent(this, GransPushActivity::class.java)
            try {
                Constant.metaAds.showInterstitial()
            } catch (e: Exception) {
                Log.e("InterstitialAdError", "Failed to show interstitial ad: ${e.message}")
            }
            startActivity(intent)
        }

        redcustom.setOnClickListener {
            val intent = Intent(this, RedCustomActivity::class.java)
            try {
                Constant.metaAds.showInterstitial()
            } catch (e: Exception) {
                Log.e("InterstitialAdError", "Failed to show interstitial ad: ${e.message}")
            }
            startActivity(intent)
        }

        onetap.setOnClickListener {
            val intent = Intent(this, OneTapActivity::class.java)
            try {
                Constant.metaAds.showInterstitial()
            } catch (e: Exception) {
                Log.e("InterstitialAdError", "Failed to show interstitial ad: ${e.message}")
            }
            startActivity(intent)
        }
        gloowall.setOnClickListener {
            val intent = Intent(this, GlooActivity::class.java)
            try {
                Constant.metaAds.showInterstitial()
            } catch (e: Exception) {
                Log.e("InterstitialAdError", "Failed to show interstitial ad: ${e.message}")
            }
            startActivity(intent)
        }

        playnow.setOnClickListener{
            val playUrl = getString(R.string.play_now)
            openInExternalBrowser(playUrl)
        }

        discoverplaynow.setOnClickListener{
            val disUrl = getString(R.string.discover_play_now)
            openInExternalBrowser(disUrl)
        }



        connectivityObserver = ConnectivityObserver(this)

        connectivityObserver.observeNetworkStatus().observe(this, Observer { isConnected ->
            if (isConnected) {
                dismissNoInternetPopup()
            } else {
                showNoInternetPopup()
            }
        })

        if (!isInternetAvailable(this)) {
            showNoInternetPopup()
        } else {
            setupClickListeners()
        }

        setupClickListeners()

    }

    private fun setupClickListeners() {
        val headshootLayout: LinearLayout = findViewById(R.id.headshoot)
        headshootLayout.setOnClickListener {
            val intent = Intent(this, HeadshootActivity::class.java)
            startActivity(intent)
        }
    }

    private fun showNoInternetPopup() {
        if (noInternetDialog == null) {
            val dialogView = layoutInflater.inflate(R.layout.layout_no_internet, null)
            noInternetDialog = AlertDialog.Builder(this, R.style.TransparentDialog)
                .setView(dialogView)
                .setCancelable(false)
                .create()
            noInternetDialog?.show()
        }
    }


    private fun dismissNoInternetPopup() {
        noInternetDialog?.dismiss()
        noInternetDialog = null
    }

    private fun openInExternalBrowser(url: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }

    override fun onBackPressed() {
        if (noInternetDialog != null) return
        super.onBackPressed()
    }
}