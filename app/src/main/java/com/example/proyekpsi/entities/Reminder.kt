package com.example.proyekpsi.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "reminderTable")
class Reminder(
    @ColumnInfo(name = "title") val reminderTitle: String,
    @ColumnInfo(name = "description") val reminderDescription: String,
    @ColumnInfo(name = "timestamp") val timeStamp: String
) {
    @PrimaryKey(autoGenerate = true)
    var id = 0
}