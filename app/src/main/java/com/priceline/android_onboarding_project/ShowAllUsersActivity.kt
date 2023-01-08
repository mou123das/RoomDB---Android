package com.priceline.android_onboarding_project

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.priceline.android_onboarding_project.data.Person
import com.priceline.android_onboarding_project.data.PersonDatabase
import com.priceline.android_onboarding_project.databinding.ActivityShowAllUsersBinding
import com.priceline.android_onboarding_project.databinding.CustomToastBinding
import com.priceline.android_onboarding_project.databinding.UpdateDetailsBinding

//SHOW ALL USERS ACTIVITY
class ShowAllUsersActivity : AppCompatActivity(){

    private lateinit var adapter: PersonAdapter
    private lateinit var activityResultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val showAllUsersBinding = ActivityShowAllUsersBinding.inflate(layoutInflater)
        setContentView(showAllUsersBinding.root)
        val recyclerView = showAllUsersBinding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        val instance = PersonDatabase.getInstance(this)
        //Fetching list of users currently residing in database
        val allRecords = instance.personDao().fetchAllPersons().toMutableList()
        //Setting up the adapter
        adapter = PersonAdapter(allRecords, referenceItemClickListener)
        recyclerView.adapter = adapter

        if(adapter.itemCount==0)
            customToastMaker(R.drawable.wrong, "No records are there to be displayed")

        //To add a new Person
        showAllUsersBinding.btnNewPerson.setOnClickListener{
            val intent = Intent(this, RegisterPageActivity::class.java)
            activityResultLauncher.launch(intent)
        }

        activityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->

            if (result.resultCode == RESULT_OK) {
                result.data?.let { data ->
                    val newRecord = data.getParcelableExtra<Person>("newRecord")
                    newRecord?.let {
                        adapter.insert(it)
                    }
                }
            }
        }
        Log.d("lifecycle","onCreate_SHOW ALL USERS ACTIVITY")
    }

    private val referenceItemClickListener = object:PersonAdapter.OnItemClickListener{

        override fun onDeleteBtnClick(position: Int) {
            val instance = PersonDatabase.getInstance(this@ShowAllUsersActivity)
            val result = instance.personDao().deletePerson(adapter.returnPersonByPosition(position))
            if (result != 0) {
                adapter.remove(position)
                customToastMaker(R.drawable.correct, "Record has been deleted successfully!",0)
                if(adapter.itemCount==0)
                    customToastMaker(R.drawable.wrong, "No records are there to be displayed")
            } else
                customToastMaker(R.drawable.wrong, "Record deletion unsuccessful!")
        }

        override fun onEditBtnClick(position: Int) {
            customAlertDialog(position)
        }
    }

    private fun customAlertDialog(position: Int) {
        val updateDetailsBinding = UpdateDetailsBinding.inflate(layoutInflater,null,false)
        val dialogBuilder = AlertDialog.Builder(this)
        val currentItem = adapter.returnPersonByPosition(position)
        updateDetailsBinding.apply {
            etFirstName.setText(currentItem.firstName)
            etLastName.setText(currentItem.lastName)
            etEmailAddress.setText(currentItem.emailAddress)
            etEmailAddress.setTextColor(resources.getColor(R.color.grey))
            etPhoneNumber.setText(currentItem.phoneNumber)
            etStreetAddress.setText(currentItem.address.street)
            etCityAddress.setText(currentItem.address.city)
            etPincodeAddress.setText(currentItem.address.pincode.toString())
        }
         dialogBuilder.setView(updateDetailsBinding.root)

         dialogBuilder.setNegativeButton("CLOSE",null)
         val dialog = dialogBuilder.show()
        //Click implementation of update button
         updateDetailsBinding.btnUpdateButton.setOnClickListener {
            dialog.dismiss()
            updateDetailsBinding.apply {
                currentItem.firstName=etFirstName.text.toString().trim()
                currentItem.lastName=etLastName.text.toString().trim()
                currentItem.phoneNumber=etPhoneNumber.text.toString().trim()
                currentItem.address.city=etCityAddress.text.toString().trim()
                currentItem.address.street=etStreetAddress.text.toString().trim()
                currentItem.address.pincode=etPincodeAddress.text.toString().trim().toInt()
            }
            updateDetailsOfRecord(currentItem,position)
        }
    }

    private fun updateDetailsOfRecord(currentItem: Person,position: Int) {
        val instance = PersonDatabase.getInstance(this)
        val result = instance.personDao().updateDetails(currentItem)
        if (result != 0) {
            val returnedRecord = instance.personDao().fetchRecordById(currentItem.id)
            if (returnedRecord != null) {
                adapter.updateUser(returnedRecord,position)
            }
            customToastMaker(R.drawable.correct, "Record has been updated successfully!")
        } else
            customToastMaker(R.drawable.wrong, "Record couldn't be updated!")
    }

    private fun customToastMaker(toastImage: Int, toastText: String, toastLength:Int=1) {
        val customToastBinding = CustomToastBinding.inflate(layoutInflater)
        val customToast = Toast(this)
        customToastBinding.customToastMessage.text = toastText
        customToastBinding.customToastImage.setImageResource(toastImage)
        if(toastLength==1)
            customToast.duration = Toast.LENGTH_LONG
        else
            customToast.duration = Toast.LENGTH_SHORT
        customToast.view = customToastBinding.root
        customToast.show()
    }
    
    override fun onStart() {
        super.onStart()
        Log.d("lifecycle","onStart_SHOW ALL USERS ACTIVITY")
    }

    override fun onResume() {
        super.onResume()
        Log.d("lifecycle","onResume_SHOW ALL USERS ACTIVITY")
    }

    override fun onPause() {
        super.onPause()
        Log.d("lifecycle","onPause_SHOW ALL USERS ACTIVITY")
    }

    override fun onStop() {
        super.onStop()
        Log.d("lifecycle","onStop_SHOW ALL USERS ACTIVITY")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("lifecycle","onRestart_SHOW ALL USERS ACTIVITY")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("lifecycle","onDestroy_SHOW ALL USERS ACTIVITY")
    }
}
