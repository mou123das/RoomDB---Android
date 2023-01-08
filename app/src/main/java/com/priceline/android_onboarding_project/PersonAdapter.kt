package com.priceline.android_onboarding_project

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.priceline.android_onboarding_project.data.Person
import com.priceline.android_onboarding_project.databinding.TemplateBinding
import java.util.Collections

class PersonAdapter(private var allRecords: MutableList<Person>, private  val clickListener: OnItemClickListener) : RecyclerView.Adapter<PersonAdapter.MyViewHolder>() {

    interface OnItemClickListener {
        fun onDeleteBtnClick(position: Int)
        fun onEditBtnClick(position: Int)
    }

    class MyViewHolder(templateBinding: TemplateBinding):RecyclerView.ViewHolder(templateBinding.root) {
        val firstName : TextView=templateBinding.textFirstName
        val lastName : TextView=templateBinding.textLastName
        val emailAddress : TextView=templateBinding.textEmail
        val phoneNumber : TextView=templateBinding.textPhone
        val street : TextView=templateBinding.street
        val city : TextView=templateBinding.city
        val pincode : TextView=templateBinding.pincode
        val editButton: Button=templateBinding.btnEdit
        val deleteButton: Button=templateBinding.btnDelete
    }

    fun remove(position: Int){
        allRecords.removeAt(position)
        notifyItemRemoved(position)
    }

    fun insert(person: Person){
        allRecords.add(0,person)
        notifyItemInserted(0)
    }

    fun returnPersonByPosition(position: Int):Person{
        return allRecords[position]
    }

    fun updateUser(updatedPerson: Person,position: Int){
        allRecords[position] = updatedPerson
        notifyItemChanged(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       // val template= LayoutInflater.from(parent.context).inflate(R.layout.template, parent, false) // inflating the layout
        val templateBinding = TemplateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(templateBinding)
    }

    override fun onBindViewHolder(holder:  MyViewHolder, position: Int) {
        val persons=allRecords[position]
        holder.apply {
            firstName.text="FIRSTNAME: "+persons.firstName
            lastName.text="LAST NAME: "+persons.lastName
            emailAddress.text="EMAIL ADDRESS: "+persons.emailAddress
            phoneNumber.text="PHONE NUMBER: "+persons.phoneNumber
            street.text="STREET: "+persons.address.street
            city.text="CITY: "+persons.address.city
            pincode.text="PINCODE: "+persons.address.pincode.toString()
        }
        holder.editButton.setOnClickListener{
            clickListener.onEditBtnClick(holder.adapterPosition)
        }
        holder.deleteButton.setOnClickListener{
            clickListener.onDeleteBtnClick(holder.adapterPosition)
        }
    }

    override fun getItemCount(): Int {
        return allRecords.size
    }
}
