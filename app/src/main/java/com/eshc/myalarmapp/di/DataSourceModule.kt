package com.eshc.myalarmapp.di

import com.eshc.myalarmapp.data.source.AlarmDataSource
import com.eshc.myalarmapp.data.source.local.AlarmLocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun bindAlarmLocalDataSource(
        alarmLocalDataSourceImpl: AlarmLocalDataSourceImpl
    ) : AlarmDataSource
}