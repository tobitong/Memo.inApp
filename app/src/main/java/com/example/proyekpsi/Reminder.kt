package com.example.proyekpsi

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reminderTable")
class Reminder
    (
    @ColumnInfo(name = "title")val reminderTitle: String,
    @ColumnInfo(name="description")val reminderDescription: String,
    @ColumnInfo(name = "alarmTime")val reminderAlarm: String
    ) {
        @PrimaryKey(autoGenerate = true)
        var id = 0
}