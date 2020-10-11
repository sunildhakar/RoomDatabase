package com.app.roomexample.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.app.roomexample.R
import com.app.roomexample.models.User
import com.app.roomexample.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*


class UpdateFragment : Fragment() {
    private val args by navArgs<UpdateFragmentArgs>()

    private lateinit var userViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_update, container, false)

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        view.updateFirstName_et.setText(args.currentUser.firstName)
        view.updatelastName_et.setText(args.currentUser.lastName)
        view.updateage_et.setText(args.currentUser.age.toString())

        view.updateBtn.setOnClickListener {
            updateCurrentItem()
        }

        setHasOptionsMenu(true)
        return view
    }

    private fun updateCurrentItem() {
        val firstName = updateFirstName_et.text.toString()
        val lastName = updatelastName_et.text.toString()
        val age = Integer.parseInt(updateage_et.text.toString())

        if (inputCheck(firstName, lastName, updateage_et.text)) {
            val user =
                User(args.currentUser.id, firstName, lastName, Integer.parseInt(age.toString()))
            userViewModel.updateUserData(user)

            Toast.makeText(requireContext(), "successfully updated", Toast.LENGTH_SHORT).show()

            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "please enter value all fields", Toast.LENGTH_SHORT)
                .show()

        }

    }

    private fun inputCheck(firstName: String, lastName: String, age: Editable): Boolean {

        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.delete_item) {
            deleteUser()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteUser() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Delete ${args.currentUser.firstName}?")
        builder.setMessage("Are you sure wanat to delete ${args.currentUser.firstName}")

        builder.setPositiveButton("Yes") { _, _ ->
            userViewModel.deleteUser(args.currentUser)

            Toast.makeText(
                requireContext(),
                "Successfully Deleted ${args.currentUser.firstName}",
                Toast.LENGTH_SHORT
            )
                .show()

            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("No") { _, _ -> }

        builder.create().show()
    }
}