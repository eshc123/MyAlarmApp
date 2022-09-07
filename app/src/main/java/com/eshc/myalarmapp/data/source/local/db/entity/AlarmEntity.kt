package com.eshc.myalarmapp.data.source.local.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.eshc.myalarmapp.domain.model.AlarmModel

@Entity(tableName = "alarm_table")
data class AlarmEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "alarm_id") val id : Int = 0,
    @ColumnInfo(name = "title") val title : String,
    @ColumnInfo(name = "time") val time : String,
    @ColumnInfo(name = "active") val active : Boolean
)

fun AlarmEntity.toAlarmModel() : AlarmModel {
    return AlarmModel(
        id = id,
        title = title,
        time = time,
        active = active
    )
}

fun AlarmModel.toAlarmEntity() : AlarmEntity {
    return AlarmEntity(
        title = title,
        time = time,
        active = active
    )
}