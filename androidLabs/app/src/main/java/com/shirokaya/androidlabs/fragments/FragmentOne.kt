package com.shirokaya.androidlabs.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shirokaya.androidlabs.PhonesData
import com.shirokaya.androidlabs.R
import com.shirokaya.androidlabs.adapters.PhonesAdapter

class FragmentOne : Fragment() {

    private val myAdapter = PhonesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_one, container, false)
        val recyclerView = root.findViewById<RecyclerView>(R.id.recyclerView)
        loadData()
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = myAdapter

        return root
    }

    private fun loadData()
    {
        myAdapter.setupPhones(PhonesData.phonesArr)
    }
}