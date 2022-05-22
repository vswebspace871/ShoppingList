package com.example.shoppinglist

import android.app.Activity
import android.hardware.input.InputManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.inputmethod.InputMethod
import android.view.inputmethod.InputMethodManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_items.*

class itemsActivity : AppCompatActivity(), OnItemClickListener {

    lateinit var thisGroup:Group
    var itemsAdapter:ItemsAdapter? = null

    override fun itemClicked(index: Int) {
        thisGroup.items[index].completed = !thisGroup.items[index].completed
        itemsAdapter!!.notifyDataSetChanged()
    }

    override fun itemLongClicked(index: Int) {
        thisGroup.items.removeAt(index)
        itemsAdapter!!.notifyItemRemoved(index)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_items)

        var selectedIndex = intent.getIntExtra("groupIndex", 0)
        thisGroup = AppData.groups[selectedIndex]

        toolbarTitle.text = thisGroup.name



        // for white arrow use this theme in toolbar
        // android:theme="@style/ThemeOverlay.AppCompat.Dark.ActionBar"


        itemsRecyclerView.layoutManager = LinearLayoutManager(this)
        itemsAdapter = ItemsAdapter(thisGroup, this)
        itemsRecyclerView.adapter = itemsAdapter




        setSupportActionBar(toolbar)
        //toolbar par back button lagane ke liye
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        //hide application name
        supportActionBar!!.setDisplayShowTitleEnabled(false)

        newItemEditText.setOnKeyListener { view, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER)
            {
                if (event.action == KeyEvent.ACTION_DOWN) // if button pressed

                {
                    //get value from edittext
                    val name:String = newItemEditText.text.toString()
                    val item = Item(name, false)
                    thisGroup.items.add(item)
                    itemsAdapter!!.notifyItemInserted(thisGroup.items.count())
                    newItemEditText.text.clear() // edittext wapis se khali ho jayega

                    //keyboard apne aap minimize ho jana chaiye,
                    val inputManager:InputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as
                             InputMethodManager

                    inputManager.hideSoftInputFromWindow(view.windowToken, 0)
                    //keyboard apne aap minimize ho jana chaiye,
                }
            }
            false
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }


}