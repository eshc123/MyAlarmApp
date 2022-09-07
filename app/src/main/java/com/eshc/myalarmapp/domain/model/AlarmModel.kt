package com.eshc.myalarmapp.domain.model

data class AlarmModel(
    val id : Int? = null,
    val time : String,
    val title : String,
    val active : Boolean
)