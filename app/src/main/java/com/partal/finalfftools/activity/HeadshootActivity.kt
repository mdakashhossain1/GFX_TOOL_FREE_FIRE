package com.partal.finalfftools.activity

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.facebook.ads.NativeAdLayout
import com.partal.finalfftools.Ads.Constant
import com.partal.finalfftools.R
import com.partal.finalfftools.getDeviceInformation

class HeadshootActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_headshoot)

        val nativeAdLayout = findViewById<NativeAdLayout>(R.id.nativeadheadshoot)
        Constant.metaAds.loadNative(nativeAdLayout)

        val back = findViewById<ImageView>(R.id.back)
        val nextButton = findViewById<LinearLayout>(R.id.next_button)
        val versionTextView = findViewById<TextView>(R.id.versionTextView)
        val hardwareTextView = findViewById<TextView>(R.id.hardwareTextView)
        val deviceTextView = findViewById<TextView>(R.id.deviceTextView)
        val deviceModelTextView = findViewById<TextView>(R.id.deviceModelTextView)
        val productTextView = findViewById<TextView>(R.id.productTextView)
        val sdkTextView = findViewById<TextView>(R.id.sdkTextView)
        val deviceInfo = getDeviceInformation()
        versionTextView.text = deviceInfo["Version"]
        hardwareTextView.text = deviceInfo["Hardware"]
        deviceTextView.text = deviceInfo["Device"]
        deviceModelTextView.text = deviceInfo["Device Model"]
        productTextView.text = deviceInfo["Product"]
        sdkTextView.text = deviceInfo["SDK"]

        back.setOnClickListener {
            finish()
        }

        nextButton.setOnClickListener {
            val loadingDialog = Dialog(this)
            loadingDialog.setContentView(R.layout.loading_dialog)
            loadingDialog.setCancelable(false)
            loadingDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
            loadingDialog.show()
            Handler(Looper.getMainLooper()).postDelayed({
                loadingDialog.dismiss()
                val intent = Intent(this, HeadshotConfigActivity::class.java)
                startActivity(intent)
                try {
                    Constant.metaAds.showInterstitial()
                } catch (e: Exception) {
                    Log.e("InterstitialAdError", "Failed to show interstitial ad: ${e.message}")
                }
                finish()
            }, 3000)
        }
    }

}