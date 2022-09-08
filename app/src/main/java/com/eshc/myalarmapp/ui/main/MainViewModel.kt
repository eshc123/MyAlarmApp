package com.eshc.myalarmapp.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eshc.myalarmapp.domain.usecase.GetAlarmsUseCase
import com.eshc.myalarmapp.domain.usecase.UpdateAlarmIsActiveUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    getAlarmsUseCase: GetAlarmsUseCase,
    private val updateAlarmIsActiveUseCase: UpdateAlarmIsActiveUseCase
) : ViewModel() {

    val alarms = getAlarmsUseCase()

    fun updateAlarm(alarmId : Int?){
        viewModelScope.launch {
            alarmId?.let {
                updateAlarmIsActiveUseCase(it)
            }
        }
    }
}