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


        return binding.root
    }



    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)
//        val manager = activity!!.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchItem = menu.findItem(R.id.search_bar)

        searchItem.setOnActionExpandListener(object: MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
                return true
            }

            override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                oboete_logo.visibility = View.VISIBLE
                resultRecyclerView.visibility = View.INVISIBLE
                return true
            }
        })
        val searchView = searchItem?.actionView as SearchView

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
                Log.i("LauncherFragment", "newText: $newText")
                return true
            }

        })
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun initRecyclerView(recyclerView: RecyclerView) {
        searchWordAdapter = SearchWordRecyclerViewAdapter()
        recyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = searchWordAdapter

        }
    }

}
