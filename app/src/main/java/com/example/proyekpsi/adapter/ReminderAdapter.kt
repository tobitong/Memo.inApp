package com.example.proyekpsi.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.proyekpsi.R
import com.example.proyekpsi.Reminder

class ReminderAdapter(
    val context: Context,
    val reminderClickInterface: ReminderClickInterface,
    val reminderClickDeleteInterface: ReminderClickDeleteInterface
): RecyclerView.Adapter<ReminderAdapter.ViewHolder>() {

    private val allReminder = ArrayList<Reminder>()
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val reminderTV = itemView.findViewById<TextView>(R.id.tvReminderTitle)
        val alarmTV = itemView.findViewById<TextView>(R.id.tvReminderAlarm)
        val deleteIV = itemView.findViewById<ImageView>(R.id.deleteButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.reminder_item,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.reminderTV.setText(allReminder.get(position).reminderTitle)
        holder.alarmTV.setText(allReminder.get(position).reminderAlarm)

        holder.deleteIV.setOnClickListener {
            reminderClickDeleteInterface.onDeleteIconClick(allReminder.get(position))
        }

        holder.itemView.setOnClickListener {
            reminderClickInterface.onReminderClick(allReminder.get(position))
        }
    }

    override fun getItemCount(): Int {
        return allReminder.size
    }

    fun updateList(newList: List<Reminder>) {
        allReminder.clear()
        allReminder.addAll(newList)
        notifyDataSetChanged()
    }
}

interface ReminderClickDeleteInterface {
    fun onDeleteIconClick(reminder: Reminder)
}

interface ReminderClickInterface {
    fun onReminderClick(reminder: Reminder)
}