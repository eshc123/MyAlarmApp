package com.eshc.myalarmapp.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DetailViewModel : ViewModel() {

    val title = MutableLiveData<String>()

    val hour = MutableLiveData<Int>()

    val minute = MutableLiveData<Int>()
}