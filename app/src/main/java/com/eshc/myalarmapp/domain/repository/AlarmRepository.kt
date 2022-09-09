package com.eshc.myalarmapp.domain.repository

import com.eshc.myalarmapp.domain.model.AlarmModel
import kotlinx.coroutines.flow.Flow

interface AlarmRepository {

    fun getAlarms() : Flow<List<AlarmModel>>

    suspend fun getAlarmById(alarmId : Int) : AlarmModel

    suspend fun insertAlarm(alarm : AlarmModel)

    suspend fun updateAlarm(alarm : AlarmModel)

    suspend fun updateAlarmIsActive(alarmId : Int)
}