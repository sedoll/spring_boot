package com.shop.mapper;

import com.shop.entity.Book;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BookMapper {
    public int createBook(Book book) throws Exception;
    public List<Book> readBookAll() throws Exception;
    public Book readBook(Long seq) throws Exception;
}
