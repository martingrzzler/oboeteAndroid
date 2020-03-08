package com.martingrzzler.oboeteandroid.main.ui.main

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.martingrzzler.oboeteandroid.R
import com.martingrzzler.oboeteandroid.databinding.FragmentLauncherBinding
import com.martingrzzler.oboeteandroid.main.viewmodels.LauncherFragmentViewModel
import kotlinx.android.synthetic.main.fragment_launcher.*



class LauncherFragment : Fragment() {

    lateinit var viewModel: LauncherFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentLauncherBinding.inflate(inflater)
        setHasOptionsMenu(true)

        viewModel = ViewModelProvider(this).get(LauncherFragmentViewModel::class.java)
        binding.launcherFragmentViewModel = viewModel
        binding.lifecycleOwner = this



        return binding.root
    }



    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)
//        val manager = activity!!.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchItem = menu.findItem(R.id.search_bar)
        val searchView = searchItem?.actionView as SearchView


        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.makeQueryCallUserInput(query!!).observe(viewLifecycleOwner, Observer {
                    response_text.text = it.toString()
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

}
