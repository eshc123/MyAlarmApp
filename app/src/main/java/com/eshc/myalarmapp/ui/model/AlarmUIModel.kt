package com.eshc.myalarmapp.ui.model

import com.eshc.myalarmapp.domain.model.AlarmModel

data class AlarmUIModel(
    val id : Int,
    val time : String,
    val title : String,
    val isActive : Boolean,
    val onActive : () -> Unit
)

fun AlarmModel.toAlarmUIModel(
    onActive: () -> Unit
) : AlarmUIModel {
    return AlarmUIModel(
        id = id ?: -1,
        time = time,
        title = title,
        isActive = isActive,
        onActive = onActive
    )
}

fun AlarmUIModel.toAlarmModel() : AlarmModel {
    return AlarmModel(
        id = id,
        time = time,
        title = title,
        isActive = isActive
    )
}