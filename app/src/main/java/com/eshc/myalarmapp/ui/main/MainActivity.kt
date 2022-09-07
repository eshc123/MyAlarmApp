package com.eshc.myalarmapp.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.eshc.myalarmapp.R
import com.eshc.myalarmapp.databinding.ActivityMainBinding
import com.eshc.myalarmapp.ui.adapter.AlarmAdapter
import com.eshc.myalarmapp.ui.detail.DetailActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding

    private val viewModel : MainViewModel by viewModels()

    private val alarmAdapter : AlarmAdapter by lazy {
        AlarmAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(
            this, R.layout.activity_main
        )
        viewModel
        initRecyclerView()
        initFab()
    }

    private fun initRecyclerView(){
        binding?.rvAlarm?.let {
            it.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
            it.adapter = alarmAdapter
        }
    }

    private fun initFab(){
        binding?.fbRegister?.let {
            it.setOnClickListener {
                startActivity(Intent(this,DetailActivity::class.java))
            }
        }
    }
}