package com.example.Student_Library_Management_System.Models;


import com.example.Student_Library_Management_System.Enums.Genre;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private int pages;

    private boolean issued;

    @OneToMany(mappedBy = "book",cascade = CascadeType.ALL)
    private List<Transactions> listOfTransactions=new ArrayList<>();


    public List<Transactions> getListOfTransactions() {
        return listOfTransactions;
    }

    public void setListOfTransactions(List<Transactions> listOfTransactions) {
        this.listOfTransactions = listOfTransactions;
    }

    public boolean isIssued() {
        return issued;
    }

    public void setIssued(boolean issued) {
        this.issued = issued;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    @Enumerated(value = EnumType.STRING)
    private Genre genre;

    //book is child with respect to author
    //setting here the foreign key standard 3 steps

    @ManyToOne
    @JoinColumn// add an extra attribute of author id parent table pk (for the foreign key)
    private Author author; // this is the parent entity we are connecting with



    //book is also a child wrt card class
    @ManyToOne
    @JoinColumn
    private Card card;

    public Book() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
