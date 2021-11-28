package com.example.proyekpsi

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyekpsi.adapter.ReminderAdapter
import com.example.proyekpsi.adapter.ReminderClickDeleteInterface
import com.example.proyekpsi.adapter.ReminderClickInterface
import com.example.proyekpsi.viewmodel.ReminderViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ReminderFragment: Fragment(), ReminderClickDeleteInterface,
    ReminderClickInterface {
    lateinit var reminderRV: RecyclerView
    lateinit var addButton: FloatingActionButton
    lateinit var viewModel: ReminderViewModel

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

        reminderRV = view.findViewById(R.id.rvReminder)
        addButton = view.findViewById(R.id.btnAddReminder)

        // Recycler View and Adapter
        reminderRV.layoutManager = LinearLayoutManager(view.context)
        val reminderAdapter = ReminderAdapter(view.context, this, this)
        reminderRV.adapter = reminderAdapter

        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(viewModel.getApplication())).get(ReminderViewModel::class.java)
        viewModel.allReminder.observe(viewLifecycleOwner, { list->
            list?.let {
                reminderAdapter.updateList(it)
            }
        })
        addButton.setOnClickListener {
            val intent = Intent(view.context, AddEditReminderActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }
    }

    override fun onDeleteIconClick(reminder: Reminder) {
        viewModel.deleteReminder(reminder)
        Toast.makeText(view?.context, "${reminder.reminderTitle} Deleted", Toast.LENGTH_LONG).show()
    }

    override fun onReminderClick(reminder: Reminder) {
        val intent = Intent(view?.context, AddEditReminderActivity::class.java)
        intent.putExtra("reminderType", "Edit")
        intent.putExtra("reminderTitle", reminder.reminderTitle)
        intent.putExtra("reminderDescription", reminder.reminderDescription)
        intent.putExtra("reminderID", reminder.id)
        startActivity(intent)
        activity?.finish()
    }
}