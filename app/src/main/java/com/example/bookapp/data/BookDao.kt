package com.example.bookapp.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.bookapp.model.Book

@Dao
interface BookDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addBook(book: Book)

    @Update
    suspend fun updateBook(book: Book)

    @Delete
    suspend fun deleteBook(book: Book)

    @Query("DELETE FROM book_table")
    suspend fun deleteAllBooks()

    @Query("SELECT * FROM book_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<Book>>

}