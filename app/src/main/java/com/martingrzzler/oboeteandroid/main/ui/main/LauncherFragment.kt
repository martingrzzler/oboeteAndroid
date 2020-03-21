package com.martingrzzler.oboeteandroid.main.ui.main

import android.animation.ObjectAnimator
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.animation.Animation
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.martingrzzler.oboeteandroid.databinding.FragmentLauncherBinding
import com.martingrzzler.oboeteandroid.main.adapters.Interaction
import com.martingrzzler.oboeteandroid.main.adapters.SearchWordRecyclerViewAdapter
import com.martingrzzler.oboeteandroid.main.di.scopes.MainScope
import com.martingrzzler.oboeteandroid.main.model.Word
import com.martingrzzler.oboeteandroid.main.viewmodels.LauncherFragmentViewModel
import com.martingrzzler.oboeteandroid.main.viewmodels.ResponseState
import kotlinx.android.synthetic.main.fragment_launcher.*
import kotlinx.android.synthetic.main.fragment_word_detail.*
import javax.inject.Inject

@MainScope
class LauncherFragment : Fragment(), Interaction {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var viewModel: LauncherFragmentViewModel
    lateinit var searchWordAdapter: SearchWordRecyclerViewAdapter
    private lateinit var animator: ObjectAnimator

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

        // set up the animation for the custom progressbar
        animator = ObjectAnimator.ofFloat(binding.customProgressbar, View.ROTATION, -360F, 0F)
        animator.duration = 800
        animator.repeatCount = Animation.INFINITE




        initRecyclerView(binding.resultRecyclerView)
        initSearchView(binding.searchView)
        handleApiState( binding.coordinator, binding.customProgressbar)



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
        searchWordAdapter = SearchWordRecyclerViewAdapter(this)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = searchWordAdapter

        }
    }

    override fun onItemSelected(position: Int, item: Word) {
        val action = LauncherFragmentDirections.actionLauncherFragmentToWordDetailFragment(item)
        findNavController().navigate(action)
    }


    private fun handleApiState(coordinator: CoordinatorLayout, customProgressbar: TextView) {
        // Observe apiStatus
        viewModel.apiStatus.observe(viewLifecycleOwner, Observer {state ->
            when(state) {
                ResponseState.ERROR -> {
                    Snackbar.make(coordinator, "Check your Connection. There a Network Error!", Snackbar.LENGTH_LONG).show()
                }
                ResponseState.LOADING -> {
                    customProgressbar.visibility = View.VISIBLE
                    manageAnimation(animator, customProgressbar)
                }

                ResponseState.DONE -> {
                    customProgressbar.visibility = View.INVISIBLE
                    manageAnimation(animator, customProgressbar)
                }
                else -> {
                    Log.i("LauncherFragment", "apiStatus was NULL")
                }

            }
        })

    }
    // Would like to test whether the animation actually stops, how to unit test functions in fragments
    private fun manageAnimation(animator: ObjectAnimator, progressbar: TextView) {
        if(progressbar.visibility == View.VISIBLE) {
            animator.start()
        } else {
            animator.pause()
        }
    }

}
