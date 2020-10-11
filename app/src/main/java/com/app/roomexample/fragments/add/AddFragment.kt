package com.app.roomexample.fragments.add

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.app.roomexample.R
import com.app.roomexample.viewmodel.UserViewModel
import com.app.roomexample.models.User
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*


class AddFragment : Fragment() {
    private lateinit var userViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add, container, false)
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        view.button.setOnClickListener {
            insertDataToDatabase()
        }
        return view
    }

    private fun insertDataToDatabase() {
        val firstName = addFirstName_et.text.toString()
        val lastName = lastName_et.text.toString()
        val age = age_et.text



        if (inputCheck(firstName, lastName, age)) {
            val user = User(0, firstName, lastName, Integer.parseInt(age.toString()))
            userViewModel.addUser(user)

            Toast.makeText(requireContext(), "successfully added", Toast.LENGTH_SHORT).show()

            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(), "please enter value all fields", Toast.LENGTH_SHORT).show()

        }

    }

    private fun inputCheck(firstName: String, lastName: String, age: Editable): Boolean {

        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())

    }


}