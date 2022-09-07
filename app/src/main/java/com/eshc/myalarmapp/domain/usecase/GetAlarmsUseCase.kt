package com.eshc.myalarmapp.domain.usecase

import com.eshc.myalarmapp.domain.model.AlarmModel
import com.eshc.myalarmapp.domain.repository.AlarmRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAlarmsUseCase @Inject constructor(
    private val alarmRepository: AlarmRepository
) {
    operator fun invoke() : Flow<List<AlarmModel>> {
        return alarmRepository.getAlarms()
    }
}