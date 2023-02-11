package com.example.Student_Library_Management_System.Services;


import com.example.Student_Library_Management_System.Models.Author;
import com.example.Student_Library_Management_System.Models.Book;
import com.example.Student_Library_Management_System.Repositories.AuthorRepository;
import com.example.Student_Library_Management_System.Repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    AuthorRepository authorRepository;


    public String addBook(Book book) {

        //I want to get the author  entity
        int authorId = book.getAuthor().getId();

        //now i will be fetching the author entity

        Author author;
        try {
            author = authorRepository.findById(authorId).get();
        } catch (Exception e) {
            return e.getMessage();
        }

//        int pages= book.getPages();

        //basic attribute are already set from postman

        //setting the foreign key attribute in the child class
        book.setAuthor(author);

        //we need to update the list of books written in the parent class

        List<Book> currentBooksWritten=author.getBooksWritten();
        currentBooksWritten.add(book);

//        author.setBooksWritten(currentBooksWritten);

        //now the book is to be save but also the author used to be save

        //why do we need to again save the author -> we are updating the author
        //because the author entity has been updated, we need to re save it or update it ;

        authorRepository.save(author);

        //.save to save and update

        return "book added successfully";
    }
}
