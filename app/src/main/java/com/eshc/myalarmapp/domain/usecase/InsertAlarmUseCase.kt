package com.eshc.myalarmapp.domain.usecase

import com.eshc.myalarmapp.domain.model.AlarmModel
import com.eshc.myalarmapp.domain.repository.AlarmRepository
import javax.inject.Inject

class InsertAlarmUseCase @Inject constructor(
    private val alarmRepository: AlarmRepository
) {
    suspend operator fun invoke(alarm : AlarmModel)  {
        alarmRepository.insertAlarm(alarm)
    }
}