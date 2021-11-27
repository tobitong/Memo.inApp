package com.example.proyekpsi.repository

import androidx.lifecycle.LiveData
import com.example.proyekpsi.Reminder
import com.example.proyekpsi.dao.ReminderDao

class ReminderRepository(private val reminderDao: ReminderDao) {

    val allNotes: LiveData<List<Reminder>> = reminderDao.getAllReminder()

    suspend fun insert(reminder: Reminder){
        reminderDao.insert(reminder)
    }

    suspend fun delete(reminder: Reminder){
        reminderDao.delete(reminder)
    }

    suspend fun update(reminder: Reminder){
        reminderDao.update(reminder)
    }
}