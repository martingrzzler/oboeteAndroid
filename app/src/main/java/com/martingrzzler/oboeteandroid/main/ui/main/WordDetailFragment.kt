package com.martingrzzler.oboeteandroid.main.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import com.martingrzzler.oboeteandroid.databinding.FragmentWordDetailBinding

class WordDetailFragment : Fragment() {

    val args: WordDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentWordDetailBinding.inflate(inflater)

        binding.word.text = args.word.word
        binding.reading.text = args.word.reading


        return binding.root
    }

}
