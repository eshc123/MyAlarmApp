package com.eshc.myalarmapp.ui.adapter

import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BindingAdapter
import com.eshc.myalarmapp.R

@BindingAdapter("timeText")
fun AppCompatTextView.setTimeText(resource : String){
    val hour = resource.substring(0,2).toInt()
    val minute = resource.substring(2,4).toInt()

    text = if(hour < 12) context.resources.getString(R.string.alarm_time_am,hour,minute)
            else if(hour == 12) context.resources.getString(R.string.alarm_time_pm,hour,minute)
            else context.resources.getString(R.string.alarm_time_pm,hour - 12,minute)
}