package com.eshc.myalarmapp.ui.alarms

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.eshc.myalarmapp.R
import com.eshc.myalarmapp.databinding.FragmentAlarmsBinding
import com.eshc.myalarmapp.ui.adapter.AlarmAdapter
import com.eshc.myalarmapp.ui.alarmdetail.AlarmDetailFragment
import com.eshc.myalarmapp.ui.model.toAlarmUIModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AlarmsFragment : Fragment() {
    private var _binding: FragmentAlarmsBinding? = null
    private val binding get() = _binding

    private val viewModel : AlarmsViewModel by viewModels()

    private val alarmAdapter : AlarmAdapter by lazy {
        AlarmAdapter(
            onClick = {
                viewModel.editAlarm(it.id)
            },
            onSwitch = {
                viewModel.updateAlarm(it.id)
            }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAlarmsBinding.inflate(
            inflater,
            container,
            false
        )

        binding?.lifecycleOwner = this.viewLifecycleOwner

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initFab()
        initObserver()
    }


    private fun initRecyclerView(){
        binding?.rvAlarm?.let {
            it.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)
            it.adapter = alarmAdapter
        }
    }

    private fun initFab(){
        binding?.fbRegister?.let {
            it.setOnClickListener {
                viewModel.addAlarm()
            }
        }
    }

    private fun initObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.alarmsUiState.collectLatest {
                    alarmAdapter.submitList(it.alarms)


                    if(it.addAlarmShown == true){
                        moveToAlarmDetail()
                        viewModel.alarmShown()
                    }

                    if(it.editAlarmId != null ){
                        moveToAlarmDetail(it.editAlarmId)
                        viewModel.alarmShown()
                    }
                }
            }
        }
    }

    private fun moveToAlarmDetail(alarmId : Int? = null){
        parentFragmentManager.commit {
            AlarmDetailFragment().apply {
                if(alarmId != null)
                arguments = Bundle().apply {
                    putInt("alarmId",alarmId)
                }
                addToBackStack(this.tag)
                replace(R.id.fl_main,this, this.tag)
            }
        }
    }
}