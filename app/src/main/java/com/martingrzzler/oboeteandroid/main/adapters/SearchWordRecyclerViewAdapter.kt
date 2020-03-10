package com.martingrzzler.oboeteandroid.main.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.martingrzzler.oboeteandroid.R
import com.martingrzzler.oboeteandroid.main.model.Word
import kotlinx.android.synthetic.main.layout_word_list_item.view.*

class SearchWordRecyclerViewAdapter(private val interaction: Interaction? = null) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Word>() {

        override fun areItemsTheSame(oldItem: Word, newItem: Word): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Word, newItem: Word): Boolean {
            return oldItem == newItem
        }

    }
    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return WordViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.layout_word_list_item,
                parent,
                false
            ),
            interaction
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is WordViewHolder -> {
                holder.bind(differ.currentList[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list: List<Word>) {
        differ.submitList(list)
    }

    class WordViewHolder
    constructor(
        itemView: View,
        private val interaction: Interaction?
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: Word) = with(itemView) {
            itemView.setOnClickListener {
                interaction?.onItemSelected(adapterPosition, item)
            }
            itemView.word_item_word.text = item.word
            itemView.word_item_reading.text = item.reading
            itemView.word_item_translations.text = formatTranslationList(item.translation)
        }

        private fun formatTranslationList(list: List<String>): String {
           val listString = list.toString().replace("[","").replace("]","")
            return if(listString.length > 50) {
                listString.substring(0, 49) + "..."
            } else {
                listString
            }


        }
    }



    interface Interaction {
        fun onItemSelected(position: Int, item: Word)
    }


}
