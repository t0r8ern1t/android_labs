package com.shirokaya.androidlabs.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shirokaya.androidlabs.PhoneModel
import com.shirokaya.androidlabs.R

class PhonesAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private  var mPhonesList: ArrayList<PhoneModel> = ArrayList()

    fun setupPhones(phonesList: Array<PhoneModel>) {
        mPhonesList.clear()
        mPhonesList.addAll(phonesList)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val  layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.recyclerview_item, parent, false)
        return PhonesViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return mPhonesList.count()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is PhonesViewHolder) {
            holder.bind(mPhones = mPhonesList[position])
        }
    }
}