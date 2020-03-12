package com.martingrzzler.oboeteandroid.main.ui.main

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.martingrzzler.oboeteandroid.R
import com.martingrzzler.oboeteandroid.databinding.FragmentLauncherBinding
import com.martingrzzler.oboeteandroid.main.adapters.SearchWordRecyclerViewAdapter
import com.martingrzzler.oboeteandroid.main.model.Word
import com.martingrzzler.oboeteandroid.main.viewmodels.LauncherFragmentViewModel
import kotlinx.android.synthetic.main.fragment_launcher.*


class LauncherFragment : Fragment() {

    private lateinit var viewModel: LauncherFragmentViewModel
    lateinit var searchWordAdapter: SearchWordRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentLauncherBinding.inflate(inflater)
        setHasOptionsMenu(true)

        viewModel = ViewModelProvider(this).get(LauncherFragmentViewModel::class.java)
        binding.launcherFragmentViewModel = viewModel
        binding.lifecycleOwner = this

        initRecyclerView(binding.resultRecyclerView)
        initSearchView(binding.searchView)


        return binding.root
    }

    private fun initSearchView(searchView: SearchView) {


        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.makeQueryCallUserInput(query!!).observe(viewLifecycleOwner, Observer {words ->
                    oboete_logo.visibility = View.INVISIBLE
                    resultRecyclerView.visibility = View.VISIBLE
                    searchWordAdapter.submitList(words)

                })
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText =="") {
                    oboete_logo.visibility = View.VISIBLE
                    resultRecyclerView.visibility = View.INVISIBLE
                }
                return true
            }

        })
    }

    private fun initRecyclerView(recyclerView: RecyclerView) {
        searchWordAdapter = SearchWordRecyclerViewAdapter()
        recyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = searchWordAdapter

        }
    }

}
