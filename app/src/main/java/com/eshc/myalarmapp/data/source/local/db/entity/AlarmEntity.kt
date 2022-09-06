package com.eshc.myalarmapp.data.source.local.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "alarm_table")
data class AlarmEntity(
    @PrimaryKey
    @ColumnInfo(name = "alarm_id") val id : Int,
    @ColumnInfo(name = "title") val title : String,
    @ColumnInfo(name = "time") val time : String,
    @ColumnInfo(name = "active") val active : Boolean
)