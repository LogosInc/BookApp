package com.example.bookapp.repository

import androidx.lifecycle.LiveData
import com.example.bookapp.data.BookDao
import com.example.bookapp.model.Book

class BookRepository(private val bookDao: BookDao) {

    val readAllData: LiveData<List<Book>> = bookDao.readAllData()

    suspend fun addBook(book: Book) {
        bookDao.addBook(book)
    }

    suspend fun updateBook(book: Book) {
        bookDao.updateBook(book)
    }

    suspend fun deleteBook(book: Book) {
        bookDao.deleteBook(book)
    }

    suspend fun deleteAllBooks() {
        bookDao.deleteAllBooks()
    }
}