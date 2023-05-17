package edu.temple.myapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PageViewModel : ViewModel() {

    public val DEFAULT_TITLE = "SuperBrowser"

    private val mutableNumPages = MutableLiveData(1)
    private val mutableCurrentPageTitle = MutableLiveData(DEFAULT_TITLE)

    val numPages: LiveData<Int> get() = mutableNumPages
    val currentPageTitle: LiveData<String> get() = mutableCurrentPageTitle

    fun addPage() {
        mutableNumPages.value = mutableNumPages.value!! + 1
    }


    fun setCurrentPageTitle(pageTitle: String) {
        mutableCurrentPageTitle.value = "$DEFAULT_TITLE - $pageTitle"
    }

    fun setDefaultTitle() {
        mutableCurrentPageTitle.value = DEFAULT_TITLE
    }
}