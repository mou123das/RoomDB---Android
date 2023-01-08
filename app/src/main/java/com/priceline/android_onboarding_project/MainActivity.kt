package com.priceline.android_onboarding_project

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.priceline.android_onboarding_project.data.PersonDatabase
import com.priceline.android_onboarding_project.databinding.CustomToastBinding
import com.priceline.android_onboarding_project.databinding.LoginPageBinding

//LOGIN PAGE ACTIVITY
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val loginPageBinding = LoginPageBinding.inflate(layoutInflater)
        setContentView(loginPageBinding.root)
        val emailAddressValue = loginPageBinding.etEmailAddress
        val passwordValue = loginPageBinding.etPassword

        //Click implementation of Register button
        loginPageBinding.btnRegisterButton.setOnClickListener {
            val intent = Intent(this, RegisterPageActivity::class.java)
            startActivity(intent)
        }

        val instance = PersonDatabase.getInstance(this)

        loginPageBinding.btnLoginButton.setOnClickListener {

            if (emailAddressValue.text.isEmpty() && passwordValue.text.isEmpty())
                customToastMaker(R.drawable.wrong, "Fields cannot be empty!")

            else {
                //fetching the record after validating the data entered with the record's data
                val fetchedRecord = instance.personDao().fetchRecordWithValidation(emailAddressValue.text.toString().trim(), passwordValue.text.toString().trim())
                if (fetchedRecord != null) {
                    customToastMaker(R.drawable.correct, "Login is Successful!")
                    val intent = Intent(this, LoginWelcomeActivity::class.java)
                    intent.putExtra("Record_ID", fetchedRecord.id)
                    startActivity(intent)
                } else
                    customToastMaker(R.drawable.wrong, "Invalid Credentials!")
            }
            emailAddressValue.text.clear()
            passwordValue.text.clear()
        }

        //Click implementation of Login button
        loginPageBinding.btnShowAllUsersButton.setOnClickListener{
            val intent = Intent(this, ShowAllUsersActivity::class.java)
            startActivity(intent)
        }
        Log.d("lifecycle","onCreate_LOGIN PAGE ACTIVITY")
    }

    private fun customToastMaker(toastImage: Int, toastText: String) {
        val customToast = Toast(this)
        val customToastBinding = CustomToastBinding.inflate(layoutInflater)
        customToastBinding.customToastMessage.text = toastText
        customToastBinding.customToastImage.setImageResource(toastImage)
        customToast.duration = Toast.LENGTH_LONG
        customToast.view = customToastBinding.root
        customToast.show()
    }

    override fun onStart() {
        super.onStart()
        Log.d("lifecycle","onStart_LOGIN PAGE ACTIVITY")
    }

    override fun onResume() {
        super.onResume()
        Log.d("lifecycle","onResume_LOGIN PAGE ACTIVITY")
    }

    override fun onPause() {
        super.onPause()
        Log.d("lifecycle","onPause_LOGIN PAGE ACTIVITY")
    }

    override fun onStop() {
        super.onStop()
        Log.d("lifecycle","onStop_LOGIN PAGE ACTIVITY")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("lifecycle","onRestart_LOGIN PAGE ACTIVITY")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("lifecycle","onDestroy_LOGIN PAGE ACTIVITY")
    }
}





//setContentView(R.layout.activity_main_relative_layout)
//setContentView(R.layout.activity_main_linear_layout_1)
//setContentView(R.layout.activity_main_linear_layout_2)
//setContentView(R.layout.activity_main_constraint_layout)