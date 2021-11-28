package com.example.proyekpsi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.example.proyekpsi.viewmodel.ReminderViewModel
import java.text.SimpleDateFormat
import java.util.*

class AddEditReminderActivity : AppCompatActivity() {
    lateinit var reminderTitleEdit: EditText
    lateinit var reminderDesciptionEdit: EditText
    lateinit var addUpdateButton: Button
    lateinit var viewModel: ReminderViewModel
    var reminderId = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit_reminder)

        reminderTitleEdit = findViewById(R.id.etReminderTitle)
        reminderDesciptionEdit = findViewById(R.id.etReminderDescription)
        addUpdateButton = findViewById(R.id.btnAddUpdate)
        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(ReminderViewModel::class.java)

        val reminderType = intent.getStringExtra("reminderType")
        if(reminderType.equals("Edit")){
            val reminderTitle = intent.getStringExtra("reminderTitle")
            val reminderDesc = intent.getStringExtra("reminderDescription")
            reminderId  = intent.getIntExtra("reminderID", -1)
            addUpdateButton.setText("Update Note")
            reminderTitleEdit.setText(reminderTitle)
            reminderDesciptionEdit.setText(reminderDesc)
        } else{
            addUpdateButton.setText("Save Reminder")
        }

        addUpdateButton.setOnClickListener {
            val reminderTitle = reminderTitleEdit.text.toString()
            val reminderDescription = reminderDesciptionEdit.text.toString()

            if(reminderType.equals("Edit")){
                if(reminderTitle.isNotEmpty() && reminderDescription.isNotEmpty()){
                    val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm")
                    val currentDate:String = sdf.format(Date())
                    val updateReminder = Reminder(reminderTitle, reminderDescription, currentDate)
                    updateReminder.id = reminderId
                    viewModel.updateReminder(updateReminder)
                    Toast.makeText(this, "Reminder Updated..", Toast.LENGTH_SHORT).show()
                }
            } else {
                if(reminderTitle.isNotEmpty() && reminderDescription.isNotEmpty()) {
                    val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm")
                    val currentDate:String = sdf.format(Date())
                    viewModel.addReminder(Reminder(reminderTitle, reminderDescription, currentDate))
                    Toast.makeText(this, "Reminder Added", Toast.LENGTH_SHORT).show()
                }
            }
            startActivity(Intent(applicationContext, MainActivity::class.java))
            this.finish()
        }
    }
}