package com.eshc.myalarmapp.data.source

import com.eshc.myalarmapp.domain.model.AlarmModel
import kotlinx.coroutines.flow.Flow

interface AlarmDataSource {

    fun getAlarms() : Flow<List<AlarmModel>>

    suspend fun insertAlarm(alarm : AlarmModel)

    suspend fun updateAlarm(alarm : AlarmModel)

    suspend fun updateAlarmIsActive(alarmId : Int)
}