package com.eshc.myalarmapp.data.repository

import com.eshc.myalarmapp.data.source.AlarmDataSource
import com.eshc.myalarmapp.domain.model.AlarmModel
import com.eshc.myalarmapp.domain.repository.AlarmRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AlarmRepositoryImpl @Inject constructor(
    private val alarmDataSource: AlarmDataSource
) : AlarmRepository {

    override fun getAlarms(): Flow<List<AlarmModel>> {
        return alarmDataSource.getAlarms()
    }

    override suspend fun getAlarmById(alarmId: Int): AlarmModel {
        return alarmDataSource.getAlarmById(alarmId)
    }

    override suspend fun insertAlarm(alarm: AlarmModel) {
        alarmDataSource.insertAlarm(alarm)
    }

    override suspend fun updateAlarm(alarm: AlarmModel) {
        alarmDataSource.updateAlarm(alarm)
    }

    override suspend fun updateAlarmIsActive(alarmId: Int) {
        alarmDataSource.updateAlarmIsActive(alarmId)
    }
}