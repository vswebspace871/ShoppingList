package com.example.shoppinglist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.groups.*

class GroupsActivity : AppCompatActivity(), OnGroupClickListener {

    private var groupsAdapter:GroupsAdapter?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.groups)

        groupsRecyclerView.layoutManager = LinearLayoutManager(this)

        AppData.initialize()

        groupsAdapter = GroupsAdapter(AppData.groups, this)
        groupsRecyclerView.adapter = groupsAdapter
    }

    override fun onResume() {
        super.onResume()
        groupsAdapter!!.notifyDataSetChanged()
    }

    fun createNewGroup(v: View) {

        val builder = AlertDialog.Builder(this)
        builder.setTitle("New Group")
        builder.setMessage("Enter a name for new group")

        val myInput = EditText(this)
        myInput.inputType = InputType.TYPE_CLASS_TEXT
        //input field ko dialog box me dikhane ke liye
        builder.setView(myInput)

        builder.setPositiveButton("Save") { _, _ ->
            val groupName: String = myInput.text.toString()
            val newGroup = Group(groupName, mutableListOf())

            AppData.groups.add(newGroup)
            groupsAdapter!!.notifyItemInserted(AppData.groups.count())
        }

        //performing negative action
        builder.setNegativeButton("Cancel") { _, _ ->

        }
        // Create the AlertDialog
        val alertDialog: AlertDialog = builder.create()
        // Set other dialog properties
        alertDialog.setCancelable(false) // idhar udhar click krne se dialog box hatega nahi
        alertDialog.show()
    }

    override fun groupClicked(index: Int) {
       val intent = Intent(this, itemsActivity::class.java)
        intent.putExtra("groupIndex", index)

        startActivity(intent)


        //animation
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }

    override fun groupLongClicked(index: Int) {
        //Delete a Group
        AppData.groups.removeAt(index)
        groupsAdapter!!.notifyItemRemoved(index)
    }
}



