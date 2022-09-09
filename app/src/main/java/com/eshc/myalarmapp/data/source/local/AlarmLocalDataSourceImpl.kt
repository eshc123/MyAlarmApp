package com.eshc.myalarmapp.data.source.local

import com.eshc.myalarmapp.data.source.AlarmDataSource
import com.eshc.myalarmapp.data.source.local.db.dao.AlarmDao
import com.eshc.myalarmapp.data.source.local.db.entity.toAlarmEntity
import com.eshc.myalarmapp.data.source.local.db.entity.toAlarmModel
import com.eshc.myalarmapp.domain.model.AlarmModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AlarmLocalDataSourceImpl @Inject constructor(
    private val alarmDao: AlarmDao
) : AlarmDataSource {
    override fun getAlarms(): Flow<List<AlarmModel>> {
        return alarmDao.getAlarms().map { list ->
            list.map {
                it.toAlarmModel()
            }
        }
    }

    override suspend fun insertAlarm(alarm: AlarmModel) {
        alarmDao.insertOrReplaceAlarm(alarm.toAlarmEntity())
    }

    override suspend fun updateAlarm(alarm: AlarmModel) {
        alarmDao.updateAlarm(alarm.toAlarmEntity())
    }

    override suspend fun updateAlarmIsActive(alarmId: Int) {
        alarmDao.updateAlarmIsActive(alarmId)
    }

    override suspend fun getAlarmById(alarmId: Int): AlarmModel {
        return alarmDao.getAlarmById(alarmId).toAlarmModel()
    }
}