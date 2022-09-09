package com.eshc.myalarmapp.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.commit
import com.eshc.myalarmapp.R
import com.eshc.myalarmapp.databinding.ActivityMainBinding
import com.eshc.myalarmapp.ui.alarms.AlarmsFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding

    private val viewModel : MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(
            this, R.layout.activity_main
        )

        binding?.lifecycleOwner = this

        binding?.flMain?.let {
            initContainer(it)
        }
    }

    private fun initContainer(frameLayout: FrameLayout){
        supportFragmentManager.commit {
            replace(frameLayout.id,AlarmsFragment())
        }
    }
}