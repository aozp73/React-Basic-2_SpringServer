package com.example.book.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.book.domain.Book;
import com.example.book.domain.BookRespository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BookService {

    private final BookRespository bookRespository;

    @Transactional
    public Book save(Book book) {
        return bookRespository.save(book);
    }

    @Transactional(readOnly = true)
    public Book findById(Long id) {
        return bookRespository.findById(id).orElseThrow(() -> new IllegalArgumentException("id를 확인해주세요."));
    }

    @Transactional(readOnly = true)
    public List<Book> findAll() {
        return bookRespository.findAll();
    }

    @Transactional
    public Book update(Long id, Book book) {
        Book bookEntity = bookRespository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("id를 확인해주세요."));
        bookEntity.setTitle(book.getTitle());
        bookEntity.setAuthor(book.getAuthor());

        return bookEntity;
    }

    @Transactional
    public String delete(Long id) {
        bookRespository.deleteById(id);
        ;
        return "ok";
    }

}
