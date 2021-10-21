package com.example.bookapp.fragments.add

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.room.Entity
import com.example.bookapp.R
import com.example.bookapp.databinding.FragmentAddBinding
import com.example.bookapp.model.Book
import com.example.bookapp.viewmodel.BookViewModel

@Entity
class AddFragment : Fragment() {

    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!

    private lateinit var mBookViewModel: BookViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentAddBinding.inflate(inflater, container, false)

        mBookViewModel = ViewModelProvider(this).get(BookViewModel::class.java)

        binding.btnAdd.setOnClickListener {
            insertDataToDatabase()
        }

        return binding.root
    }

    private fun inputCheck(title: String, author: String): Boolean {
        return !(TextUtils.isEmpty(title) && TextUtils.isEmpty(author))
    }

    private fun insertDataToDatabase() {
        val title = binding.addTitle.text.toString()
        val author = binding.addAuthor.text.toString()

        if (inputCheck(title, author)) {
            val book = Book(0, title, author)

            mBookViewModel.addBook(book)
            Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_LONG)
                .show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}