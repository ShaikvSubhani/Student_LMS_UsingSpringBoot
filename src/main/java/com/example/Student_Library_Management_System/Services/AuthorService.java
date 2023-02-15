package com.example.Student_Library_Management_System.Services;

import com.example.Student_Library_Management_System.DTOs.AuthorEntryDto;
import com.example.Student_Library_Management_System.Models.Author;
import com.example.Student_Library_Management_System.Repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
