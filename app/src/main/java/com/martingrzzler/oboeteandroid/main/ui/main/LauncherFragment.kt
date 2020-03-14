package com.martingrzzler.oboeteandroid.main.ui.main

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ProgressBar
import androidx.appcompat.widget.SearchView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.martingrzzler.oboeteandroid.databinding.FragmentLauncherBinding
import com.martingrzzler.oboeteandroid.main.adapters.SearchWordRecyclerViewAdapter
import com.martingrzzler.oboeteandroid.main.di.scopes.MainScope
import com.martingrzzler.oboeteandroid.main.viewmodels.LauncherFragmentViewModel
import com.martingrzzler.oboeteandroid.main.viewmodels.ResponseState
import kotlinx.android.synthetic.main.fragment_launcher.*
import javax.inject.Inject

@MainScope
class LauncherFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var viewModel: LauncherFragmentViewModel
    lateinit var searchWordAdapter: SearchWordRecyclerViewAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as MainActivity).mainComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentLauncherBinding.inflate(inflater)

        // inject viewmodel
        viewModel = ViewModelProvider(this, viewModelFactory).get(LauncherFragmentViewModel::class.java)
        binding.launcherFragmentViewModel = viewModel
        binding.lifecycleOwner = this



        initRecyclerView(binding.resultRecyclerView)
        initSearchView(binding.searchView)
        handleApiState(binding.progressbar, binding.coordinator)


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
                if(resultRecyclerView.visibility == View.VISIBLE) {
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

    private fun handleApiState(progressBar: ProgressBar, coordinator: CoordinatorLayout) {
        // Observe apiStatus
        viewModel.apiStatus.observe(viewLifecycleOwner, Observer {state ->
            when(state) {
                ResponseState.ERROR -> {
                    Snackbar.make(coordinator, "Check your Connection. There a Network Error!", Snackbar.LENGTH_LONG).show()
                }
                ResponseState.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                }

                ResponseState.DONE -> {
                    progressBar.visibility = View.INVISIBLE
                }
                else -> {
                    Log.i("LauncherFragment", "apiStatus was NULL")
                }

            }
        })

    }

}
