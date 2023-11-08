package com.shop.biz;

import com.shop.entity.Book;
import org.springframework.stereotype.Service;

import java.util.List;

public interface BookService {
    public int createBook(Book book) throws Exception;
    public List<Book> readBookAll() throws Exception;
    public Book readBook(Long seq) throws Exception;
}
