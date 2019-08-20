// IBookManager.aidl
package com.android.clark.aidl;
import com.android.clark.aidl.Book;
// Declare any non-default types here with import statements

interface IBookManager {
    List<Book> getBookList();
    void addBook(in Book book);
}
