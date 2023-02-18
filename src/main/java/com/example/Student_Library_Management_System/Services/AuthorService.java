package com.example.Student_Library_Management_System.Services;

import com.example.Student_Library_Management_System.DTOs.AuthorEntryDto;
import com.example.Student_Library_Management_System.DTOs.AuthorResponseDto;
import com.example.Student_Library_Management_System.DTOs.BookResponseDto;
import com.example.Student_Library_Management_System.Models.Author;
import com.example.Student_Library_Management_System.Models.Book;
import com.example.Student_Library_Management_System.Repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    public String createAuthor(AuthorEntryDto authorEntryDto)
    {

        //important step is : in the params : the object is
        //of dto type but the repository interacts only with entities

        //solution : convert authorentrydto-->author


        //created an abject
        Author author=new Author();

        //so that we can set the correct values in the db
        author.setName(authorEntryDto.getName());
        author.setAge(authorEntryDto.getAge());
        author.setCountry(authorEntryDto.getCountry());
        author.setRating(authorEntryDto.getRating());

        authorRepository.save(author);
        return "author created";
    }

    public AuthorResponseDto getAuthorById(Integer authorId)
    {
        Author author= authorRepository.findById(authorId).get();

        AuthorResponseDto authorResponseDto=new AuthorResponseDto();

        //set its attribute

       // List<Book> --> List<BookResponseDto>

        List<Book> bookList=author.getBooksWritten();
        List<BookResponseDto> booksWrittenDto=new ArrayList<>();

        for(Book b: bookList)
        {
            BookResponseDto bookResponseDto=new BookResponseDto();
            bookResponseDto.setGenre(b.getGenre());
            bookResponseDto.setPages(b.getPages());
            bookResponseDto.setName(b.getName());

            booksWrittenDto.add(bookResponseDto);
        }

        //setting attributes for author responses

        authorResponseDto.setName(author.getName());
        authorResponseDto.setBooksWritten(booksWrittenDto);
        authorResponseDto.setAge(author.getAge());
        authorResponseDto.setRating(author.getRating());

        return authorResponseDto;



    }
}
