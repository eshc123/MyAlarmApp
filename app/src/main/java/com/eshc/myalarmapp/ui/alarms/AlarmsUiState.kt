package com.eshc.myalarmapp.ui.alarms

import com.eshc.myalarmapp.ui.model.AlarmUIModel

data class AlarmsUiState (
    val alarms : List<AlarmUIModel>,
    val isLoading : Boolean = false,
    val addAlarmShown : Boolean? = null,
    val editAlarmId : Int? = null
)