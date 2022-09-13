package com.eshc.myalarmapp.ui.alarmdetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eshc.myalarmapp.domain.model.AlarmModel
import com.eshc.myalarmapp.domain.usecase.GetAlarmByIdUseCase
import com.eshc.myalarmapp.domain.usecase.InsertAlarmUseCase
import com.eshc.myalarmapp.domain.usecase.UpdateAlarmUseCase
import com.eshc.myalarmapp.ui.model.AlarmUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class AlarmDetailViewModel @Inject constructor(
    private val getAlarmByIdUseCase: GetAlarmByIdUseCase,
    private val insertAlarmUseCase: InsertAlarmUseCase,
    private val updateAlarmUseCase: UpdateAlarmUseCase
) : ViewModel() {

    val title = MutableLiveData<String>("")

    val hour = MutableLiveData<Int>()

    val minute = MutableLiveData<Int>()

    fun initAlarm(alarmId : Int?) {
        if(alarmId == null) {
            val calendar = Calendar.getInstance()
            hour.value = calendar.get(Calendar.HOUR_OF_DAY)
            minute.value = calendar.get(Calendar.MINUTE)
            return
        }

        viewModelScope.launch {
            getAlarmByIdUseCase(alarmId).also {
                title.value = it.title
                hour.value = it.time.substring(0, 2).toInt()
                minute.value = it.time.substring(2, 4).toInt()
            }
        }
    }

    fun insertAlarm(){
        viewModelScope.launch {
            insertAlarmUseCase(
                AlarmModel(
                    title = title.value ?: "",
                    time = "%02d".format(hour.value) + "%02d".format(minute.value),
                    isActive = true
                )
            )
        }
    }

    fun updateAlarm(alarmId: Int?){
        viewModelScope.launch {
            updateAlarmUseCase(
                AlarmModel(
                    id = alarmId,
                    title = title.value ?: "",
                    time = "%02d".format(hour.value) + "%02d".format(minute.value),
                    isActive = true
                )
            )
        }
    }
}