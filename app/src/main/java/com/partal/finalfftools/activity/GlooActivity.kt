package com.partal.finalfftools.activity

import android.app.Dialog
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.facebook.ads.NativeAdLayout
import com.partal.finalfftools.Ads.Constant
import com.partal.finalfftools.R
import com.shashank.sony.fancytoastlib.FancyToast

class GlooActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gloo)

        val nativeAdLayout = findViewById<NativeAdLayout>(R.id.nativeadgloo)
        Constant.metaAds.loadNative(nativeAdLayout)

        val nextButton = findViewById<LinearLayout>(R.id.next_button)
        val back = findViewById<ImageView>(R.id.back)
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

        // In your Activity or Fragment
        val seekBar = findViewById<SeekBar>(R.id.gloo_distance_slider)
        val distanceValue = findViewById<TextView>(R.id.distance_value)


        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                distanceValue.text = progress.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })


    }
}