package com.eshc.myalarmapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.eshc.myalarmapp.databinding.ItemAlarmBinding
import com.eshc.myalarmapp.ui.model.AlarmUIModel

class AlarmAdapter : ListAdapter<AlarmUIModel, AlarmAdapter.AlarmViewHolder>(AlarmDiffCallback()) {

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

    class AlarmViewHolder(val binding : ItemAlarmBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(alarm : AlarmUIModel){
            binding.apply {
                this.alarm = alarm
            }
        }
    }

    private class AlarmDiffCallback : DiffUtil.ItemCallback<AlarmUIModel>() {
        override fun areItemsTheSame(oldItem: AlarmUIModel, newItem: AlarmUIModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: AlarmUIModel, newItem: AlarmUIModel): Boolean {
            return oldItem == newItem
        }

    }
}