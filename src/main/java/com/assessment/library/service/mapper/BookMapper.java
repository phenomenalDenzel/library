package com.assessment.library.service.mapper;

import com.assessment.library.model.Book;
import com.assessment.library.service.dto.BookDTO;
import org.springframework.stereotype.Service;

@Service
public class BookMapper implements EntityMapper<BookDTO, Book>{

    @Override
    public Book toEntity(BookDTO dto){
        if(dto == null)
            return null;

        Book book = new Book();
        book.setAuthors(dto.getAuthors());
        book.setId(dto.getId());
        book.setTitle(dto.getTitle());
        book.setIsbn(dto.getIsbn());
        book.setCountry(dto.getCountry());
        book.setPublisher(dto.getPublisher());

        return book;
    }

    @Override
    public BookDTO toDto(Book entity){
        if(entity == null)
            return null;

        BookDTO book = new BookDTO();
        book.setAuthors(entity.getAuthors());
        book.setId(entity.getId());
        book.setTitle(entity.getTitle());
        book.setIsbn(entity.getIsbn());
        book.setCountry(entity.getCountry());
        book.setPublisher(entity.getPublisher());

        return book;
    }
}
