package com.example.bookapp.fragments.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.bookapp.databinding.CustomRowBinding
import com.example.bookapp.model.Book

class ListAdapter : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    var bookList = emptyList<Book>()

    class MyViewHolder(val binding: CustomRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            CustomRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = bookList[position]

        holder.binding.apply {
            tvId.text = currentItem.id.toString()
            tvTitle.text = currentItem.title
            tvAuthor.text = currentItem.author
        }
        holder.binding.rowLayout.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    fun setData(book: List<Book>) {
        this.bookList = book
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return bookList.size
    }
}