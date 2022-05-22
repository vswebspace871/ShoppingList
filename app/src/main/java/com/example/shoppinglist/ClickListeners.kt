package com.example.shoppinglist

interface  OnGroupClickListener
{
    fun groupClicked(index: Int)
    fun groupLongClicked(index: Int)
}


interface  OnItemClickListener
{
    fun itemClicked(index: Int)
    fun itemLongClicked(index: Int)
}