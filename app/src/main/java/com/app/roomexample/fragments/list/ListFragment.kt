package com.app.roomexample.fragments.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.roomexample.R
import com.app.roomexample.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_list.view.*


class ListFragment : Fragment() {
    private lateinit var userViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)


        val adapter = ListAdapter()
        val recyclerView = view.recylerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        userViewModel.readAllData.observe(viewLifecycleOwner, Observer { user ->
            adapter.setData(user)
        })


        view.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }
        setHasOptionsMenu(true)

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.delete_item) {
            deleteAllUser()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllUser() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Delete All Users?")
        builder.setMessage("Are you sure want to delete all users")

        builder.setPositiveButton("Yes") { _, _ ->
            userViewModel.deleteAllUser()

            Toast.makeText(
                requireContext(),
                "Successfully Deleted All Users",
                Toast.LENGTH_SHORT
            )
                .show()

        }
        builder.setNegativeButton("No") { _, _ -> }

        builder.create().show()
    }


}