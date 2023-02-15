package com.example.Student_Library_Management_System.Models;


import com.example.Student_Library_Management_System.Enums.CardStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="card")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @CreationTimestamp  //it will auto timestamp the time , whenever an entry is created
    private Date createdOn;

    @UpdateTimestamp  //sets time when entry is updated
    private Date updatedOn;

    @Enumerated(value=EnumType.STRING)
    private CardStatus cardStatus;

    @OneToOne
    @JoinColumn
    private Student studentVariableName;  // this variable is used in the parent class while doing the bidirectional mapping


    //card is parent wrt book
    @OneToMany(mappedBy = "card",cascade = CascadeType.ALL)
    private List<Book> booksIssued;

    public List<Book> getBooksIssued() {
        return booksIssued;
    }

    public void setBooksIssued(List<Book> booksIssued) {
        this.booksIssued = booksIssued;
    }

    public List<Transactions> getTransactionsList() {
        return transactionsList;
    }

    public void setTransactionsList(List<Transactions> transactionsList) {
        this.transactionsList = transactionsList;
    }

    //connecting the card class to transaction
    //this is bydirection mapping
    @OneToMany(mappedBy = "card",cascade = CascadeType.ALL)
    private List<Transactions> transactionsList=new ArrayList<>();




    public Student getStudentVariableName() {
        return studentVariableName;
    }

    public void setStudentVariableName(Student studentVariableName) {
        this.studentVariableName = studentVariableName;
    }

    public Card() {
        booksIssued=new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    public CardStatus getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(CardStatus cardStatus) {
        this.cardStatus = cardStatus;
    }
}
