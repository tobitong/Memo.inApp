package com.example.proyekpsi.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.proyekpsi.Reminder
import com.example.proyekpsi.database.ReminderDatabase
import com.example.proyekpsi.repository.ReminderRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ReminderViewModel(application: Application): AndroidViewModel(application) {
    val allReminder: LiveData<List<Reminder>>
    val repository: ReminderRepository

    init {
        val dao = ReminderDatabase.getDatabase(application).getReminderDao()
        repository = ReminderRepository(dao)
        allReminder = repository.allNotes
    }

    fun deleteReminder(reminder: Reminder) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(reminder)
    }

    fun updateReminder(reminder: Reminder) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(reminder)
    }

    fun addReminder(reminder: Reminder) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(reminder)
    }
}