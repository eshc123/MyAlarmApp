package com.eshc.myalarmapp.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.eshc.myalarmapp.R
import com.eshc.myalarmapp.databinding.ActivityMainBinding
import com.eshc.myalarmapp.ui.adapter.AlarmAdapter
import com.eshc.myalarmapp.ui.detail.DetailActivity
import com.eshc.myalarmapp.ui.model.toAlarmUIModel
import com.eshc.myalarmapp.util.ALARM_ID
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding

    private val viewModel : MainViewModel by viewModels()

    private val alarmAdapter : AlarmAdapter by lazy {
        AlarmAdapter(
            onClick = {
                moveToDetail(it.id)
            },
            onSwitch = {
                viewModel.updateAlarm(it.id)
            }
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(
            this, R.layout.activity_main
        )

        initRecyclerView()
        initFab()
        initObserver()
    }

    private fun initRecyclerView(){
        binding?.rvAlarm?.let {
            it.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
            it.adapter = alarmAdapter
        }
    }

    private fun initFab(){
        binding?.fbRegister?.let {
            it.setOnClickListener {
                moveToDetail()
            }
        }
    }

    private fun initObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.alarms.collect { list ->
                    alarmAdapter.submitList(
                        list.map {
                            it.toAlarmUIModel()
                        }
                    )
                }
            }
        }
    }

    private fun moveToDetail(alarmId : Int? = null) {
        val detailIntent = Intent(this,DetailActivity::class.java)
        if(alarmId != null){
            detailIntent.putExtra(ALARM_ID,alarmId)
        }
        startActivity(detailIntent)
    }
}