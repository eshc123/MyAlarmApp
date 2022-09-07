package com.eshc.myalarmapp.di

import com.eshc.myalarmapp.data.repository.AlarmRepositoryImpl
import com.eshc.myalarmapp.domain.repository.AlarmRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindAlarmRepository(
        alarmRepositoryImpl: AlarmRepositoryImpl
    ) : AlarmRepository
}