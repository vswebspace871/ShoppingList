package com.example.shoppinglist

class AppData
{
    companion object DataHolder
    {
        var groups: MutableList<Group> = mutableListOf()

        fun initialize ()
        {
            val item1= Item ("Bread", false)// object creation
            val item2= Item ("Milk", true)// object creation
            val item3= Item ("Tap to cross", true)// object creation
            val item4= Item ("Long Press to Delete", true)// object creation

            val group1 = Group("Home", mutableListOf(item1, item2))
            val group2 = Group("Training", mutableListOf(item3, item4))

            groups = mutableListOf(group1, group2)

        }
    }
}