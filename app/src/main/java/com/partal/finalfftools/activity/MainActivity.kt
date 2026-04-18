package com.partal.finalfftools.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ClickableSpan
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.partal.finalfftools.Ads.Constant
import com.partal.finalfftools.ConnectivityObserver
import com.partal.finalfftools.R
import com.partal.finalfftools.isInternetAvailable
import com.shashank.sony.fancytoastlib.FancyToast

class MainActivity : AppCompatActivity() {

    private lateinit var connectivityObserver: ConnectivityObserver
    private var noInternetDialog: AlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val startButton: Button = findViewById(R.id.startButton)
        val checkBox: CheckBox = findViewById(R.id.agreementCheckBox)
        val privacyTerms: TextView = findViewById(R.id.privacyTerms)
        val spannableString = SpannableString("Privacy Policy | Terms & Condition")

        startButton.setOnClickListener {
            if (checkBox.isChecked) {
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            } else {
                FancyToast.makeText(this, "Please agree to the terms and conditions", FancyToast.LENGTH_SHORT, FancyToast.ERROR, false).show()
            }
        }

        val privacyPolicySpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                val privacyUrl = getString(R.string.privacy_policy_url)
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(privacyUrl))
                startActivity(intent)
                try {
                    Constant.metaAds.showInterstitial()
                } catch (e: Exception) {
                    Log.e("InterstitialAdError", "Failed to show interstitial ad: ${e.message}")
                }
            }
        }

        val termsConditionSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                val termsUrl = getString(R.string.terms_conditions_url)
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(termsUrl))
                startActivity(intent)
                try {
                    Constant.metaAds.showInterstitial()
                } catch (e: Exception) {
                    Log.e("InterstitialAdError", "Failed to show interstitial ad: ${e.message}")
                }
            }
        }

        spannableString.setSpan(privacyPolicySpan, 0, 14, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(termsConditionSpan, 17, 34, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        privacyTerms.text = spannableString
        privacyTerms.movementMethod = android.text.method.LinkMovementMethod.getInstance()

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
            FancyToast.makeText(this, "You are Offline", FancyToast.LENGTH_SHORT, FancyToast.ERROR, false).show()
        } else {
            FancyToast.makeText(this, "You are Online", FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, false).show()
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

    override fun onBackPressed() {
        if (noInternetDialog != null) return
        super.onBackPressed()
    }
}