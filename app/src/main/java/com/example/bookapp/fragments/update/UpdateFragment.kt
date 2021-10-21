package com.example.bookapp.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.bookapp.R
import com.example.bookapp.databinding.FragmentUpdateBinding
import com.example.bookapp.model.Book
import com.example.bookapp.viewmodel.BookViewModel


class UpdateFragment : Fragment() {

    private var _binding: FragmentUpdateBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<UpdateFragmentArgs>()

    private lateinit var mBookViewModel: BookViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentUpdateBinding.inflate(inflater, container, false)

        mBookViewModel = ViewModelProvider(this).get(BookViewModel::class.java)

        binding.apply {
            updateTitle.setText(args.currentBook.title)
            updateAuthor.setText(args.currentBook.author)
        }

        binding.btnUpdate.setOnClickListener {
            updateItem()
        }

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete) {
            deleteBook()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun deleteBook() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            mBookViewModel.deleteBook(args.currentBook)
            Toast.makeText(
                requireContext(),
                "Successfully removed: ${args.currentBook.title}",
                Toast.LENGTH_SHORT
            ).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Delete ${args.currentBook.title}?")
        builder.setMessage("Are you sure you want to delete${args.currentBook.title}?")
        builder.create().show()
    }

    private fun inputCheck(title: String, author: String): Boolean {
        return !(TextUtils.isEmpty(title) && TextUtils.isEmpty(author))
    }

    private fun updateItem() {
        binding.apply {
            val title = updateTitle.text.toString()
            val author = updateAuthor.text.toString()

            if (inputCheck(title, author)) {
                val updateBook = Book(args.currentBook.id, title, author)

                mBookViewModel.updateBook(updateBook)
                Toast.makeText(requireContext(), "Updated successfully!", Toast.LENGTH_LONG).show()
                findNavController().navigate(R.id.action_updateFragment_to_listFragment)
            } else {
                Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_LONG)
                    .show()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}