/*
 * Copyright 2019, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.trackmysleepquality.sleeptracker

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.trackmysleepquality.database.SleepNight
import com.example.android.trackmysleepquality.databinding.ListItemSleepNightBinding

//la clase ahora recibe una variable clickListener: SleepNightListener
class SleepNightAdapter(val clickListener: SleepNightListener):
    ListAdapter<SleepNight, SleepNightAdapter.ViewHolder>(SleepNightDiffCallback()) {
//class SleepNightAdapter : ListAdapter<SleepNight, SleepNightAdapter.ViewHolder>(SleepNightDiffCallback()) {

//    var data =  listOf<SleepNight>()
//        set(value) {
//            field = value
//            notifyDataSetChanged()
//        }
//
//    override fun getItemCount() = data.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //ItemViewHolder
//        val item = data[position]
//        holder.textView.text = item.sleepQuality.toString()
//        if (item.sleepQuality <= 1) {
//            holder.textView.setTextColor(Color.RED) // red
//        } else {
//            // reset
//            holder.textView.setTextColor(Color.BLACK) // black
//        }
//        val item = data[position]
//        //DifUtil
//        val item = getItem(position)
//        holder.bind(item)
        holder.bind(getItem(position)!!, clickListener)
    }
    /* funcion para objeto TextItemViewHolder
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextItemViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater
                .inflate(R.layout.text_item_view, parent, false) as TextView
            return TextItemViewHolder(view)
        }
        */

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //        parent: ViewGroup, viewType: Int): ViewHolder {
//        val layoutInflater =
//            LayoutInflater.from(parent.context)
//        val view = layoutInflater
//            .inflate(R.layout.list_item_sleep_night,
//                parent, false)
//        return ViewHolder(view)
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: ListItemSleepNightBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(item: SleepNight, clickListener: SleepNightListener) {
            /* bind sin el GridLayout
            val res = itemView.context.resources
            binding.sleepLength.text =
                convertDurationToFormatted(item.startTimeMilli, item.endTimeMilli, res)
            binding.qualityString.text = convertNumericQualityToString(item.sleepQuality, res)
            binding.qualityImage.setImageResource(
                when (item.sleepQuality) {
                    0 -> R.drawable.ic_sleep_0
                    1 -> R.drawable.ic_sleep_1
                    2 -> R.drawable.ic_sleep_2
                    3 -> R.drawable.ic_sleep_3
                    4 -> R.drawable.ic_sleep_4
                    5 -> R.drawable.ic_sleep_5
                    else -> R.drawable.ic_sleep_active
                }
            )
             */
            binding.sleep = item
            binding.clickListener = clickListener
            binding.executePendingBindings() //se ejecutan los bindings restantes
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
//                val view = layoutInflater
//                        .inflate(R.layout.list_item_sleep_night, parent, false)
                val binding =
                    ListItemSleepNightBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class SleepNightDiffCallback : DiffUtil.ItemCallback<SleepNight>() {
    override fun areItemsTheSame(oldItem: SleepNight, newItem: SleepNight): Boolean {
        //To change body of created functions use File | Settings | File Templates.
        return oldItem.nightId == newItem.nightId
    }

    override fun areContentsTheSame(oldItem: SleepNight, newItem: SleepNight): Boolean {
        //To change body of created functions use File | Settings | File Templates.
        return oldItem == newItem
    }
}

class SleepNightListener(val clickListener: (sleepId: Long) -> Unit) {
    //cuando se da click a un elemento de la lista, la vista llama a esta funcion onClick
    fun onClick(night: SleepNight) = clickListener(night.nightId)

}
