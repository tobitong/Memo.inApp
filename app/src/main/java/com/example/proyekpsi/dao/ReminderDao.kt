package com.example.proyekpsi.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.proyekpsi.Reminder

@Dao
interface ReminderDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(reminder: Reminder)

    @Update
    suspend fun update(reminder: Reminder)

    @Delete
    suspend fun delete(reminder: Reminder)

    @Query("Select * from reminderTable order by id ASC")
    fun getAllReminder(): LiveData<List<Reminder>>
}