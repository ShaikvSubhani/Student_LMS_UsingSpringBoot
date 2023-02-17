package com.example.Student_Library_Management_System.Services;


import com.example.Student_Library_Management_System.DTOs.IssueBookRequestDto;
import com.example.Student_Library_Management_System.Enums.CardStatus;
import com.example.Student_Library_Management_System.Enums.TransactionStatus;
import com.example.Student_Library_Management_System.Models.Book;
import com.example.Student_Library_Management_System.Models.Card;
import com.example.Student_Library_Management_System.Models.Transactions;
import com.example.Student_Library_Management_System.Repositories.BookRepository;
import com.example.Student_Library_Management_System.Repositories.CardRepository;
import com.example.Student_Library_Management_System.Repositories.TransactionRepository;
import jakarta.transaction.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TransactionService {


    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    CardRepository cardRepository;

    public String issueBook(IssueBookRequestDto issueBookRequestDto) throws Exception
    {
        int bookId=issueBookRequestDto.getBookId();
        int cardId=issueBookRequestDto.getCardId();

        //get the card and book entity
        //because we need to set the attributes

        Book book =bookRepository.findById(bookId).get();
        Card card=cardRepository.findById(cardId).get();


        //final goal is to make a trnsaction entity and save it

        Transactions transaction=new Transactions();

        //setting the attributes
        transaction.setBook(book);
        transaction.setCard(card);
        transaction.setTransactionId(UUID.randomUUID().toString());
        transaction.setIssueOperation(true);
        transaction.setTransactionStatus(TransactionStatus.PENDING);


        if(book==null  || book.isIssued()==true)
        {
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);
            throw new Exception("Book is not available");
        }

        if(card==null || card.getCardStatus()!=CardStatus.ACTIVATED)
        {
            transaction.setTransactionStatus((TransactionStatus.FAILED));
            transactionRepository.save(transaction);
            throw new Exception("card is not valid");
        }

        //we have reached a success case now
        transaction.setTransactionStatus(TransactionStatus.SUCCESS);

        //set attributes of book
        book.setIssued(true);
        //btw the book and card bidirectional
        List<Transactions> listOfTransactionForBook=book.getListOfTransactions();
        listOfTransactionForBook.add(transaction);

        //i need to make changes in the card
        List<Book> issuedBookForCard=card.getBooksIssued();
        issuedBookForCard.add(book);
        card.setBooksIssued(issuedBookForCard);





        //card and the transactions
        List<Transactions> transactionsListForCard=card.getTransactionsList();
        transactionsListForCard.add(transaction);
        card.setTransactionsList(transactionsListForCard);

        //save the parent
        cardRepository.save(card);
        //automatically book and transaction will be save using cascade
        // save the parent

    return "book issued successfully";

    }

    public String getTransactions(int bookId,int cardId)
    {
        List<Transactions> transactionsList=transactionRepository.getTransactionsForBookAndCard(bookId,cardId);

        String transactionId=transactionsList.get(0).getTransactionId();

        return transactionId;
    }

}







