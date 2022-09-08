package com.eshc.myalarmapp.domain.usecase

import com.eshc.myalarmapp.domain.repository.AlarmRepository
import javax.inject.Inject

class UpdateAlarmIsActiveUseCase @Inject constructor(
    private val alarmRepository: AlarmRepository
) {
    suspend operator fun invoke(alarmId : Int)  {
        alarmRepository.updateAlarmIsActive(alarmId)
    }
}