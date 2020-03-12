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
import com.google.android.material.snackbar.Snackbar
import com.martingrzzler.oboeteandroid.R
import com.martingrzzler.oboeteandroid.databinding.FragmentLauncherBinding
import com.martingrzzler.oboeteandroid.main.adapters.SearchWordRecyclerViewAdapter
import com.martingrzzler.oboeteandroid.main.model.Word
import com.martingrzzler.oboeteandroid.main.viewmodels.LauncherFragmentViewModel
import com.martingrzzler.oboeteandroid.main.viewmodels.ResponseState
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

        // Obeserve apiStatus
        viewModel.apiStatus.observe(viewLifecycleOwner, Observer {state ->
            when(state) {
                ResponseState.ERROR -> {
                    Snackbar.make(binding.coordinator, "Check your Connection. There a Network Error!", Snackbar.LENGTH_LONG).show()
                }
                ResponseState.LOADING -> {
                    binding.progressbar.visibility = View.VISIBLE
                }

                ResponseState.DONE -> {
                    binding.progressbar.visibility = View.INVISIBLE
                }

                ResponseState.NOTFOUND -> {
                    Snackbar.make(binding.coordinator, "Sorry! Couldn't find that", Snackbar.LENGTH_LONG).show()
                }

            }
        })

        initRecyclerView(binding.resultRecyclerView)
        initSearchView(binding.searchView)


        return binding.root
    }

    private fun initSearchView(searchView: SearchView) {


        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                oboete_logo.visibility = View.INVISIBLE
                viewModel.makeQueryCallUserInput(query!!).observe(viewLifecycleOwner, Observer {words ->
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
