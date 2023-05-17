package edu.temple.desserrtapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ItemViewModel : ViewModel() {
    private val selectedItem = MutableLiveData<Item>()
    private val items = MutableLiveData<Array<Item>>()

    fun setSelectedItem(item: Item) {
        selectedItem.value = item
    }

    fun getSelectedItem() : LiveData<Item> = selectedItem

    fun setItems(items : Array<Item>) {
        this.items.value = items
    }

    fun getItems() : LiveData<Array<Item>> = items
}