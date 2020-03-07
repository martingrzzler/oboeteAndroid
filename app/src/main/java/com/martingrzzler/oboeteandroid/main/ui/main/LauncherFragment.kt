package com.martingrzzler.oboeteandroid.main.ui.main

import android.app.SearchManager

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import com.martingrzzler.oboeteandroid.R
import com.martingrzzler.oboeteandroid.databinding.FragmentLauncherBinding
import com.martingrzzler.oboeteandroid.main.model.DataResponse
import com.martingrzzler.oboeteandroid.main.network.Retrofit
import retrofit2.Call
import retrofit2.Response


class LauncherFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentLauncherBinding.inflate(inflater)
        setHasOptionsMenu(true)

        return binding.root
    }

    private fun testRetrofitRequest(query: String) {

        val responseCall = Retrofit.apiService.getWord(query)
        responseCall.enqueue(object : retrofit2.Callback<DataResponse> {
            override fun onFailure(call: Call<DataResponse>, t: Throwable) {
                Toast.makeText(activity, t.toString(), Toast.LENGTH_LONG).show()
                Log.i("LauncherFragment", t.toString())
            }

            override fun onResponse(call: Call<DataResponse>, response: Response<DataResponse>) {
                Toast.makeText(activity, response.body().toString(), Toast.LENGTH_LONG).show()
                Log.i("LauncherFragment", response.body().toString())
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)
        val manager = activity!!.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchItem = menu.findItem(R.id.search_bar)
        val searchView = searchItem?.actionView as SearchView


        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                testRetrofitRequest(query!!)
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
