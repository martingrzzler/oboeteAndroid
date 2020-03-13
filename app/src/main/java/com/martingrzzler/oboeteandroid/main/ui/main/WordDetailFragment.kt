package com.martingrzzler.oboeteandroid.main.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.martingrzzler.oboeteandroid.databinding.FragmentWordDetailBinding

class WordDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentWordDetailBinding.inflate(inflater)
        return binding.root
    }

}
