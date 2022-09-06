package com.eshc.myalarmapp.data.source.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.eshc.myalarmapp.data.source.local.db.dao.AlarmDao
import com.eshc.myalarmapp.data.source.local.db.entity.AlarmEntity

@Database(entities = [AlarmEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase()  {
    abstract fun alarmDao() : AlarmDao
}