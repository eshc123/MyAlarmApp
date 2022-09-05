package com.eshc.myalarmapp.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.eshc.myalarmapp.R
import com.eshc.myalarmapp.databinding.ActivityMainBinding
import com.eshc.myalarmapp.ui.adapter.AlarmAdapter

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding

    private val alarmAdapter : AlarmAdapter by lazy {
        AlarmAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(
            this, R.layout.activity_main
        )

        initRecyclerView()
    }

    private fun initRecyclerView(){
        binding?.rvAlarm?.let {
            it.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
            it.adapter = alarmAdapter
        }
    }
}