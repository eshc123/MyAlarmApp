package com.eshc.myalarmapp.di

import android.content.Context
import androidx.room.Room
import com.eshc.myalarmapp.data.source.local.db.AppDatabase
import com.eshc.myalarmapp.data.source.local.db.dao.AlarmDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context : Context
    ) : AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideAlarmDao(
        database : AppDatabase
    ) : AlarmDao {
        return database.alarmDao()
    }
}