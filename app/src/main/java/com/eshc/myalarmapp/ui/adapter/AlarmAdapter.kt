package com.eshc.myalarmapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.eshc.myalarmapp.databinding.ItemAlarmBinding
import com.eshc.myalarmapp.ui.model.AlarmUIModel

class AlarmAdapter(
    private val onClick: (AlarmUIModel) -> Unit,
    private val onSwitch: (AlarmUIModel) -> Unit
) : ListAdapter<AlarmUIModel, AlarmAdapter.AlarmViewHolder>(AlarmDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlarmViewHolder {
        return AlarmViewHolder(
            ItemAlarmBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: AlarmViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onBindViewHolder(
        holder: AlarmViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if(payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            if(payloads[0] == true)
                holder.bindActiveState(getItem(position).isActive)
        }
    }

    inner class AlarmViewHolder(
        private val binding : ItemAlarmBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(alarm : AlarmUIModel){
            binding.apply {
                this.alarm = alarm
                this.swActive.setOnClickListener {
                    onSwitch(alarm)
                }
                this.root.setOnClickListener {
                    onClick(alarm)
                }
            }
        }
        fun bindActiveState(isActive : Boolean) {
            binding.swActive.isChecked = isActive
        }
    }

    private class AlarmDiffCallback : DiffUtil.ItemCallback<AlarmUIModel>() {
        override fun areItemsTheSame(oldItem: AlarmUIModel, newItem: AlarmUIModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: AlarmUIModel, newItem: AlarmUIModel): Boolean {
            return oldItem == newItem
        }

        override fun getChangePayload(oldItem: AlarmUIModel, newItem: AlarmUIModel): Any? {
            return if(oldItem.isActive != newItem.isActive) true else null
        }
    }
}