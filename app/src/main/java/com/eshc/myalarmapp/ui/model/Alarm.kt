package com.eshc.myalarmapp.ui.model

data class Alarm(
    val id : Int,
    val time : String,
    val title : String,
    val active : Boolean
)