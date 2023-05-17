package edu.temple.myapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BrowserViewModel : ViewModel() {

    private val pages = ArrayList<String>()
    private val pagesLiveData = MutableLiveData<List<String>>(pages)

    private val currentPageIndex = MutableLiveData<Int>(0)

    fun getPageCount() : Int = if(pagesLiveData.value == null) 0 else pagesLiveData.value!!.size

    fun getPages() : LiveData<List<String>> = pagesLiveData

    fun getPageIndex() : LiveData<Int> = currentPageIndex

    fun setPageIndex(pos: Int) {
        currentPageIndex.value = pos
    }

    fun addNewPage() {
        pages.add("")
        pagesLiveData.value = pages
    }

    fun setTitle(pos: Int, title: String) {
        pages[pos] = title
        pagesLiveData.value = pages
    }

}