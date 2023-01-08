package com.priceline.android_onboarding_project

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.priceline.android_onboarding_project.data.PersonDatabase
import com.priceline.android_onboarding_project.databinding.ActivityLoginWelcomeBinding

//LOGIN WELCOME ACTIVITY
class LoginWelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val loginWelcomeBinding = ActivityLoginWelcomeBinding.inflate(layoutInflater)
        setContentView(loginWelcomeBinding.root)

        val fetchedRecordID =intent.getIntExtra("Record_ID",-1)
        val instance = PersonDatabase.getInstance(this)
        //Fetching the details of logged in User by querying the database based on ID
        val fetchedRecord=instance.personDao().fetchRecordById(fetchedRecordID)
        val res = resources
        fetchedRecord?.let {
            val welcomeText=SpannableString(String.format(res.getString(R.string.welcome_login_text),it.firstName))
            welcomeText.setSpan(
                ForegroundColorSpan(Color.MAGENTA),
                welcomeText.indexOf(' '), // start
                welcomeText.length, // end
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            welcomeText.setSpan(
                StyleSpan(Typeface.BOLD_ITALIC),
                welcomeText.indexOf(' '),
                welcomeText.length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            welcomeText.setSpan(
                RelativeSizeSpan(1.5f),
                welcomeText.indexOf(' '),
                welcomeText.length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            loginWelcomeBinding.textWelcome.text=welcomeText
            loginWelcomeBinding.textFirstName.text=String.format(res.getString(R.string.first_Name),it.firstName)
            loginWelcomeBinding.textLastName.text=String.format(res.getString(R.string.last_Name),it.lastName)
            loginWelcomeBinding.textEmail.text=String.format(res.getString(R.string.email_address),it.emailAddress)
            loginWelcomeBinding.textPhone.text=String.format(res.getString(R.string.phone_number),it.phoneNumber)
            loginWelcomeBinding.city.text=String.format(res.getString(R.string.city),it.address.city)
            loginWelcomeBinding.street.text=String.format(res.getString(R.string.street),it.address.street)
            loginWelcomeBinding.pincode.text=String.format(res.getString(R.string.pincode),it.address.pincode)
        }
        Log.d("lifecycle","onCreate_LOGIN WELCOME ACTIVITY")
    }

    override fun onStart() {
        super.onStart()
        Log.d("lifecycle","onStart_LOGIN WELCOME ACTIVITY")
    }

    override fun onResume() {
        super.onResume()
        Log.d("lifecycle","onResume_LOGIN WELCOME ACTIVITY")
    }

    override fun onPause() {
        super.onPause()
        Log.d("lifecycle","onPause_LOGIN WELCOME ACTIVITY")
    }

    override fun onStop() {
        super.onStop()
        Log.d("lifecycle","onStop_LOGIN WELCOME ACTIVITY")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("lifecycle","onRestart_LOGIN WELCOME ACTIVITY")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("lifecycle","onDestroy_LOGIN WELCOME ACTIVITY")
    }
}