package com.eshc.myalarmapp.ui.alarmdetail

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.eshc.myalarmapp.R
import com.eshc.myalarmapp.databinding.FragmentAlarmDetailBinding
import com.eshc.myalarmapp.util.ALARM_ID
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlarmDetailFragment : Fragment() {

    private var _binding: FragmentAlarmDetailBinding? = null
    private val binding get() = _binding

    private val viewModel : AlarmDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAlarmDetailBinding.inflate(
            inflater,
            container,
            false
        ).apply {
            this.viewmodel = viewModel
            this.lifecycleOwner = this@AlarmDetailFragment.viewLifecycleOwner
        }

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getInt("alarmId").apply {
            viewModel.initAlarm(this)

            binding?.btSubmit?.setOnClickListener {
                if(this == null)
                    viewModel.insertAlarm()
                else viewModel.updateAlarm(this)

                parentFragmentManager.popBackStack()
            }
        }

    }

}