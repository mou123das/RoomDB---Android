package com.priceline.android_onboarding_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.priceline.android_onboarding_project.data.Address
import com.priceline.android_onboarding_project.data.Person
import com.priceline.android_onboarding_project.data.PersonDatabase
import com.priceline.android_onboarding_project.databinding.ActivityRegisterPageBinding
import com.priceline.android_onboarding_project.databinding.CustomToastBinding
import com.priceline.android_onboarding_project.databinding.DetailsAddedCustomAlertDialogBinding

//REGISTER PAGE ACTIVITY
class RegisterPageActivity : AppCompatActivity() {

    private lateinit var registerPageBinding:ActivityRegisterPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerPageBinding = ActivityRegisterPageBinding.inflate(layoutInflater)
        setContentView(registerPageBinding.root)
        //Click implementation of Register button
        registerPageBinding.btnRegisterButton.setOnClickListener {
            validations()
        }
        Log.d("lifecycle","onCreate_REGISTER PAGE ACTIVITY")
    }

    private fun validations(){
        val firstName = registerPageBinding.etFirstName
        val lastName = registerPageBinding.etLastName
        val emailAddress = registerPageBinding.etEmailAddress
        val phoneNumber = registerPageBinding.etPhoneNumber
        val password = registerPageBinding.etPassword
        val street = registerPageBinding.etStreetAddress
        val city = registerPageBinding.etCityAddress
        val pincode = registerPageBinding.etPincodeAddress
        val confirmPassword = registerPageBinding.etConfirmPassword
        val instance = PersonDatabase.getInstance(this)
        //Validating the extracted fields
        if (firstName.text.isEmpty() || lastName.text.isEmpty() || emailAddress.text.isEmpty() || phoneNumber.text.isEmpty() || password.text.isEmpty() || city.text.isEmpty() || street.text.isEmpty() || pincode.text.isEmpty())
            customToastMaker(R.drawable.wrong, "Fields cannot be empty!")
        else if (emailAddress.text.toString().length >= 7 && password.text.toString().length >= 7 && confirmPassword.text.toString().length >= 7) {
            if (password.text.toString() == confirmPassword.text.toString()) {
                //Validating if newly entered email address already exits in database or not
                val idReturned = instance.personDao().emailValidation(emailAddress.text.toString())
                if (idReturned == 0) {
                    val newPerson=Person(
                        firstName.text.toString().trim(),
                        lastName.text.toString().trim(),
                        emailAddress.text.toString().trim(),
                        phoneNumber.text.toString().trim(),
                        password.text.toString().trim(),
                        Address(
                            street.text.toString().trim(),
                            city.text.toString().trim(),
                            pincode.text.toString().trim().toInt()
                        )
                    )
                    //Inserting new Person data in database
                    val insertedId=instance.personDao().addPerson(newPerson)
                    //If new person successfully inserted in database
                    if(insertedId>0) {
                        newPerson.id=insertedId.toInt()
                        customAlertDialog(newPerson)
                    }
                    else
                        customToastMaker(R.drawable.wrong, "Registration Unsuccessful!")
                } else
                    customToastMaker(R.drawable.wrong, "Email already exists!")
            } else
                customToastMaker(R.drawable.wrong, "Passwords don't match!")
        } else
            customToastMaker(R.drawable.wrong, "Lengths of fields not satisfied!")

        firstName.text.clear()
        lastName.text.clear()
        emailAddress.text.clear()
        phoneNumber.text.clear()
        password.text.clear()
        street.text.clear()
        city.text.clear()
        pincode.text.clear()
        confirmPassword.text.clear()
    }

    private fun customAlertDialog(newPerson:Person) {
        val dialogBuilder = AlertDialog.Builder(this)
        val detailsAddedCustomAlertDialogBinding=DetailsAddedCustomAlertDialogBinding.inflate(layoutInflater)
        dialogBuilder.setView(detailsAddedCustomAlertDialogBinding.root)
        val dialog = dialogBuilder.show()
        detailsAddedCustomAlertDialogBinding.btnCloseButton.setOnClickListener {
            dialog.dismiss()
            val task=Intent() //Pass the data to ShowAllUsersActivity
            task.putExtra("newRecord",newPerson)
            setResult(RESULT_OK, task)
            finish()
        }
    }

    private fun customToastMaker(toastImage: Int, toastText: String) {
        val customToastBinding=CustomToastBinding.inflate(layoutInflater)
        val customToast = Toast(this)
        customToastBinding.customToastMessage.text = toastText
        customToastBinding.customToastImage.setImageResource(toastImage)
        customToast.duration = Toast.LENGTH_LONG
        customToast.view = customToastBinding.root
        customToast.show()
    }

    override fun onStart() {
        super.onStart()
        Log.d("lifecycle","onStart_REGISTER PAGE ACTIVITY")
    }

    override fun onResume() {
        super.onResume()
        Log.d("lifecycle","onResume_REGISTER PAGE ACTIVITY")
    }

    override fun onPause() {
        super.onPause()
        Log.d("lifecycle","onPause_REGISTER PAGE ACTIVITY")
    }

    override fun onStop() {
        super.onStop()
        Log.d("lifecycle","onStop_REGISTER PAGE ACTIVITY")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("lifecycle","onRestart_REGISTER PAGE ACTIVITY")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("lifecycle","onDestroy_REGISTER PAGE ACTIVITY")
    }
}