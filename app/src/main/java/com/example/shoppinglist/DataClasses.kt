package com.example.shoppinglist

data class Item(val name:String, var completed: Boolean)



data class Group (val name: String, var items: MutableList<Item>)