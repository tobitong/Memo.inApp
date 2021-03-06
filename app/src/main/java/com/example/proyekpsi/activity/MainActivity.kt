package com.example.proyekpsi.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.proyekpsi.R
import com.example.proyekpsi.fragment.NotesFragment
import com.example.proyekpsi.fragment.ReminderFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val notesFragment = NotesFragment()
        val reminderFragment = ReminderFragment()

        setCurrentFragment(notesFragment)

        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.notes ->setCurrentFragment(notesFragment)
                R.id.reminder ->setCurrentFragment(reminderFragment)
            }
            true
        }
    }

    private fun setCurrentFragment(fragment:Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentView,fragment)
            addToBackStack(fragment.javaClass.simpleName)
            commit()
        }
}