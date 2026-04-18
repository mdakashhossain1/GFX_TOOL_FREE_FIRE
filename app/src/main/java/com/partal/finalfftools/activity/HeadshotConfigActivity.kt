package com.partal.finalfftools.activity

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.facebook.ads.NativeAdLayout
import com.partal.finalfftools.Ads.Constant
import com.partal.finalfftools.R
import com.shashank.sony.fancytoastlib.FancyToast
import java.util.Random

class HeadshotConfigActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_headshot_config)
        val nativeAdLayout = findViewById<NativeAdLayout>(R.id.nativeadconfig)
        Constant.metaAds.loadNative(nativeAdLayout)
        val generateSensiButton: AppCompatButton = findViewById(R.id.generateSensiButton)
        val generalProgressBar: ProgressBar = findViewById(R.id.generalProgressBar)
        val redDotProgressBar: ProgressBar = findViewById(R.id.redDotProgressBar)
        val twoXScopeProgressBar: ProgressBar = findViewById(R.id.twoXScopeProgressBar)
        val fourXScopeProgressBar: ProgressBar = findViewById(R.id.fourXScopeProgressBar)
        val awmScopeProgressBar: ProgressBar = findViewById(R.id.awmScopeProgressBar)
        val generalText: TextView = findViewById(R.id.generalText)
        val redDotText: TextView = findViewById(R.id.redDotText)
        val twoXScopeText: TextView = findViewById(R.id.twoXScopeText)
        val fourXScopeText: TextView = findViewById(R.id.fourXScopeText)
        val awmScopeText: TextView = findViewById(R.id.awmScopeText)
        val nextButton = findViewById<LinearLayout>(R.id.next_button)
        val back = findViewById<ImageView>(R.id.back)
        val generalProgress = generateRandomProgress()
        val redDotProgress = generateRandomProgress()
        val twoXScopeProgress = generateRandomProgress()
        val fourXScopeProgress = generateRandomProgress()
        val awmScopeProgress = generateRandomProgress()

        generalProgressBar.progress = generalProgress
        redDotProgressBar.progress = redDotProgress
        twoXScopeProgressBar.progress = twoXScopeProgress
        fourXScopeProgressBar.progress = fourXScopeProgress
        awmScopeProgressBar.progress = awmScopeProgress
        generalText.text = "$generalProgress"
        redDotText.text = "$redDotProgress"
        twoXScopeText.text = "$twoXScopeProgress"
        fourXScopeText.text = "$fourXScopeProgress"
        awmScopeText.text = "$awmScopeProgress"
        generateSensiButton.setOnClickListener {
            val generalProgress = generateRandomProgress()
            val redDotProgress = generateRandomProgress()
            val twoXScopeProgress = generateRandomProgress()
            val fourXScopeProgress = generateRandomProgress()
            val awmScopeProgress = generateRandomProgress()
            generalProgressBar.progress = generalProgress
            redDotProgressBar.progress = redDotProgress
            twoXScopeProgressBar.progress = twoXScopeProgress
            fourXScopeProgressBar.progress = fourXScopeProgress
            awmScopeProgressBar.progress = awmScopeProgress
            generalText.text = "$generalProgress"
            redDotText.text = "$redDotProgress"
            twoXScopeText.text = "$twoXScopeProgress"
            fourXScopeText.text = "$fourXScopeProgress"
            awmScopeText.text = "$awmScopeProgress"
            FancyToast.makeText(this, "Success", FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, false).show()
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

        back.setOnClickListener {
            finish()
        }

    }

    private fun generateRandomProgress(): Int {
        return Random().nextInt(71) + 30
    }
}