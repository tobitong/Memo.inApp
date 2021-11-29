package com.example.proyekpsi.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyekpsi.R
import com.example.proyekpsi.activity.AddEditReminderActivity
import com.example.proyekpsi.adapter.ReminderAdapter
import com.example.proyekpsi.adapter.ReminderClickDeleteInterface
import com.example.proyekpsi.adapter.ReminderClickInterface
import com.example.proyekpsi.entities.Reminder
import com.example.proyekpsi.viewmodel.ReminderViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ReminderFragment: Fragment(), ReminderClickDeleteInterface,
    ReminderClickInterface {
    lateinit var viewModal: ReminderViewModel
    lateinit var reminderRV: RecyclerView
    lateinit var addFAB: FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_reminder, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // on below line we are initializing
        // all our variables.
        reminderRV = view.findViewById(R.id.rvReminder)
        addFAB = view.findViewById(R.id.btnAddReminder)

        // on below line we are setting layout
        // manager to our recycler view.
        reminderRV.layoutManager = LinearLayoutManager(view.context)

        // on below line we are initializing our adapter class.
        val reminderRVAdapter = ReminderAdapter(view.context, this, this)

        // on below line we are setting
        // adapter to our recycler view.
        reminderRV.adapter = reminderRVAdapter

        // on below line we are
        // initializing our view modal.
        viewModal = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        ).get(ReminderViewModel::class.java)

        // on below line we are calling all notes method
        // from our view modal class to observer the changes on list.
        viewModal.allReminder.observe(requireActivity(), Observer { list ->
            list?.let {
                // on below line we are updating our list.
                reminderRVAdapter.updateList(it)
            }
        })
        addFAB.setOnClickListener {
            // adding a click listener for fab button
            // and opening a new intent to add a new note.
            val intent = Intent(activity, AddEditReminderActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }

    }

    override fun onReminderClick(reminder: Reminder) {
        // opening a new intent and passing a data to it.
        val intent = Intent(activity, AddEditReminderActivity::class.java)
        intent.putExtra("reminderType", "Edit")
        intent.putExtra("reminderTitle", reminder.reminderTitle)
        intent.putExtra("reminderDescription", reminder.reminderDescription)
        intent.putExtra("reminderId", reminder.id)
        startActivity(intent)
        requireActivity().finish()
    }

    override fun onDeleteIconClick(reminder: Reminder) {
        // in on note click method we are calling delete
        // method from our view modal to delete our not.
        viewModal.deleteReminder(reminder)
        // displaying a toast message
        Toast.makeText(activity, "${reminder.reminderTitle} Deleted", Toast.LENGTH_LONG).show()
    }
}