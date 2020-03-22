package com.martingrzzler.oboeteandroid.main.ui.main

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.animation.Animation
import android.widget.ImageView
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
import javax.inject.Inject

@MainScope
class LauncherFragment : Fragment(), Interaction {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var viewModel: LauncherFragmentViewModel
    lateinit var searchWordAdapter: SearchWordRecyclerViewAdapter
    private lateinit var animator: AnimatorSet

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
        initSearchView(binding.searchView, binding.resultRecyclerView, binding.oboeteLogo)
        handleApiState( binding.coordinator, binding.customProgressbar, binding.resultRecyclerView)
        initAnimation(binding.customProgressbar)



        return binding.root
    }

    private fun initSearchView(searchView: SearchView, recyclerView: RecyclerView, logo: ImageView) {
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                logo.visibility = View.INVISIBLE
                viewModel.makeQueryCallUserInput(query!!)
                viewModel.words.observe(viewLifecycleOwner, Observer {words ->
                    searchWordAdapter.submitList(words)
                })
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText =="") {
                    logo.visibility = View.VISIBLE
                }
                if(recyclerView.visibility == View.VISIBLE) {
                    recyclerView.visibility = View.INVISIBLE
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


    private fun handleApiState(coordinator: CoordinatorLayout, customProgressbar: TextView, recyclerView: RecyclerView) {
        // Observe apiStatus
        viewModel.apiStatus.observe(viewLifecycleOwner, Observer {state ->
            when(state) {
                ResponseState.ERROR -> {
                    Snackbar.make(coordinator, "Check your Connection. There was a Network Error!", Snackbar.LENGTH_LONG).show()
                }
                ResponseState.LOADING -> {
                    customProgressbar.visibility = View.VISIBLE
                    recyclerView.visibility = View.INVISIBLE
                    manageAnimation(animator, customProgressbar)
                }

                ResponseState.DONE -> {
                    recyclerView.visibility = View.VISIBLE
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
    private fun manageAnimation(animator: AnimatorSet, progressbar: TextView) {
        if(progressbar.visibility == View.VISIBLE) {
            animator.start()
        } else {
            animator.cancel()
        }
    }

    private fun initAnimation(customProgressbar: TextView) {

        val rotater = ObjectAnimator.ofFloat(customProgressbar, View.ROTATION, 350f)
        rotater.repeatCount = Animation.INFINITE
        val scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, 2f)
        val scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 2f)
        val scaler = ObjectAnimator.ofPropertyValuesHolder(customProgressbar, scaleX, scaleY)
        scaler.repeatCount = Animation.INFINITE
        scaler.repeatMode = ObjectAnimator.REVERSE
        animator = AnimatorSet()
        animator.playTogether(rotater, scaler)
        animator.duration = 550
    }

}
