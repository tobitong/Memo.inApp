package com.example.proyekpsi.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.proyekpsi.R
import com.example.proyekpsi.entities.Reminder

class ReminderAdapter(
    val context: Context,
    val reminderClickDeleteInterface: ReminderClickDeleteInterface,
    val reminderClickInterface: ReminderClickInterface
) :
    RecyclerView.Adapter<ReminderAdapter.ViewHolder>() {

    // on below line we are creating a
    // variable for our all notes list.
    private val allReminders = ArrayList<Reminder>()

    // on below line we are creating a view holder class.
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // on below line we are creating an initializing all our
        // variables which we have added in layout file.
        val noteTV = itemView.findViewById<TextView>(R.id.tvReminderNote)
        val dateTV = itemView.findViewById<TextView>(R.id.tvReminderDate)
        val deleteIV = itemView.findViewById<ImageView>(R.id.ivReminderDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflating our layout file for each item of recycler view.
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.reminder_item,
            parent, false
        )
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // on below line we are setting data to item of recycler view.
        holder.noteTV.setText(allReminders.get(position).reminderTitle)
        holder.dateTV.setText("Last Updated : " + allReminders.get(position).timeStamp)
        // on below line we are adding click listener to our delete image view icon.
        holder.deleteIV.setOnClickListener {
            // on below line we are calling a note click
            // interface and we are passing a position to it.
            reminderClickDeleteInterface.onDeleteIconClick(allReminders.get(position))
        }

        // on below line we are adding click listener
        // to our recycler view item.
        holder.itemView.setOnClickListener {
            // on below line we are calling a note click interface
            // and we are passing a position to it.
            reminderClickInterface.onReminderClick(allReminders.get(position))
        }
    }

    override fun getItemCount(): Int {
        // on below line we are
        // returning our list size.
        return allReminders.size
    }

    // below method is use to update our list of notes.
    fun updateList(newList: List<Reminder>) {
        // on below line we are clearing
        // our notes array list
        allReminders.clear()
        // on below line we are adding a
        // new list to our all notes list.
        allReminders.addAll(newList)
        // on below line we are calling notify data
        // change method to notify our adapter.
        notifyDataSetChanged()
    }
}

interface ReminderClickDeleteInterface {
    // creating a method for click
    // action on delete image view.
    fun onDeleteIconClick(reminder: Reminder)
}

interface ReminderClickInterface {
    // creating a method for click action
    // on recycler view item for updating it.
    fun onReminderClick(reminder: Reminder)
}