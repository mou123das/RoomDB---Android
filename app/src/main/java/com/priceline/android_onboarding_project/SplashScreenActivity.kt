package com.priceline.android_onboarding_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log

//SPLASH SCREEN ACTIVITY
class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // this is being called so that once we press back from the login UI we exit the app rather than going back to the splash screen
        }, 1500)

        Log.d("lifecycle","onCreate_SPLASH SCREEN ACTIVITY")
    }

    override fun onStart() {
        super.onStart()
        Log.d("lifecycle","onStart_SPLASH SCREEN ACTIVITY")
    }

    override fun onResume() {
        super.onResume()
        Log.d("lifecycle","onResume_SPLASH SCREEN ACTIVITY")
    }

    override fun onPause() {
        super.onPause()
        Log.d("lifecycle","onPause_SPLASH SCREEN ACTIVITY")
    }

    override fun onStop() {
        super.onStop()
        Log.d("lifecycle","onStop_SPLASH SCREEN ACTIVITY")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("lifecycle","onRestart_SPLASH SCREEN ACTIVITY")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("lifecycle","onDestroy_SPLASH SCREEN ACTIVITY")
    }
}

