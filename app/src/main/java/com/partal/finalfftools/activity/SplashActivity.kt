package com.partal.finalfftools.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.partal.finalfftools.Ads.Constant
import com.partal.finalfftools.Ads.MetaAds
import com.partal.finalfftools.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Constant.metaAds = MetaAds(this)
        //Constant.metaAds.setAdUnit("","","","");
        Handler().postDelayed({
            startActivity(
                Intent(
                    this,
                    MainActivity::class.java
                )
            )
        }, 3000)
    }
}