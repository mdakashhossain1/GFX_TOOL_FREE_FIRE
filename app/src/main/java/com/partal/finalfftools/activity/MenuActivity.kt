package com.partal.finalfftools.activity

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.facebook.ads.NativeAdLayout
import com.partal.finalfftools.Ads.Constant
import com.partal.finalfftools.R

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val privacyPolicyLayout = findViewById<LinearLayout>(R.id.privacy_policy)
        val termsConditionsLayout = findViewById<LinearLayout>(R.id.terms_condition)
        val helpSupportLayout = findViewById<LinearLayout>(R.id.help_support)
        val contactUsLayout = findViewById<LinearLayout>(R.id.contact_us)
        val rateUsLayout = findViewById<LinearLayout>(R.id.rate_us)
        val shareAppLayout = findViewById<LinearLayout>(R.id.share_app)

        val nativeAdLayout = findViewById<NativeAdLayout>(R.id.nativeprofile)
        Constant.metaAds.loadNative(nativeAdLayout)
        val back = findViewById<ImageView>(R.id.back)
        back.setOnClickListener {
            finish()
        }

        privacyPolicyLayout.setOnClickListener {
            val privacyPolicyUrl = getString(R.string.privacy_policy_url)
            openInExternalBrowser(privacyPolicyUrl)
        }

        termsConditionsLayout.setOnClickListener {
            val termsConditionsUrl = getString(R.string.terms_conditions_url)
            openInExternalBrowser(termsConditionsUrl)
        }

        helpSupportLayout.setOnClickListener {
            val helpSupportUrl = getString(R.string.help_support_url)
            openInExternalBrowser(helpSupportUrl)
        }

        contactUsLayout.setOnClickListener {
            val contactUsUrl = getString(R.string.contact_us_url)
            openInExternalBrowser(contactUsUrl)
        }
        rateUsLayout.setOnClickListener{
            val appPackageName = packageName
            try {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$appPackageName")))
            } catch (e: ActivityNotFoundException) {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=$appPackageName")))
            }
        }
        shareAppLayout.setOnClickListener {
            val appPackageName = packageName
            val playStoreUrl = "https://play.google.com/store/apps/details?id=$appPackageName"
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name))
            shareIntent.putExtra(Intent.EXTRA_TEXT, playStoreUrl)
            startActivity(Intent.createChooser(shareIntent, "Share via"))
        }
    }

    private fun openInExternalBrowser(url: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }
}