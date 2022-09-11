package com.eshc.myalarmapp.ui.alarms

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eshc.myalarmapp.domain.usecase.GetAlarmsUseCase
import com.eshc.myalarmapp.domain.usecase.UpdateAlarmIsActiveUseCase
import com.eshc.myalarmapp.ui.model.toAlarmUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlarmsViewModel @Inject constructor(
    getAlarmsUseCase: GetAlarmsUseCase,
    private val updateAlarmIsActiveUseCase: UpdateAlarmIsActiveUseCase
) : ViewModel() {

    private val _alarmsUiState = MutableStateFlow(AlarmsUiState(emptyList()))
    val alarmsUiState : StateFlow<AlarmsUiState>
        get() = _alarmsUiState
            .combine(alarms) { uiState, alarms ->
                uiState.copy(
                    alarms = alarms.map {
                        it.toAlarmUIModel()
                    }
                )
            }
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5000),
                AlarmsUiState(emptyList())
            )

    val alarms = getAlarmsUseCase()

    fun updateAlarm(alarmId : Int?){
        viewModelScope.launch {
            alarmId?.let {
                updateAlarmIsActiveUseCase(it)
            }
        }
    }

    fun addAlarm() {
        _alarmsUiState.value = alarmsUiState.value.copy(
            addAlarmShown = true
        )
    }

    fun editAlarm(alarmId: Int) {
        _alarmsUiState.value = alarmsUiState.value.copy(
            editAlarmId = alarmId
        )
    }

    fun alarmShown() {
        _alarmsUiState.update {
            it.copy(
                addAlarmShown = null,
                editAlarmId = null
            )
        }
    }


}