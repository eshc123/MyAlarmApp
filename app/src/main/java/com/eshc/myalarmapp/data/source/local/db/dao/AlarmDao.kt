package com.eshc.myalarmapp.data.source.local.db.dao

import androidx.room.*
import com.eshc.myalarmapp.data.source.local.db.entity.AlarmEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AlarmDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplaceAlarm(alarmEntity: AlarmEntity) : Long

    @Update
    suspend fun updateAlarm(alarmEntity: AlarmEntity)

    @Query("UPDATE alarm_table SET is_active = NOT is_active WHERE alarm_id = :alarmId")
    suspend fun updateAlarmIsActive(alarmId : Int)

    @Query("SELECT * FROM alarm_table")
    fun getAlarms() : Flow<List<AlarmEntity>>

    @Query("SELECT * FROM alarm_table WHERE alarm_id = :alarmId")
    suspend fun getAlarmById(alarmId : Int) : AlarmEntity
}