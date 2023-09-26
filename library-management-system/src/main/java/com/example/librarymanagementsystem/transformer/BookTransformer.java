package com.example.librarymanagementsystem.transformer;

import com.example.librarymanagementsystem.model.Book;

import java.util.ArrayList;
import java.util.List;

public class BookTransformer {
    public static List<String> BookListToStringList(List<Book> books){
        List<String> listOfBook = new ArrayList<>();

        for(var ele : books){
            listOfBook.add(ele.getName());
        }

        return listOfBook;
    }
}
