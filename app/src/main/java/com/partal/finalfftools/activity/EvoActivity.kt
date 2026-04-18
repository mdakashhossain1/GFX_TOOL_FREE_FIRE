package com.partal.finalfftools.activity

import android.app.Dialog
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.facebook.ads.NativeAdLayout
import com.partal.finalfftools.Ads.Constant
import com.partal.finalfftools.R
import com.shashank.sony.fancytoastlib.FancyToast

class EvoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_evo)

        val nativeAdLayout = findViewById<NativeAdLayout>(R.id.nativeevo)
        Constant.metaAds.loadNative(nativeAdLayout)

        val back = findViewById<ImageView>(R.id.back)
        val nextButton = findViewById<LinearLayout>(R.id.next_button)
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
                try {
                    Constant.metaAds.showInterstitial()
                } catch (e: Exception) {
                    Log.e("InterstitialAdError", "Failed to show interstitial ad: ${e.message}")
                }
                FancyToast.makeText(this, "Applied Successfully", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, false).show()
            }, 5000)
        }
    }
}